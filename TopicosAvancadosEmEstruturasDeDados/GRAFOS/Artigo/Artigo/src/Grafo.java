import java.util.ArrayList;

public class Grafo {

    ArrayList<Vertice> listaVertices;

    public Grafo(ArrayList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public Grafo() {
        this.listaVertices = null;
    }

    public void addVertice(Vertice v) {
        if(this.listaVertices == null){
            this.listaVertices = new ArrayList<Vertice>();   
        }
        this.listaVertices.add(v);
    }
    
    public void imprimeArestasDeVertice(Vertice v) {
        if(this.listaVertices.contains(v)){
            if(v.listaArestas == null){
                System.out.println("Vertice sem arestas");
            }
            for (Aresta aresta: v.listaArestas){
                System.out.println(aresta);
            }
        } else {
            System.out.println("Vertice inexistente no grafo");            
        }
    }
    
    public void imprimeVerticesDoGrafo() {
        for(Vertice v: this.listaVertices){
            System.out.println(v);
        }
    }

    public Integer retornaGrauVertice(Vertice v) {
        if(v.listaArestas == null){
            return null;
        }
        return v.listaArestas.size();
    }

    public void imprimeGrauVertice(Vertice v) {
        if (!this.listaVertices.contains(v)){
            System.out.println("Vértice não existente no grafo");
        } else {
            System.out.println(v.listaArestas.size());
        }
        
    }
    
    public boolean removeVertice(Vertice v) {
        if (!this.listaVertices.contains(v)){
            System.out.println("Vértice inexistente no grafo");
            return false;
        }
        this.listaVertices.remove(v);
        System.out.println("Vértice removido com sucessso!");
        return true;
    }

    public boolean imprimeGrafo() {
        for(Vertice v : this.listaVertices){
            System.out.println("Vértice: " + v);
            System.out.println("Arestas: " + v.listaArestas);
        }
        return true;
    }
}
