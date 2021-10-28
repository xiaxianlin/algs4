package me.ixxl.string;

import edu.princeton.cs.algs4.MinPQ;
import me.ixxl.util.BinaryDump;
import me.ixxl.util.BinaryIn;
import me.ixxl.util.BinaryOut;

public class Huffman {
    private static int R = 256;

    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    private static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;

    }

    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<>();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.insert(new Node(c, freq[c], null, null));
            }
        }
        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    private static Node readTrie(BinaryIn in) {
        if (in.readBoolean()) {
            return new Node(in.readChar(), 0, null, null);
        }
        return new Node('\0', 0, readTrie(in), readTrie(in));
    }

    private static void writeTrie(Node x, BinaryOut out) {
        if (x.isLeaf()) {
            out.write(true);
            out.write(x.ch);
            return;
        }
        out.write(false);
        writeTrie(x.left, out);
        writeTrie(x.right, out);
    }

    public static void compress(BinaryIn in, BinaryOut out) {
        String s = in.readString();
        char[] input = s.toCharArray();
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        Node root = buildTrie(freq);
        String[] st = new String[R];
        buildCode(st, root, "");
        writeTrie(root, out);
        out.write(input.length);
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1') {
                    out.write(true);
                } else {
                    out.write(false);
                }
            }
        }
        out.close();
    }

    public static void expand(BinaryIn in, BinaryOut out) {
        Node root = readTrie(in);
        int N = in.readInt();
        for (int i = 0; i < N; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                if (in.readBoolean()) {
                    x = x.right;
                } else {
                    x = x.left;
                }
            }
            out.write(x.ch);
        }
        out.close();
    }

    public static void main(String[] args) {
        BinaryIn cin = new BinaryIn("./assets/abra.txt");
        BinaryOut cout = new BinaryOut("./assets/abra_out.txt");
        compress(cin, cout);

        BinaryDump.run(60, new BinaryIn("./assets/abra_out.txt"));
    }
}
