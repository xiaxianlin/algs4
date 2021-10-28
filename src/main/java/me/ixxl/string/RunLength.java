package me.ixxl.string;

import me.ixxl.util.BinaryIn;
import me.ixxl.util.BinaryOut;

public class RunLength {
    public static void compress(BinaryIn in, BinaryOut out) {
        char cnt = 0;
        boolean b, old = false;
        while (!in.isEmpty()) {
            b = in.readBoolean();
            if (b != old) {
                out.write(cnt);
                cnt = 0;
                old = !old;
            } else {
                if (cnt == 255) {
                    out.write(cnt);
                    cnt = 0;
                    out.write(cnt);
                }
            }
            cnt++;
        }
        out.write(cnt);
        out.close();
    }

    public static void expend(BinaryIn in, BinaryOut out) {
        boolean b = false;
        while (!in.isEmpty()) {
            char cnt = in.readChar();
            for (int i = 0; i < cnt; i++) {
                out.write(b);
                b = !b;
            }
            out.close();
        }
    }
}
