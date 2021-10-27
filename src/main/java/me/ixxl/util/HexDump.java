package me.ixxl.util;

import edu.princeton.cs.algs4.StdOut;

public class HexDump {
    public static void run(BinaryIn in, int bytesPerLine) {
        int i;
        for (i = 0; !in.isEmpty(); i++) {
            if (bytesPerLine == 0) {
                in.readChar();
                continue;
            }
            if (i == 0)
                StdOut.printf("");
            else if (i % bytesPerLine == 0)
                StdOut.printf("\n", i);
            else
                StdOut.print(" ");
            char c = in.readChar();
            StdOut.printf("%02x", c & 0xff);
        }
        if (bytesPerLine != 0)
            StdOut.println();
        StdOut.println((i * 8) + " bits");
    }

    public static void run(BinaryIn in) {
        run(in, 16);
    }
}
