/**
 * Analisador léxico para expressões simples
 */
package analisador_lexico;

%%

%class Lexer
%public
%unicode
%debug
%standalone
%line
%column
%type Token

%eofval{
    return new Token(Tag.EOF);
%eofval}

%eof{
    System.out.println("Análise léxica terminada com sucesso!");
%eof}

%{
    // Buffer para armazenar as cadeias de caracteres
    private StringBuffer buffer = new StringBuffer();
%}

%{
    // Buffer para armazenar as cadeias de caracteres
    private StringBuffer comment = new StringBuffer();
%}
%{
// Macros
%}

%xstate IN_STR
%xstate IN_COM

delim	= [\ \t\n]
punct = [{ } ; ( ) ,]
ws		= {delim}+
letter	= [A-Za-z]
digit	= [0-9]
id		= {letter}({letter}|{digit}){0,32}
number	= {digit}+(\.{digit}+)?(E[+-]?{digit}+)?
char = "'"{character}"'"
character = ({letter} | {digit})
%%
/* Regras e ações */
<YYINITIAL> {
    "\"" 	    { yybegin(IN_STR); buffer.setLength(0); }
    "/*"        { yybegin(IN_COM); comment.setLength(0); }
    {ws}		{ /* ignorar */ }
    program		{ return new Token(Tag.PROGRAM); }
    if			{ return new Token(Tag.IF); }
    else		{ return new Token(Tag.ELSE); }
    while		{ return new Token(Tag.WHILE); }
    foreach		{ return new Token(Tag.FOREACH); }
    function	{ return new Token(Tag.FUNCTION); }
    end		    { return new Token(Tag.END); }
    {id}		{ return new Id(yytext()); }
    {number}	{ return new Num(Double.parseDouble(yytext())); }
    {punct}     { return new Punctuation(yytext());}
    {char}      { return new Char(yytext());}
    "<"			{ return new Relop(Tag.LT);}
    "<="		{ return new Relop(Tag.LE);}
    "=="		{ return new Relop(Tag.EQ);}
    "!="		{ return new Relop(Tag.NE);}
    ">"			{ return new Relop(Tag.GT);}
    ">="		{ return new Relop(Tag.GE);}
    "="			{ return new Atop(Tag.ATTR);}
    "+"			{ return new Mathop(Tag.ADD);}
    "-"		    { return new Mathop(Tag.SUB);}
    "*"			{ return new Mathop(Tag.MULT);}
    "/"		    { return new Mathop(Tag.DIV);}
    "%"			{ return new Mathop(Tag.RST);}
    "++"		{ return new Mathop(Tag.INC);}
    "--"		{ return new Mathop(Tag.DEC);}
    

    /* Qualquer outro - gerar erro */
    .		    { throw new Error("Símbolo ilegal <" + yytext() +
                    "(" + (int)(yytext().charAt(0)) + ")" + ">"); }
}
<IN_STR> {
	"\""		{ yybegin(YYINITIAL); 
				    return new Str(buffer.toString()); }
	.|{delim}	{ buffer.append(yytext()); }
    <<EOF>> {throw new Error("cadeia não terminada");}
}

<IN_COM> {
	"*/"		{ yybegin(YYINITIAL); 
				    return new Str(comment.toString()); }
	.|{delim}	{ comment.append(yytext()); }
    <<EOF>> {throw new Error("cadeia não terminada");}
}
