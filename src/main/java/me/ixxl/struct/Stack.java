package me.ixxl.struct;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

	private Node<Item> first;
	private int N;

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void push(Item item) {
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldFirst;
		N++;
	}

	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
}
