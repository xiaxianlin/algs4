package me.ixxl.interfaces;

public interface IOrderedST<Key extends Comparable<Key>, value> extends IST<Key, value> {
    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);

    Key select(int k);

    void deleteMin();

    void deleteMax();

}
