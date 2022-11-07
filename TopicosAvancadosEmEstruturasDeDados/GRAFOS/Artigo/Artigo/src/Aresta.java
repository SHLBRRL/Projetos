public class Aresta {
    Vertice origem;
    Vertice destino;
    
    public Aresta(Vertice origem, Vertice destino) {
        this.origem = origem;
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Aresta [origem=" + origem + ", destino=" + destino + "]";
    }
}
    