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
public class ArrayIndexedCollection<E> implements List<E> {

	/**
	 * default size of collection
	 */
	private static final int DEFAULT_SIZE = 16;
	/**
	 * size of collection
	 */
	private int size;
	/**
	 * Array of elemens that are contained within collection
	 */
	private E[] elements;

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
	@SuppressWarnings("unchecked")
	public ArrayIndexedCollection(int initialCapacity) {

		if (initialCapacity < 1) {
			throw new IllegalArgumentException();
		}

		elements = (E[]) new Object[initialCapacity];
	}

	public ArrayIndexedCollection(Collection<? extends E> collection) {
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
	@SuppressWarnings("unchecked")
	public ArrayIndexedCollection(Collection<? extends E> collection, int initialCapacity) {
		this(initialCapacity);

		if (collection == null) {
			throw new NullPointerException();
		}

		if (initialCapacity < collection.size()) {
			elements = (E[]) new Object[collection.size()];

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
	 * The average complexity of this method is 1.
	 */
	@Override
	public void add(E value) {
		if (value == null) {
			throw new NullPointerException("The value can't be null");
		}

		if (size == elements.length) {
			elements = Arrays.copyOf(elements, elements.length * 2);
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
	 * Returns object at given index. Average complexity of this method is 1.
	 * 
	 * @param index index of element that is to be returned
	 * @return Object at index
	 */

	public E get(int index) {
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
		size = 0;
	}

	/**
	 * Inserts object at position in collection while shifting element at the
	 * position and all others after that element for one position higher. Average
	 * complexity of this method is n.
	 * 
	 * @param value    object that is added to the list
	 * @param position position at which object is added
	 */
	public void insert(E value, int position) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 0) {
			elements[position] = value;
			modificationCount++; 
			return;
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
	 * Removes object at index.
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
	public E[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ElementsGetter<E> createElementsGetter() {
		return new MyElementsGetter(this);
	}

	private static class MyElementsGetter<T> implements ElementsGetter<T> { 
		private int index;
		private ArrayIndexedCollection<T> collection;
		private Long savedModificationCount = 0L;

		public MyElementsGetter(ArrayIndexedCollection<T> collection) { 
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
