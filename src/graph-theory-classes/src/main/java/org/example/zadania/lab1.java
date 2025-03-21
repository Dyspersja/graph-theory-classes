package org.example.zadania;

import org.example.graph.Graph;

import java.io.IOException;
import java.util.Scanner;

public class lab1 {
    public static void zad1() {
        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        // src/main/resources/lab1/zad1/graph.txt
        Scanner consoleInput = new Scanner(System.in);
        String filePath = consoleInput.nextLine();
        consoleInput.close();

        Graph graph = new Graph();

        try {
            graph.loadFromFile(filePath);
        } catch (IOException e) {
            System.err.println("Error loading graph from file: " + e.getMessage());
        }
    }
}
