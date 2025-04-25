package org.example.graph;

import org.example.matrix.Matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Graph {
    private String name;

    private final Map<Integer, Vertex> vertices;
    private final List<Edge> edges;

    public Graph() {
        name = "G";
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addVertex(int id) {
        vertices.putIfAbsent(id, new Vertex(id));
    }

    public void removeVertex(int id) {
        vertices.remove(id);
        edges.removeIf(edge -> edge.getV1().getId() == id || edge.getV2().getId() == id);
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

    public void loadFromStructuredFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> {
                int index = line.indexOf(';');
                if (index == -1) return;

                String edge = line.substring(0,index);
                String[] parts = edge.split("--");

                int v1 = Integer.parseInt(parts[0].trim());
                int v2 = Integer.parseInt(parts[1].trim());
                addEdge(v1, v2);
            });
        }
    }

    public void saveToFile(String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.print(this);
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

    public Map<Integer, Integer> getDegrees() {
        Map<Integer, Integer> degrees = new HashMap<>();
        for (Edge edge : edges) {
            int v1 = edge.getV1().getId();
            int v2 = edge.getV2().getId();
            degrees.put(v1, degrees.getOrDefault(v1, 0) + 1);
            degrees.put(v2, degrees.getOrDefault(v2, 0) + 1);
        }
        return degrees;
    }

    public int getRegularDegree() {
        Map<Integer, Integer> degrees = getDegrees();

        int expected = degrees.values().iterator().next();
        for (int degree : degrees.values()) {
            if (degree != expected) {
                return -1;
            }
        }

        return expected;
    }

    public Set<Edge> getComplement() {
        Set<Edge> complementEdges = new HashSet<>();

        for (Vertex v1 : vertices.values()) {
            for (Vertex v2 : vertices.values()) {
                if (v1 == v2) continue;

                Edge edge = new Edge(v1, v2);
                if (!edges.contains(edge)) {
                    complementEdges.add(edge);
                }
            }
        }

        return complementEdges;
    }

    public boolean isSimple() {
        Set<Edge> previousEdges = new HashSet<>();

        for (Edge edge : edges) {
            int v1 = edge.getV1().getId();
            int v2 = edge.getV2().getId();

            if (v1 == v2) return false;
            if (!previousEdges.add(edge)) return false;
        }

        return true;
    }

    public boolean isComplete() {
        int n = vertices.size();
        return edges.size() == (n * (n - 1)) / 2;
    }

    public boolean isCyclic() {
        if(!this.isConnected()) return false;
        return this.getRegularDegree() == 2;
    }

    public boolean isConnected() {
        int startVertex = vertices.keySet().iterator().next();

        Set<Integer> visited = new HashSet<>();
        dfs(startVertex, visited);

        return visited.size() == vertices.size();
    }

    public boolean isEulerian() {
        if (!isConnected()) return false;

        Map<Integer, Integer> degrees = getDegrees();
        for (int degree : degrees.values()) {
            if (degree % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isSemiEulerian() {
        if (!isConnected()) return false;

        Map<Integer, Integer> degrees = getDegrees();
        int oddDegreeCount = 0;

        for (int degree : degrees.values()) {
            if (degree % 2 != 0) {
                oddDegreeCount++;
            }
        }

        return oddDegreeCount == 2;
    }

    public boolean isNonEulerian() {
        return !isEulerian() && !isSemiEulerian();
    }

    public boolean isWheelAndTransformToCycle() {
        if (vertices.size() < 4) return false;

        Map<Integer, Integer> degrees = getDegrees();
        int centerVertex = -1;
        int maxDegree = -1;

        for (Map.Entry<Integer, Integer> entry : degrees.entrySet()) {
            if (entry.getValue() > maxDegree) {
                maxDegree = entry.getValue();
                centerVertex = entry.getKey();
            }
        }

        if (maxDegree != vertices.size() - 1) {
            return false;
        }

        removeVertex(centerVertex);
        return this.isCyclic();
    }

    public void transformToWheelGraph() {
        if (!isCyclic()) return;

        int centralVertexId = vertices.size() + 1;
        this.addVertex(centralVertexId);

        for (int v : vertices.keySet()) {
            if (centralVertexId == v) continue;
            this.addEdge(centralVertexId, v);
        }
    }

    private void dfs(int current, Set<Integer> visited) {
        visited.add(current);

        for (Edge edge : edges) {
            if (edge.getV1().getId() == current) {
                int neighbor = edge.getV2().getId();
                if(!visited.contains(neighbor)) {
                    dfs(neighbor, visited);
                }
            } else if (edge.getV2().getId() == current) {
                int neighbor = edge.getV1().getId();
                if(!visited.contains(neighbor)) {
                    dfs(neighbor, visited);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("graph ");
        sb.append(this.name);
        sb.append(" {\n");

        for (Edge edge : edges) {
            int v1 = edge.getV1().getId();
            int v2 = edge.getV2().getId();
            sb.append("    ").append(v1).append(" -- ").append(v2).append(";\n");
        }
        sb.append("}");

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getOrder() {
        return vertices.size();
    }

    public int getSize() {
        return edges.size();
    }
}
