package transpiler;
import java.util.HashMap; //para armazenar as variáveis e seus valores
import parser.sym; //para obter as constantes de terminais
import ast.AST; //árvore AST
import ast.Identifier; //AST de identificador
import ast.Number; //AST de número
import ast.Operator; //AST de operador
import java.util.Locale;

public class Transpiler {
    // Tabela de símbolos
    private static final HashMap<String, Double> symbolTable = new HashMap<>();
    
    private static final StringBuilder stackProgram = new StringBuilder();

    public static void clearStackProgram() {
        stackProgram.setLength(0);
    }

    public static String getStackProgram() {
        return stackProgram.toString();
    }

    public static void transpile(AST ast) throws Exception {
        // se a árvore não for nula
        Locale.setDefault(Locale.US);
        if (ast != null) {
            // processar a AST esquerda
            transpile(ast.getLeftAST());
            // processar a AST direita
            transpile(ast.getRightAST());
            // se for operador
            if (ast instanceof Operator) {
                // obter a árvore com o operador
                Operator operator = (Operator) ast;
                // obter o valor do operador
                int op = operator.getOperatorSymbol();
                // operadores SEMPRE terão, no mínimo, o lado esquerdo
                // então já pode salvá-lo aqui
                AST leftAST = operator.getLeftAST();
                // este switch executa cada operador separadamente
                switch (op) {
                    case sym.PRINT: {// exibir na tela
                        stackProgram.append(
                            String.format("%s", "PRINT"));
                        break;
                    }
                    case sym.READ: {
                        // obter o nome do identificador
                        String id = ((Identifier) operator.getLeftAST())
                                .getIdentifierName();
                        // adicionar ou atualizar a variável
                        symbolTable.put(id, 0.0);
                        stackProgram.append(
                            String.format("%s\n", "READ")+
                            String.format("%s", "POP " + id));  
                        break;
                    }
                    case sym.ASSIGN: { // atribuição
                        // obter o identificador
                        String id = ((Identifier) leftAST).getIdentifierName();
                        
                        symbolTable.put(id, 0.0);
                        stackProgram.append(
                            String.format("%s", "POP " + id));
                        break;
                    }
                    case sym.PLUS: {// soma
                        stackProgram.append(
                            String.format("%s\n", "ADD" ));
                        break;
                    }
                    case sym.MINUS: { // subtração
                        stackProgram.append(
                            String.format("%s\n", "SUB" ));
                        break;
                    }
                    case sym.UMINUS: { // inversão de sinal
                        stackProgram.append(
                            String.format("%s\n", "NEG" ));
                        break;
                    }
                    case sym.TIMES: { // multiplicação
                        stackProgram.append(
                            String.format("%s\n", "MUL" ));
                        break;
                    }
                    case sym.DIVIDE: { // divisão
                        stackProgram.append(
                            String.format("%s\n", "DIV" ));
                        break;
                    }
                    case sym.MOD: { // resto
                        stackProgram.append(
                            String.format("%s\n", "MOD" ));
                        break;
                    }
                    case sym.EXP: {// exponenciação
                        stackProgram.append(
                            String.format("%s\n", "EXP" ));
                        break;
                    }
                    case sym.COS: { // coseno
                        stackProgram.append(
                            String.format("%s\n", "COS" ));
                        break;
                    }
                    case sym.SIN: { // seno
                        stackProgram.append(
                            String.format("%s\n", "SIN" ));
                        break;
                    }
                }
            } else if (ast instanceof Number) {
                // se for número, basta armazená-lo no próprio
                // campo valor da árvore
                Number number = (Number) ast;
                stackProgram.append(
                    String.format("PUSH %.2f\n", 
                        number.getValue()));
                
            } else if (ast instanceof Identifier) {
                // obter o identificador
                Identifier id = (Identifier) ast;
                // veja se ele está definido na tabela de símbolos
                Double value = symbolTable.get(id.getIdentifierName());
                // se estiver na tabela de símbolos, basta retornar seu
                // conteúdo na pilha
                if (value != null) {
                    stackProgram.append(
                    String.format("PUSH %s\n", 
                    id.getIdentifierName()));
                } else { // não está na tabela
                    // como ele não está na tabela, então pode ser o caso de
                    // um ID não inicializado. Mas se ele estiver em uma
                    // expressão de ATRIBUIÇÃO, isso não é um erro ->
                    // precisa verificar esse caso
                    AST parentAST = id.getParentAST();
                    // primeiro, se o pai não for um operador então não
                    // está inicializada com certeza
                    if (parentAST instanceof Operator == false) {
                        throw new Exception("Identificador não inicializado: "
                                + "\"" + id.getIdentifierName() + "\"");
                    } else {
                        // ver o tipo do operador
                        Operator op = ((Operator) parentAST);
                        // se for diferente do operador de atribuição ou leitura,
                        // então o id não está inicializado
                        if (!(op.getOperatorSymbol() == sym.ASSIGN
                                || op.getOperatorSymbol() == sym.READ)) {
                            throw new Exception("Identificador não inicializado: "
                                    + "\"" + id.getIdentifierName() + "\"");
                        }
                    }
                }
            }
        }
    }
}
