package me.ixxl.string;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private String pat;
    private long patHash;
    private int M;
    private long Q;
    private int R = 256;
    private long RM;

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 0; i < M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    public boolean check(String txt, int i) {
        for (int j = 0; j < M; j++)
            if (pat.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
    }

    public int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(txt, 0)) {
            return 0;
        }
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash) {
                if (check(txt, i - M + 1)) {
                    return i - M + 1;
                }
            }
        }
        return N;
    }

    public static void main(String[] args) {
        String txt = "AABRAACADABRAACAADABRA";
        String pat = "AACAA";

        RabinKarp kmp = new RabinKarp(pat);
        int offset = kmp.search(txt);
        System.out.println("txt: " + txt);
        System.out.print("pat: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
    }
}
