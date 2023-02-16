package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import hr.fer.zemris.java.custom.collections.ElementsGetter;;

/**
 * This class represents a linked list collection with indexed nodes of
 * collection.
 * 
 * @author Benjamin Kušen
 *
 */
public class LinkedListIndexedCollection<E> implements List<E> {

	/**
	 * Represents one node of the class with three fields.
	 */
	private static class ListNode<E> {
		/**
		 * Value that node stores.
		 */
		E value;
		/**
		 * Pointer to next node.
		 */
		ListNode<E> next;
		/**
		 * Pointer to previous node.
		 */
		ListNode<E> previous;

		/**
		 * Constructor for node.
		 * 
		 * @param value value that is stored in the node
		 */
		public ListNode(E value) {
			this.value = value;
		}
	}

	/**
	 * Size of collection
	 */
	private int size;
	/**
	 * Pointer to the first element of collection.
	 */
	private ListNode<E> first;
	/**
	 * Pointer to the last element of collection.
	 */
	private ListNode<E> last;

	/**
	 * Default constructor.
	 */

	private Long modificationCount = 0L;

	public LinkedListIndexedCollection() {
		super();
		size = 0;
		first = null;
		last = null;
	}

	/**
	 * Constructor that as a parametar accepts another collection and copies its
	 * contents.
	 * 
	 * @param collection another collection that is copied to this collection
	 */
	public LinkedListIndexedCollection(Collection<? extends E> collection) {
		this();
		if (collection == null) {
			throw new NullPointerException("Collection can't be null");
		}
		addAll(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#add(java.lang.Object)
	 */
	@Override
	public void add(E value) {
		if (value == null) {
			throw new NullPointerException("Value can't be null");
		}

		ListNode<E> node = new ListNode<E>(value);
		modificationCount++;
		size++;

		if (first == null && last == null) {
			first = node;
			last = node;
			return;
		}

		last.next = node;
		node.previous = last;
		last = node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.fer.zemris.java.custom.collections.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object value) {
		return indexOf(value) != -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.fer.zemris.java.custom.collections.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object value) {
		if (!contains(value)) {
			return false;
		}
		remove(indexOf(value));
		modificationCount++;
		return true;
	}

	/**
	 * Removes element on the index. throws <code>IndexOutOfBoundsException</code>
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> node = goToIndex(index);
		if (index == 0) {
			first = node.next;
			size--;

		} else {
			node.previous.next = node.next;
			size--;
		}

		modificationCount++;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#toArray()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		E[] array = (E[]) new Object[size];
		ListNode<E> node = first;
		int index = 0;
		while (node != null) {
			array[index] = node.value;
			node = node.next;
			index++;
		}

		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#clear()
	 */
	@Override
	public void clear() {
		first = last = null;
		size = 0;
		modificationCount++;
	}

	/**
	 * Returns element at index.
	 * 
	 * @param index index at which we want to return the element
	 * @return element at index
	 */
	public E get(int index) {
		return goToIndex(index).value;

	}

	/**
	 * inserts element at position if position isn't out of bounds. The average
	 * complexity of this method is n.
	 * 
	 * @param value
	 * @param position
	 */
	public void insert(E value, int position) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		ListNode<E> node = first;
		modificationCount++;
		if (position == 0) {
			ListNode<E> newNode = new ListNode<E>(value);
			newNode.next = node;
			node.previous = newNode;
			first = newNode;
			size++;
			return;
		}
		for (int i = 0; i < position - 1; i++) {
			node = node.next;
		}
		ListNode<E> newNode = new ListNode<E>(value);
		newNode.next = node.next;
		node.next.previous = newNode;
		node.next = newNode;
		newNode.previous = node;
		size++;
	}

	private ListNode<E> goToIndex(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		ListNode<E> node = first;

		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	/**
	 * Returns index of one instance of element in this collection. Average
	 * complexity of this method is n.
	 * 
	 * @param value
	 * @return index of one instance of element
	 */
	public int indexOf(Object value) {
		ListNode<E> node = first;
		int index = 0;
		while (node != null) {
			if (node.value.equals(value)) {
				return index;
			}
			node = node.next;
			index++;
		}
		return -1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ElementsGetter<E> createElementsGetter() {
		return new MyElementsGetter(this);
	}

	private static class MyElementsGetter<T> implements ElementsGetter<T> {

		private int index;
		private LinkedListIndexedCollection<T> collection;
		private Long savedModificationCount = 0L;

		public MyElementsGetter(LinkedListIndexedCollection<T> collection) {
			this.collection = collection;
			savedModificationCount = collection.modificationCount;
		}

		@Override
		public boolean hasNextElement() {
			if (savedModificationCount != collection.modificationCount) {
				throw new ConcurrentModificationException();
			}
			if (index < collection.size()) {
				return true;
			}
			return false;
		}

		@Override
		public T getNextElement() {
			if (!hasNextElement()) {
				throw new NoSuchElementException();
			}
			index++;
			return collection.get(index - 1);
		}

	}
}
