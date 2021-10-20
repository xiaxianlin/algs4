package me.ixxl.other;

import edu.princeton.cs.algs4.In;
import me.ixxl.search.RedBlackBST;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = 3;
        RedBlackBST<String, Integer> st = new RedBlackBST<>();

        In in = new In("./assets/test.txt");

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < minlen) {
                continue;
            }

            if (st.get(word) == null) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        System.out.println(max + ": " + st.get(max));
    }
}
