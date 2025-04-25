package org.example.zadania;

import org.example.graph.Graph;

import java.io.IOException;
import java.util.Scanner;

public class lab2 {
    public static void zad1(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab2/g.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromStructuredFile(filePath);

            int degree = graph.getRegularDegree();

            if (degree == -1) {
                System.out.println("Graf G nie jest grafem regularnym");
            } else {
                System.out.println("Graf G jest grafem regularnym");
                System.out.printf("Stopień Grafu G to %d", degree);
            }

        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }

    public static void zad2(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab2/cyclic.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromStructuredFile(filePath);

            graph.transformToWheelGraph();
            graph.setName("W");
            graph.saveToFile("src/main/resources/lab2/w.txt");

        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }

    public static void zad3(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab2/wheel.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromStructuredFile(filePath);

            if (graph.isWheelAndTransformToCycle()) {
                System.out.println("Graf jest kołem");

                System.out.println(graph.getEdges());
                graph.setName("c");
                graph.saveToFile("c.txt");
            } else {
                System.out.println("Graf nie jest kołem");
            }

        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }

    public static void zad5(Scanner consoleInput) {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab2/g.txt
        String filePath = consoleInput.nextLine();

        Graph graph = new Graph();

        try {
            graph.loadFromStructuredFile(filePath);

            if (graph.isEulerian()) {
                System.out.println("Graf jest grafem eulerowskim");
            }

            if (graph.isSemiEulerian()) {
                System.out.println("Graf jest grafem półeulerowskim");
            }

            if (graph.isNonEulerian()) {
                System.out.println("Graf jest grafem nieeulerowskim");
            }

        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }
}
