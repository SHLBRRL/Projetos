package analisador_lexico;

// Token de identificador
public class Punctuation extends Token {
    public final String lexeme;

    public Punctuation(String s) {
        super(Tag.PUNCTUATION);
        lexeme = new String(s);
    }

    @Override
    public String toString() {
        return "PUNCTUATION: <" + this.tag + ",\"" + this.lexeme + "\">";
    }
}