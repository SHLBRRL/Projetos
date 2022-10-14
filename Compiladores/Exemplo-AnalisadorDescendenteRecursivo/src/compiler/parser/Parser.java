package compiler.parser;

import compiler.scanner.Scanner;
import compiler.scanner.Tag;
import compiler.scanner.Token;

public class Parser {
    // referência a um scanner criado separadamente pelo JFlex
    private final Scanner scanner;
    // token atualmente obtido pelo Scanner
    private Token token;

    // classe do analisador sintático
    public Parser(Scanner scanner) throws Exception {
        // se não tem um scanner, então não é possível continuar
        if (scanner == null) {
            throw new Exception("Scanner não definido! Não foi possível criar o Parser!");
        }
        // armazena a referência
        this.scanner = scanner;
        // e obtém o primeiro token
        token = scanner.yylex();
    }

    // função para tratar erros
    private void error(Tag tag) throws Exception {
        throw new Exception("Erro durante a análise sintática: esperava marca com tag " + tag +
                " mas recebi marca com tag " + token.tag + "\n" +
                "Linha: " + token.line + " e Coluna: " + token.column);
    }

    private void error(Tag[] tags) throws Exception {
        String msg = "Erro durante a análise sintática: esperava marcas com tags [ ";
        for (Tag t : tags) {
            msg += t;
            msg += ' ';
        }
        msg += ']';
        msg += " mas recebi marca com tag " + token.tag + "\n" +
                "Linha: " + token.line + " e Coluna: " + token.column;
        throw new Exception(msg);
    }

    // função que verifica a marca atul e avança mna entrada
    private void accept(Tag tag) throws Exception {
        // Se o token atual é o que se está esperando
        if (token.tag == tag) {
            // avança um token na entrada
            System.out.println("TAG: " + tag);
            token = scanner.yylex();
        } else {
            // gera uma exceção
            error(tag);
        }
    }

    // função que inicia a análise sintática descendente
    // recursiva
    public void parse() throws Exception {
        // REGRA: goal = expr;
        program();
        // se, depois de expr() o token for EOF, tudo deu certo!
        if (token.tag == Tag.EOF) {
            System.out.println("Análise sintática terminada com sucesso!");
        } else {
            error(Tag.EOF);
        }
    }
    private void program() throws Exception{
        do{
            expr();
            accept(Tag.SC);
        }while(token.tag != Tag.EOF);
        
    }
    // REGRA: expr = term, eprime;
    private void expr() throws Exception {
        System.out.println("expr()");
        term();
        eprime();
    }

    // REGRA: eprime = { ('+' | '-'), term };
    private void eprime() throws Exception {
        System.out.println("eprime()");
        while (token.tag == Tag.PLUS || token.tag == Tag.MINUS) {
            accept(token.tag);
            term();
        }
    }

    // REGRA: term = factor, tprime;
    private void term() throws Exception {
        System.out.println("term()");
        factor();
        tprime();
    }

    // REGRA: tprime = { ( '*' | '/' ), factor };
    private void tprime() throws Exception {
        System.out.println("tprime()");
        while (token.tag == Tag.TIMES || token.tag == Tag.DIV) {
            accept(token.tag);
            factor();
        }
    }

    // REGRA: factor = '(', expr, ')' | number | id;
    private void factor() throws Exception {
        System.out.println("factor()");
    
        switch (token.tag) {
            
            case LPAREN:
                accept(Tag.LPAREN);
                expr();
                accept(Tag.RPAREN);
                break;
            case MINUS:
                accept(Tag.MINUS);
                expr();
                break;
            case NUMBER:
                accept(Tag.NUMBER);
                break;
            case ID:
                accept(Tag.ID);
                break;
            default:
                error(new Tag[] { Tag.LPAREN, Tag.NUMBER, Tag.ID });
                break;
        }
    }
}
