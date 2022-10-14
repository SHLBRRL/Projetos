package analisador_lexico;

// Token de operador relacional
public class Mathop extends Token {
    public final Tag op;

    public Mathop(Tag op) {
        super(Tag.MATHOP);
        this.op = op;
    }

    @Override
    public String toString() {
        return "Mathop: <" + this.tag + ",\"" + this.op + "\">";
    }
}