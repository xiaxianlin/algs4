package me.ixxl.util;

public class BinaryDump {
    public static void run(int width, BinaryIn in) {
        int cnt;
        for (cnt = 0; !in.isEmpty(); cnt++) {
            if (width == 0) {
                continue;
            }
            if (cnt != 0 && cnt % width == 0) {
                System.out.println();
            }
            if (in.readBoolean()) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        System.out.println();
        System.out.println(cnt + " bits");
    }
}
