package org.example;

import org.example.zadania.lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner consoleInput = new Scanner(System.in)) {
            // src/main/resources/lab1/graph.txt
            printTaskHeader(1,1); lab1.zad1(consoleInput);
            printTaskHeader(1,2); lab1.zad2(consoleInput);
        }
    }

    public static void printTaskHeader(int lab, int zad) {
        System.out.println("---------- Laboratorium " + lab + " Zadanie " + zad + " ----------");
    }
}
