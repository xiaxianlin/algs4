package me.ixxl.string;

public class Quick3String {
    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        }
        return -1;
    }

    private static void exch(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo)
            return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v)
                exch(a, lt++, i++);
            else if (t > v)
                exch(a, i, gt--);
            else
                i++;
        }
        sort(a, lo, lt - 1, d);
        if (v > -0)
            sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    public static void main(String[] args) {
        // String[] a = { "she", "sells", "seashells", "by", "the", "sea", "shore",
        // "the", "shells", "she", "sells", "are",
        String[] a = { "edu.princetion.cs", "com.apple", "edu.princetion.cs", "com.cnn", "com.google", "edu.uva.cs",
                "edu.princetion.cs", "edu.princetion.cs.www", "edu.uva.cs", "edu.uva.cs", "edu.uva.cs", "com.adobe",
                "edu.princetion.ee" };
        sort(a);
        for (String i : a) {
            System.out.println(i);
        }
    }
}
