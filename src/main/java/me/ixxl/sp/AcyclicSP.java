package me.ixxl.sp;

import me.ixxl.common.Topological;

public class AcyclicSP extends SP {

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        super(G, s);
        Topological top = new Topological(G);

        if (!top.hasOrder())
            throw new IllegalArgumentException("EdgeWeightedDigraph is not acyclic.");
        for (int v : top.order()) {
            relax(G, v);
        }
    }

}
