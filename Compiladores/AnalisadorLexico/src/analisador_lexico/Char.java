package analisador_lexico;

// Token de identificador
public class Char extends Token {
    public final String lexeme;

    public Char(String s) {
        super(Tag.CHAR);
        lexeme = new String(s);
    }

    @Override
    public String toString() {
        return "Char: <" + this.tag + ",\"" + this.lexeme + "\">";
    }
}