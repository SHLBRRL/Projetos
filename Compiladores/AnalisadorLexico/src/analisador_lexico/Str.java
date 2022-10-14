package analisador_lexico;

// Token para cadeias de caracteres
public class Str extends Token {
    public final String str;

    public Str(String str) {
        super(Tag.STR);
        this.str = str;
    }

    @Override
    public String toString() {
        return "STRING: <" + this.tag + ",\"" + this.str + "\">";
    }
}