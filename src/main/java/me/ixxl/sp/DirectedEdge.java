package me.ixxl.sp;

public class DirectedEdge {
    private final int V;
    private final int W;
    private final double weight;

    public DirectedEdge(int V, int W, double weight) {
        this.V = V;
        this.W = W;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return V;
    }

    public int to() {
        return W;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", V, W, weight);
    }

}
