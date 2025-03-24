package org.example.zadania;

import org.example.graph.Graph;
import org.example.matrix.Matrix;

import java.io.IOException;
import java.util.Scanner;

public class lab1 {
    public static void zad1(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab1/graph.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromFile(filePath);

            Matrix adjacencyMatrix = graph.getAdjacencyMatrix();
            Matrix incidenceMatrix = graph.getIncidenceMatrix();

            var vertices = graph.getVertices();
            var edges = graph.getEdges();

            System.out.printf("Liczba wierzcholkow grafu G wynosi %d", vertices.size());
            System.out.println();
            System.out.printf("Zbior wierzcholkow V = %s", vertices.values());

            System.out.println();
            System.out.println();

            System.out.printf("Liczba krawedzi grafu G wynosi %d", edges.size());
            System.out.println();
            System.out.printf("Zbior krawedzi E = %s", edges);

            System.out.println();
            System.out.println();

            System.out.println("Macierz sasiedztwa A =");
            System.out.println(adjacencyMatrix);

            System.out.println("Macierz incydencji M =");
            System.out.println(incidenceMatrix);

        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }

    public static void zad2(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab1/graph.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromFile(filePath);
        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }
}
