package org.example.graph;

public class Vertex {
    private final int id;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
