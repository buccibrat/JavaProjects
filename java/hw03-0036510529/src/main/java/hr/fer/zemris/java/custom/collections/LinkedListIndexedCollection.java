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
public class LinkedListIndexedCollection implements List {

	/**
	 * Represents one node of the class with three fields.
	 */
	private static class ListNode {
		/**
		 * Value that node stores.
		 */
		Object value;
		/**
		 * Pointer to next node.
		 */
		ListNode next;
		/**
		 * Pointer to previous node.
		 */
		ListNode previous;

		/**
		 * Constructor for node.
		 * 
		 * @param value value that is stored in the node
		 */
		public ListNode(Object value) {
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
	private ListNode first;
	/**
	 * Pointer to the last element of collection.
	 */
	private ListNode last;

	/**
	 * Number of modifications done over this collection.
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
	public LinkedListIndexedCollection(Collection collection) {
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
	public void add(Object value) {
		if (value == null) {
			throw new NullPointerException("Value can't be null");
		}

		ListNode node = new ListNode(value);
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
		ListNode node = goToIndex(index);
		if (size == 1) {
			first = last = null;
			return;
		}
		if (index == 0) {
			first = node.next;
			first.previous = null;
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
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		ListNode node = first;
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
	public Object get(int index) {
		return goToIndex(index).value;

	}

	/**
	 * inserts element at position if position isn't out of bounds. The average
	 * complexity of this method is n.
	 * 
	 * @param value
	 * @param position
	 */
	public void insert(Object value, int position) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		ListNode node = first;
		modificationCount++;
		if (position == 0) {
			ListNode newNode = new ListNode(value);
			newNode.next = node;
			node.previous = newNode;
			first = newNode;
			size++;
			return;
		}
		for (int i = 0; i < position - 1; i++) {
			node = node.next;
		}
		ListNode newNode = new ListNode(value);
		newNode.next = node.next;
		node.next.previous = newNode;
		node.next = newNode;
		newNode.previous = node;
		size++;
	}

	/**
	 * Returns element at index.
	 * 
	 * @param index index 
	 * @return element of collection
	 */
	private ListNode goToIndex(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		ListNode node = first;

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
		ListNode node = first;
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

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.collections.Collection#createElementsGetter()
	 */
	@Override
	public ElementsGetter createElementsGetter() {
		return new MyElementsGetter(this);
	}
	/**
	 * Implementation of ElementsGetter interface.
	 */
	private static class MyElementsGetter implements ElementsGetter {
		/**
		 * Index of next element.
		 */
		private int index;
		/**
		 * Collection that is being iterated.
		 */
		private LinkedListIndexedCollection collection;
		/**
		 * ModificationCount at creation of this object.
		 */
		private Long savedModificationCount = 0L;
		/**
		 * Constructor that receives collection.
		 * 
		 * @param collection collection
		 */
		public MyElementsGetter(LinkedListIndexedCollection collection) {
			this.collection = collection;
			savedModificationCount = collection.modificationCount;
		}
		/**
		 * Checks if collection was modified and throws exception if it was. Checks if
		 * there is next element in collection.
		 */
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
		/**
		 * Returns next element of collection or throws exception if there is none.
		 */
		@Override
		public Object getNextElement() {
			if (!hasNextElement()) {
				throw new NoSuchElementException();
			}
			index++;
			return collection.get(index - 1);
		}

	}
}
