package me.ixxl.st;

import me.ixxl.interfaces.IST;

public class ST<Key, Value> implements IST<Key, Value> {

    public int size() {
        return 0;
    }

    public void put(Key key, Value val) {
    }

    public Value get(Key key) {
        return null;
    }

    public Iterable<Key> keys() {
        return null;
    }

    public void delete(Key key) {
        put(key, null);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

}
