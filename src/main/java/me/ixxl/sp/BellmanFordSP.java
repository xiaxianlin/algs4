package me.ixxl.sp;

import me.ixxl.common.EdgeWeightedDirectedCycle;
import me.ixxl.struct.Queue;

public class BellmanFordSP extends SP {
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    private void findNegativeCycle() {
        int v = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(v);
        for (int i = 0; i < v; i++) {
            if (edgeTo[i] != null) {
                spt.addEdge(edgeTo[i]);
            }
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    @Override
    protected void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    return;
                }
            }
        }
    }

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        super(G, s);
        onQ = new boolean[G.V()];
        onQ[s] = true;

        queue = new Queue<>();
        queue.enqueue(s);

        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negetiveCycle() {
        return cycle;
    }
}
