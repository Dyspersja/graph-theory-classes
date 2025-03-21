package org.example.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<Integer, Vertex> vertices;
    private final List<Edge> edges;

    Graph() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addVertex(int id) {
        vertices.putIfAbsent(id, new Vertex(id));
    }

    public void addEdge(int id1, int id2) {
        vertices.putIfAbsent(id1, new Vertex(id1));
        Vertex v1 = vertices.get(id1);

        vertices.putIfAbsent(id2, new Vertex(id2));
        Vertex v2 = vertices.get(id2);

        edges.add(new Edge(v1, v2));
    }
}
