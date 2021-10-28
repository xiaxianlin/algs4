package me.ixxl.string;

import me.ixxl.util.BinaryIn;
import me.ixxl.util.BinaryOut;
import me.ixxl.util.HexDump;

public class LZW {
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 12;

    public static void compress(BinaryIn in, BinaryOut out) {
        TST<Integer> st = new TST<>();
        String input = in.readString();

        for (int i = 0; i < R; i++) {
            st.put("" + (char) i, i);
        }

        int code = R + 1;

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);
            out.write(st.get(s), W);

            int t = s.length();
            if (t < input.length() && code < L) {
                st.put(input.substring(0, t + 1), code++);
            }
            input = input.substring(t);
        }
        out.write(R, W);
        out.close();
    }

    public static void expand(BinaryIn in, BinaryOut out) {
        String[] st = new String[L];
        int i;

        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";

        int codeword = in.readInt(W);
        if (codeword == R)
            return;
        String val = st[codeword];

        while (true) {
            out.write(val);
            codeword = in.readInt(W);
            if (codeword == R)
                break;
            String s = st[codeword];
            if (i == codeword)
                s = val + val.charAt(0);
            if (i < L)
                st[i++] = val + s.charAt(0);
            val = s;
        }
        out.close();
    }

    public static void main(String[] args) {
        // BinaryIn cin = new BinaryIn("./assets/abraLZW.txt");
        // BinaryOut cout = new BinaryOut("./assets/abraLZW.bin");
        // compress(cin, cout);
        // HexDump.run(new BinaryIn("./assets/abraLZW.bin"), 20);

        BinaryIn cin = new BinaryIn("./assets/abraLZW.bin");
        BinaryOut cout = new BinaryOut("./assets/abraLZW.txt");
        expand(cin, cout);
    }
}
