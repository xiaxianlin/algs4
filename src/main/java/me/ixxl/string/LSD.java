package me.ixxl.string;

public class LSD {
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = { "4PGC938", "2IYE230", "3CI0720", "1ICK750", "1OHV845", "4JZY524", "1ICK750", "3CI0720",
                "1OHV845", "1OHV835", "2RLA629", "2RLA629", "3ATW723" };
        sort(a, 7);
        for (String i : a) {
            System.out.println(i);
        }
    }
}
