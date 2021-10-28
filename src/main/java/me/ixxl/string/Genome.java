package me.ixxl.string;

import me.ixxl.util.BinaryIn;
import me.ixxl.util.BinaryOut;
import me.ixxl.util.HexDump;

public class Genome {
    public static void compress() {
        Alphabet DNA = new Alphabet("ACTG");
        BinaryIn in = new BinaryIn("./assets/dna.txt");
        String s = in.readString();
        int N = s.length();
        BinaryOut out = new BinaryOut("./assets/dna.bit");
        out.write(N);
        for (int i = 0; i < N; i++) {
            int d = DNA.toIndex(s.charAt(i));
            out.write(d, DNA.lgR());
        }
        out.close();
    }

    public static void expand() {
        Alphabet DNA = new Alphabet("ACTG");
        int w = DNA.lgR();
        BinaryIn in = new BinaryIn("./assets/dna.bit");
        int N = in.readInt();
        BinaryOut out = new BinaryOut("./assets/dna.txt");
        for (int i = 0; i < N; i++) {
            char c = in.readChar(w);
            out.write(DNA.toChar(c));
        }
        out.close();
    }

    public static void main(String[] args) {
        compress();
        // expand();

        BinaryIn in = new BinaryIn("./assets/dna.bit");
        // BinaryDump.run(64, in);
        HexDump.run(in);

    }
}
