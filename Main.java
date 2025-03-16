package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Podaj nazwe pliku tekstowego z rozszerzeniem:");

        Scanner consoleInput = new Scanner(System.in);
        String filePath = consoleInput.nextLine();

        File file = new File(filePath);
        Scanner fileInput;

        try {
            fileInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int verticesCount;
        int edgesCount;

        String firstLine = fileInput.nextLine();
        String[] GraphData = firstLine.split(" ");

        verticesCount = Integer.parseInt(GraphData[0]);
        edgesCount = Integer.parseInt(GraphData[1]);

        while(fileInput.hasNextLine()) {
            String data = fileInput.nextLine();
            System.out.println(data);
        }
    }
}