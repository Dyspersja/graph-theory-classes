package org.example.graph;

import java.util.Objects;

public class Edge {
    private final Vertex v1;
    private final Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    @Override
    public String toString() {
        return v1 + "-" + v2;
    }

    @Override
    public int hashCode() {
        int smaller = Math.min(v1.getId(), v2.getId());
        int bigger = Math.max(v1.getId(), v2.getId());

        return Objects.hash(smaller, bigger);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge edge)) return false;

        int v1 = this.v1.getId();
        int v2 = this.v2.getId();
        int ov1 = edge.v1.getId();
        int ov2 = edge.v2.getId();

        return (ov1 == v1 && ov2 == v2) || (ov1 == v2 && ov2 == v1);
    }
}
