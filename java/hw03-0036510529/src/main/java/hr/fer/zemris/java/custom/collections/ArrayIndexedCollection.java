package hr.fer.zemris.java.custom.collections;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/*
 * PITANJA:
 * jel treba preurediti metode od sučelja list da rade preko ElementsGetter
 */

/**
 * Resizable collection of objects that extends Collection. Duplicate ellements
 * are allowed but null reference is not allowed
 * 
 * @author Benjamin Kušen
 *
 */
public class ArrayIndexedCollection implements List {

	/**
	 * default size of collection
	 */
	private static final int DEFAULT_SIZE = 16;

	/**
	 * Number for which current size is multiplied.
	 */
	private static final int SIZE_MULTIPLIER = 2;
	/**
	 * size of collection
	 */
	private int size;
	/**
	 * Array of elemens that are contained within collection
	 */
	private Object[] elements;
	/**
	 * number of times that collection has been modified.
	 */
	private Long modificationCount = 0L;

	/**
	 * Default constructor that sets capacity of collection to the default value;
	 */
	public ArrayIndexedCollection() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Constructor that sets capacity to the initialCapacity. If initialCapacity is
	 * less than 1 throws IllegalArgumentException
	 * 
	 * @param initialCapacity capacity to which capacity of collection is set
	 */
	public ArrayIndexedCollection(int initialCapacity) { // popravit

		if (initialCapacity < 1) {
			throw new IllegalArgumentException();
		}

		elements = new Object[initialCapacity];
	}

	public ArrayIndexedCollection(Collection collection) {
		this(collection, DEFAULT_SIZE);
	}

	/**
	 * Constructor that receives another collection and copies its elements to this
	 * collection if initialCapacity is smaller than collection received than this
	 * collection capacity is set to recieved collection size
	 * 
	 * @param collection      collection whose elements are copied to this
	 *                        collection
	 * @param initialCapacity capacity to which capacity of collection is set
	 */
	public ArrayIndexedCollection(Collection collection, int initialCapacity) {
		this(initialCapacity);

		if (collection == null) {
			throw new NullPointerException();
		}

		if (initialCapacity < collection.size()) {
			elements = new Object[collection.size()];

		}
		addAll(collection);
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * returns how many elements can collection hold
	 * 
	 * @return how many elements can collection hold
	 */
	public int getCapacity() {
		return elements.length;
	}

	/**
	 * Adds new object at the end of collection. If collection is full allocates
	 * more memory. The average complexity of this method is 1.
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new NullPointerException("The value can't be null");
		}

		if (size == elements.length) {
			elements = Arrays.copyOf(elements, elements.length * SIZE_MULTIPLIER);
		}
		elements[size] = value;
		size++;
		modificationCount++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.fer.zemris.java.custom.collections.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object value) {
		return -1 != indexOf(value);
	}

	/**
	 * Returns object at given index. Average complexity of this method is O(1).
	 * 
	 * @param index index of element that is to be returned
	 * @return Object at index
	 */

	public Object get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		return elements[index];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#clear()
	 */
	@Override
	public void clear() {
		forEach(element -> element = null);
		modificationCount++;
	}

	/**
	 * Inserts object at position in collection while shifting element at the
	 * position and all others after that element for one position higher. Average
	 * complexity of this method is n.
	 * 
	 * @param value    object that is added to the list
	 * @param position position at which object is added
	 */
	public void insert(Object value, int position) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}

		add(elements[size - 1]);
		for (int i = size - 2; i > position; i--) {
			elements[i] = elements[i - 1];
		}
		elements[position] = value;
		modificationCount++;
	}

	/**
	 * Returns index of one instance of object in collection. Average complexity of
	 * this method is n.
	 * 
	 * @param value object who's index is returned
	 * @return index of object
	 */
	public int indexOf(Object value) {
		if (value == null) {
			return -1;
		}

		for (int i = 0; i < size; i++) {
			if (elements[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Removes element at index and shifts surrounding elements accordingly.
	 * 
	 * @param index index of element should be removed
	 */
	public void remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		size--;
		modificationCount++;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.custom.collections.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	/*
	 * (non-Javadoc)
	 * 
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
		private ArrayIndexedCollection collection;
		/**
		 * ModificationCount at creation of this object.
		 */
		private Long savedModificationCount = 0L;

		/**
		 * Constructor that receives collection.
		 * 
		 * @param collection collection
		 */
		public MyElementsGetter(ArrayIndexedCollection collection) {
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
