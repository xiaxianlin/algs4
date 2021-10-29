package me.ixxl.context;

import edu.princeton.cs.algs4.In;

public class KWIC {
    public static void run(In in, int context) {
        String text = in.readAll().replaceAll("\\s+", " ");
        int N = text.length();
        SuffixArray sa = new SuffixArray(text);
        while (in.hasNextLine()) {
            String q = in.readLine();
            for (int i = sa.rank(q); i < N && sa.select(i).startsWith(q); i++) {
                int from = Math.max(0, sa.index(i) - context);
                int to = Math.min(N - 1, from + q.length() + 2 * context);
                System.out.println(text.substring(from, to));
            }
            System.out.println();
        }
    }

}
