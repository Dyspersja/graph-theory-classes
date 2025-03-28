package org.example.zadania;

import org.example.graph.AdjacencyListGraph;
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

            var degrees = graph.getDegrees();

            int order = graph.getOrder();
            int size = graph.getSize();

            System.out.printf("Rzad grafu G wynosi %d", order);

            System.out.println();
            System.out.println();

            System.out.printf("Rozmiar grafu G wynosi %d", size);

            System.out.println();
            System.out.println();

            System.out.println("Stopnie wierzcholkow:");
            degrees.forEach((key, value) -> System.out.printf("deg(%d) = %d\n", key, value));

            System.out.println();

            System.out.print("Ciag stopni grafu G: ");
            System.out.println(degrees.values().stream().sorted().toList());


        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }

    public static void zad3(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab1/graph.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromFile(filePath);

            boolean simple = graph.isSimple();

            System.out.println(simple ? "Graf G jest grafem prostym." : "Graf G jest grafem ogolnym.");

        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }

    public static void zad4(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab1/graph.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromFile(filePath);

            boolean complete = graph.isComplete();

            if (complete) {
                System.out.println("Graf G jest grafem pelnym.");
            } else {
                System.out.println("Graf G nie jest grafem pelnym.");

                System.out.println();

                var complement = graph.getComplement();

                System.out.print("Krawedzie dopelnienia grafu G: ");
                System.out.println(complement);
            }

        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }

    public static void zad5(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab1/graph.txt
        String filePath = consoleInput.nextLine();

        AdjacencyListGraph graph = new AdjacencyListGraph();

        try {
            graph.loadFromFile(filePath);
            System.out.println(graph);
        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }
}
