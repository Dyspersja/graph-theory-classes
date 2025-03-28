package org.example;

import org.example.zadania.lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner consoleInput = new Scanner(System.in)) {
            // src/main/resources/lab1/graph.txt
            printTaskHeader(1,1); lab1.zad1(consoleInput);
            printTaskHeader(1,2); lab1.zad2(consoleInput);
            printTaskHeader(1,3); lab1.zad3(consoleInput);
            printTaskHeader(1,4); lab1.zad4(consoleInput);
            printTaskHeader(1,5); lab1.zad5(consoleInput);
        }
    }

    public static void printTaskHeader(int lab, int zad) {
        System.out.println("---------- Laboratorium " + lab + " Zadanie " + zad + " ----------");
    }
}
