package me.ixxl.mst;

public class MST {

    public MST(EdgeWeightGraph G) {
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }

    public Iterable<Edge> edges() {
        return null;
    }
}
