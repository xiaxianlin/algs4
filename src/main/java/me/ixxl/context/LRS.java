package me.ixxl.context;

import edu.princeton.cs.algs4.In;

public class LRS {
    public static void run(In in) {
        String text = in.readAll();
        int N = text.length();
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                lrs = sa.select(i).substring(0, length);
            }
        }
        System.out.println(lrs);
    }
}
