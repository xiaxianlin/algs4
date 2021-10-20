package me.ixxl.sp;

import me.ixxl.pq.IndexMinPQ;

public class DijkstraSP extends SP {
    private IndexMinPQ<Double> pq;

    protected void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        super(G, s);
        pq = new IndexMinPQ<>(G.V());
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

}
