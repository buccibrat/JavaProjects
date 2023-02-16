package hr.fer.zemris.java.custom.collections;

/**
 * This class represents a linked list collection with indexed nodes of
 * collection.
 * 
 * @author Benjamin Kušen
 *
 */
public class LinkedListIndexedCollection extends Collection {

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
	 * Default constructor.
	 */
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
		} else {
			if (index == size - 1) {
				last = node.previous;
				last.next = null;
			} else {
				node.previous.next = node.next;
				node.next.previous = node;
			}
		}
		size--;
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
	 * @see
	 * hr.fer.zemris.java.custom.collections.Collection#forEach(hr.fer.zemris.java.
	 * custom.collections.Processor)
	 */
	@Override
	public void forEach(Processor processor) {
		ListNode node = first;
		while (node != null) {
			processor.process(node.value);
			node = node.next;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#clear()
	 */
	@Override
	public void clear() {
		first = last = null; // When nothing is pointing to the first node garbage collector collects it,
		// then collects the one to which first node was pointing and the process
		// continues
		size = 0;

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

		if (position == 0) {
			ListNode newNode = new ListNode(value);
			newNode.next = node;
			node.previous = newNode;
			first = newNode;
			size++;
			if (size == 1) { // If list was empty then last points to first
				last = first;
			}
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
}
