package me.ixxl.sort;

public class Heap extends Sortable {

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a[j], a[j + 1])) {
                j++;
            }
            if (!less(a[k], a[j])) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    public static void main(String[] args) {
        String[] a = { "", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
        sort(a);
        show(a);
    }
}
