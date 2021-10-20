package me.ixxl.search;

import edu.princeton.cs.algs4.Queue;
import me.ixxl.interfaces.IOrderedST;

public class RedBlackBST<Key extends Comparable<Key>, Value> implements IOrderedST<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private Node root;

    private boolean isRed(Node h) {
        return h == null ? false : h.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.N = h.N;
        h.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Value get(Node h, Key key) {
        if (h == null) {
            return null;
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            return get(h.left, key);
        } else if (cmp > 0) {
            return get(h.right, key);
        } else {
            return h.val;
        }
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, 0, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }

    private int size(Node h) {
        return h == null ? 0 : h.N;
    }

    private Node min(Node h) {
        if (h.left == null) {
            return h;
        }
        return min(h.left);
    }

    private Node max(Node h) {
        if (h.right == null) {
            return h;
        }
        return max(h.right);
    }

    private Node floor(Node h, Key key) {
        if (h == null) {
            return null;
        }
        int cmp = key.compareTo(h.key);
        if (cmp == 0) {
            return h;
        }
        if (cmp < 0) {
            return floor(h.left, key);
        }
        Node t = floor(h.right, key);
        return t != null ? t : h;
    }

    private Node ceiling(Node h, Key key) {
        if (h == null) {
            return null;
        }
        int cmp = key.compareTo(h.key);
        if (cmp == 0) {
            return h;
        }
        if (cmp > 0) {
            return ceiling(h.right, key);
        }
        Node t = ceiling(h.left, key);
        return t != null ? t : h;
    }

    private Node select(Node h, int k) {
        if (h == null) {
            return null;
        }
        int t = size(h.left);
        if (t > k) {
            return select(h.left, k);
        } else if (t < k) {
            return select(h.right, k - 1);
        } else {
            return h;
        }
    }

    private int rank(Node h, Key key) {
        if (h == null) {
            return 0;
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            return rank(h.left, key);
        } else if (cmp > 0) {
            return 1 + size(h.left) + rank(h.right, key);
        } else {
            return size(h.left);
        }
    }

    private void keys(Node h, Queue<Key> queue, Key lo, Key hi) {
        if (h == null) {
            return;
        }
        int cmplo = lo.compareTo(h.key);
        int cmphi = hi.compareTo(h.key);
        if (cmplo < 0) {
            keys(h.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi > -0) {
            queue.enqueue(h.key);
        }
        if (cmphi > 0) {
            keys(h.right, queue, lo, hi);
        }
    }

    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.right) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.right) && isRed(h.right)) {
            flipColors(h);
        }
        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (!isRed(h.left.left)) {
            h.right = rotateRight(h);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        h.left = deleteMax(h.right);
        return balance(h);
    }

    private Node delete(Node h, Key key) {
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (cmp == 0 && h.right == null) {
                return null;
            }
            if (cmp == 0) {
                Key k = min(h.right).key;
                h.val = get(h.right, k);
                h.key = k;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    public Key min() {
        return min(root).key;
    }

    public Key max() {
        return max(root).key;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }
}
