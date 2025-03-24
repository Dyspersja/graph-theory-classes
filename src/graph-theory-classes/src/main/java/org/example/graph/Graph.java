package org.example.graph;

import org.example.matrix.Matrix;

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

    public Matrix getAdjacencyMatrix() {
        int size = vertices.size();
        Matrix matrix = new Matrix(size, size);

        for (Edge edge : edges) {
            int v1 = edge.getV1().getId() - 1;
            int v2 = edge.getV2().getId() - 1;
            matrix.setValue(v1, v2, 1);
            matrix.setValue(v2, v1, 1);
        }
        return matrix;
    }

    public Matrix getIncidenceMatrix() {
        int vertexCount = vertices.size();
        int edgeCount = edges.size();
        Matrix matrix = new Matrix(vertexCount, edgeCount);

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int v1 = edge.getV1().getId() - 1;
            int v2 = edge.getV2().getId() - 1;
            matrix.setValue(v1, i, 1);
            matrix.setValue(v2, i, 1);
        }
        return matrix;
    }

    public Map<Integer, Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
