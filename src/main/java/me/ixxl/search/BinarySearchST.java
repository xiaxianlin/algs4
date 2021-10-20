package me.ixxl.search;

import edu.princeton.cs.algs4.Queue;
import me.ixxl.interfaces.IOrderedST;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements IOrderedST<Key, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    private void resize(int max) {
        Key[] keyTemp = (Key[]) new Comparable[max];
        Value[] valTemp = (Value[]) new Object[max];
        for (int i = 0; i < N; i++) {
            keyTemp[i] = keys[i];
            valTemp[i] = vals[i];
        }
        keys = keyTemp;
        vals = valTemp;
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue((keys[rank(hi)]));
        }
        return q;
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public int rank(Key key) {
        return rank(key, false);
    }

    public int rank(Key key, boolean floor) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return floor ? hi : lo;
    }

    public void put(Key key, Value val) {
        if (N == keys.length) {
            resize(2 * N);
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        int i = rank(key);
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        keys[N - 1] = null;
        vals[N - 1] = null;
        N--;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key, true);
        return keys[i];
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }
}
