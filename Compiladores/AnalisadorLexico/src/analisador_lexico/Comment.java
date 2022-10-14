package analisador_lexico;

// Token de identificador
public class Comment extends Token {
    public final String lexeme;

    public Comment(String s) {
        super(Tag.COMMENT);
        lexeme = new String(s);
    }

    @Override
    public String toString() {
        return "Comment: <" + this.tag + ",\"" + this.lexeme + "\">";
    }
}