package analisador_lexico;

// Token de operador relacional
public class Atop extends Token {
    public final Tag op;

    public Atop(Tag op) {
        super(Tag.ATOP);
        this.op = op;
    }

    @Override
    public String toString() {
        return "Atop: <" + this.tag + ",\"" + this.op + "\">";
    }
}