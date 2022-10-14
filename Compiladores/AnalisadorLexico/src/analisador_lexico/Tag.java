package analisador_lexico;

// Rótulos para as marcas
public enum Tag {
    EOF, NUMBER, ID,
    RELOP, MATHOP, ATOP, PROGRAM, IF, ELSE, WHILE, FOREACH, FUNCTION, END,
    LT, LE, EQ, NE, GT, GE, STR, DIV, ADD, SUB, ATTR, INC, DEC, RST , MULT, COMMENT,PUNCTUATION, CHAR
}