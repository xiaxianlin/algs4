package me.ixxl;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;
import me.ixxl.interfaces.IUF;
import me.ixxl.uf.QuickFindUF;

public class UFTest {
    @Test
    public void testQuickFindUF() {
        int N = 10;
        int[][] data = { { 4, 3 }, { 3, 8 }, { 6, 5 }, { 9, 4 }, { 2, 1 }, { 5, 0 }, { 7, 2 }, { 6, 1 } };
        IUF uf = new QuickFindUF(N);
        for (int i = 0; i < data.length; i++) {
            int p = data[i][0];
            int q = data[i][1];
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
