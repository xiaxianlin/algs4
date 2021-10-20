package me.ixxl;

import org.junit.Test;

import edu.princeton.cs.algs4.In;
import me.ixxl.mst.Edge;
import me.ixxl.mst.EdgeWeightGraph;
import me.ixxl.mst.KruskalMST;
import me.ixxl.mst.LazyPrimMST;
import me.ixxl.mst.MST;
import me.ixxl.mst.PrimMST;

public class MSTTest {
    private EdgeWeightGraph getGraph() {
        In in = new In("./assets/tinyEWG.txt");
        EdgeWeightGraph G = new EdgeWeightGraph(in);
        return G;
    }

    @Test
    public void testPrim() {
        MST mst = new PrimMST(getGraph());

        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weight());
    }

    @Test
    public void testLazyPrim() {
        MST mst = new LazyPrimMST(getGraph());

        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weight());
    }

    @Test
    public void testKruskalMST() {
        MST mst = new KruskalMST(getGraph());

        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weight());
    }
}
