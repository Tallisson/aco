/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ctu.labiras.aco.grafo;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 *
 * @author thiago
 */
public abstract class Grafo {
    private static Graph<Vertice, AcoLink> graph = new SparseGraph<>();
    
    public static void addVertice(Vertice v){
        graph.addVertex(v);
    }
    
    public static void addAresta(AcoLink e, Vertice v, Vertice v1) {
        graph.addEdge(e, v, v1, EdgeType.UNDIRECTED);
    }
}
