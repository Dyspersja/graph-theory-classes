package org.example.Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertice> vertices;
    private List<Edge> edges;

    Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public boolean AddVertice(Vertice v) {
        if (vertices.stream().map(Vertice::getName).anyMatch( name -> name.equals(v.getName()))) {
            vertices.add(v);
            return true;
        }
        return false;
    }

    public void AddEdge(Edge e) {

    }

}
