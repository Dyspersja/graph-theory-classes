package org.example.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<Integer, Vertex> vertices;
    private final List<Edge> edges;

    public Graph() {
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

    public void loadFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] firstLine = br.readLine().split(" ");
            int numVertices = Integer.parseInt(firstLine[0]);
            int numEdges = Integer.parseInt(firstLine[1]);

            br.lines().forEach(line -> {
                String[] parts = line.split(" ");
                int v1 = Integer.parseInt(parts[0]);
                int v2 = Integer.parseInt(parts[1]);
                addEdge(v1, v2);
            });
        }
    }
}
