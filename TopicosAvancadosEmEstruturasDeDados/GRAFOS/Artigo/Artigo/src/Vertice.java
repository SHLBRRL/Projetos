import java.util.ArrayList;

public class Vertice {

    String dado;
    ArrayList<Aresta> listaArestas;

    public Vertice(String dado, ArrayList<Aresta> listaArestas) {
        this.dado  = dado;
        this.listaArestas = listaArestas;
    }
        
    public Vertice(String dado) {
        this.dado = dado;
        this.listaArestas = null;
    }

    public void addAresta(Aresta a) {
        if (this.listaArestas == null) {
            this.listaArestas = new ArrayList<Aresta>();
        }
        this.listaArestas.add(a);
        
    }
    
    public boolean removeAresta(Aresta a) {
        if(listaArestas.contains(a)){
            listaArestas.remove(a);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vertice [dado=" + dado + "]";
    }
}

