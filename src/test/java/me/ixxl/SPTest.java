package me.ixxl;

import org.junit.Test;

import edu.princeton.cs.algs4.In;
import me.ixxl.sp.AcyclicSP;
import me.ixxl.sp.BellmanFordSP;
import me.ixxl.sp.DijkstraSP;
import me.ixxl.sp.DirectedEdge;
import me.ixxl.sp.EdgeWeightedDigraph;
import me.ixxl.sp.SP;

public class SPTest {

    @Test
    public void testSP() {
        int s = 0;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("./assets/tinyEWG.txt"));
        SP sp = new DijkstraSP(G, s);

        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t)) {
                    System.out.print(e + " ");
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testAcyclicSP() {
        int s = 5;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("./assets/tinyEWDAG.txt"));
        SP sp = new AcyclicSP(G, s);

        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                System.out.print(String.format("%d to %d (%.2f): ", s, t, sp.distTo(t)));
                for (DirectedEdge e : sp.pathTo(t)) {
                    System.out.print(e + " ");
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testBellmanFordSP() {
        int s = 0;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("./assets/tinyEWDnc.txt"));
        SP sp = new BellmanFordSP(G, s);

        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                System.out.print(String.format("%d to %d (%.2f): ", s, t, sp.distTo(t)));
                for (DirectedEdge e : sp.pathTo(t)) {
                    System.out.print(e + " ");
                }
            }
            System.out.println();
        }
    }
}
