package me.ixxl.sp;

import edu.princeton.cs.algs4.In;

public class CPM {
    private static void run(String file) {
        In in = new In(file);
        int N = in.readInt();
        in.readLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * N + 2);
        int s = 2 * N, t = 2 * N + 1;
        for (int i = 0; i < N; i++) {
            String[] a = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i + N, t, 0.0));
            G.addEdge(new DirectedEdge(i, i + N, duration));

            for (int j = 1; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i + N, successor, 0.0));
            }
        }
        AcyclicSP sp = new AcyclicSP(G, s);
        System.out.println("Start times:");
        for (int i = 0; i < N; i++) {
            System.out.println(String.format("%4d: %5.1f", i, sp.distTo[i]));
        }
        System.out.println(String.format("Finish time: %5.1f", sp.distTo[t]));
    }

    public static void main(String[] args) {
        run("./assets/jobsPC.txt");
    }
}
