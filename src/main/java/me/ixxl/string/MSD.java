package me.ixxl.string;

import me.ixxl.sort.Insertion;

public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 1];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d);
        }
    }

    public static void main(String[] args) {
        String[] a = { "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "sells",
                "are", "surely", "seashells" };
        sort(a, 0, 14, 0);
        for (String i : a) {
            System.out.println(i);
        }
    }
}
