package org.example;

import org.example.zadania.lab1;
import org.example.zadania.lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner consoleInput = new Scanner(System.in)) {
            printTaskHeader(1,1); lab1.zad1(consoleInput);
            printTaskHeader(1,2); lab1.zad2(consoleInput);
            printTaskHeader(1,3); lab1.zad3(consoleInput);
            printTaskHeader(1,4); lab1.zad4(consoleInput);
            printTaskHeader(1,5); lab1.zad5(consoleInput);

            printTaskHeader(2,1); lab2.zad1(consoleInput);
            printTaskHeader(2,2); lab2.zad2(consoleInput);
            printTaskHeader(2,3); lab2.zad3(consoleInput);
            printTaskHeader(2,5); lab2.zad5(consoleInput);
        }
    }

    public static void printTaskHeader(int lab, int zad) {
        System.out.println("---------- Laboratorium " + lab + " Zadanie " + zad + " ----------");
    }
}
