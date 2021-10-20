package me.ixxl.struct;

import java.util.Iterator;

public class ListIterator<Item> implements Iterator<Item> {
	private Node<Item> current;

	ListIterator(Node<Item> node) {
		current = node;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Item next() {
		Item item = current.item;
		current = current.next;
		return item;
	}

}
