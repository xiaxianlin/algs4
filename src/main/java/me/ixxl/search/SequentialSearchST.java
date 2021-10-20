package me.ixxl.search;

import edu.princeton.cs.algs4.Stack;
import me.ixxl.interfaces.IST;
import me.ixxl.st.ST;

public class SequentialSearchST<Key, Value> extends ST<Key, Value> implements IST<Key, Value> {
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node first;
    private int N;

    public int size() {
        return N;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public void delete(Key key) {
        Node parent = null;
        Node current = null;
        for (Node x = first; x != null; x = x.next) {
            if (x.next != null && key.equals(x.next.key)) {
                parent = x;
            }
            if (key.equals(x.key)) {
                current = x;
            }
        }
        // 没查询到节点
        if (current == null) {
            return;
        }
        // 删除节点为头节点
        if (parent == null) {
            first = current.next;
        } else {
            parent.next = current.next;
        }
        N--;
    }

    public Iterable<Key> keys() {
        Stack<Key> q = new Stack<>();
        Node node = first;
        while (node != null) {
            q.push(node.key);
            node = node.next;
        }
        return q;
    }

}
