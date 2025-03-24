package org.example.matrix;

public class Matrix {
    private final int[][] data;

    public Matrix(int rows, int cols) {
        this.data = new int[rows][cols];
    }

    public void setValue(int row, int col, int value) {
        data[row][col] = value;
    }

    public int getValue(int row, int col) {
        return data[row][col];
    }

    public int[][] toArray() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : data) {
            sb.append('|');
            for (int value : row) {
                sb.append(' ').append(value);
            }
            sb.append(" |\n");
        }
        return sb.toString();
    }
}
