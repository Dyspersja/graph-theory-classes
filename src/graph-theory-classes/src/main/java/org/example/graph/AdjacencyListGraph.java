package org.example.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListGraph {
    private final Map<Integer, List<Integer>> adjacencyList;

    public AdjacencyListGraph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int v1, int v2) {
        adjacencyList.putIfAbsent(v1, new ArrayList<>());
        adjacencyList.putIfAbsent(v2, new ArrayList<>());
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var vertex : adjacencyList.entrySet()) {
            var vertexId = vertex.getKey();
            var adjacent = vertex.getValue();
            sb.append(vertexId).append(" -> ").append(adjacent).append("\n");
        }
        return sb.toString();
    }
}
