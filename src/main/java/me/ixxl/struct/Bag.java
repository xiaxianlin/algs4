package me.ixxl.struct;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

	private Node<Item> first;

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	public void add(Item item) {
		Node<Item> oldNode = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldNode;
	}

}
