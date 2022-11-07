public class Teste_Grafo {
    public static void main(String[] args) {
        Grafo g = new Grafo();

        Vertice V1 = new Vertice("Campinas");
        Vertice V2 = new Vertice("São Paulo");
        Vertice V3 = new Vertice("Campos do Jordão");
        Vertice V4 = new Vertice("São Lourenço");
        Vertice V5 = new Vertice("Poços de Caldas");
        Vertice V6 = new Vertice("BatCaverna");

        V1.addAresta(new Aresta(V1, V2));
        V1.addAresta(new Aresta(V1, V5));

        V2.addAresta(new Aresta(V2, V1));
        V2.addAresta(new Aresta(V2, V3));
        V2.addAresta(new Aresta(V2, V4));
        V2.addAresta(new Aresta(V2, V5));
        
        V3.addAresta(new Aresta(V3, V2));
        V3.addAresta(new Aresta(V3, V4));

        V4.addAresta(new Aresta(V4, V2));
        V4.addAresta(new Aresta(V4, V3));
        V4.addAresta(new Aresta(V4, V5));

        V5.addAresta(new Aresta(V5, V1));
        V5.addAresta(new Aresta(V5, V2));
        V5.addAresta(new Aresta(V5, V4));
        
        g.addVertice(V1);
        g.addVertice(V2);
        g.addVertice(V3);
        g.addVertice(V4);
        g.addVertice(V5);

        g.imprimeArestasDeVertice(V5);

        g.imprimeVerticesDoGrafo();

        g.retornaGrauVertice(V2);

        g.imprimeGrauVertice(V2);

        g.imprimeGrafo();
        
        System.out.println("*************************************************************************");
        g.imprimeArestasDeVertice(V6);

        System.out.println(g.retornaGrauVertice(V6));
        

        g.imprimeGrauVertice(V6);
    }
}