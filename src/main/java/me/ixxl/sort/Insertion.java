package me.ixxl.sort;

public class Insertion extends Sortable {
    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
