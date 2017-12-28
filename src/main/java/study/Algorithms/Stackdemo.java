package study.Algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stackdemo<Item> implements Iterable<Item> {
	private Node<Item> first ;
	private int n ;
	public Stackdemo(){
		first = null;
		n = 0;
	}
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	public Boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}

	public void push(Item item) {
		Node <Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	public Item pop() {
		if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}
	public Item peek() {
		if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		return first.item;
	}

	@Override
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		for (Item item : this) {
			stringBuffer.append(item);
			stringBuffer.append(' ');
		}
		return stringBuffer.toString();
	}
	private class ListIterator<Item> implements Iterator<Item>{

		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {

		Stackdemo stackdemo = new Stackdemo();
		System.out.println(stackdemo.isEmpty());
		stackdemo.push("1");
		stackdemo.push("2");
		stackdemo.push("3");
		stackdemo.push("4");
		stackdemo.push("5");
		stackdemo.push("6");
		System.out.println(stackdemo.toString());
		System.out.println(stackdemo.peek());
		System.out.println(stackdemo.pop());
		System.out.println(stackdemo.toString());
		Iterator<String> iterator = stackdemo.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
