package hr.fer.zemris.java.custom.collections;

/**
 * Class <code>Collection</code> represents general collection of objects
 * 
 * @author Benjamin Kušen
 *
 */
public interface Collection {

	/**
	 * Function that returns <code>true</code> if collection is empty,
	 * <code>false</code> otherwise
	 * 
	 * @return <code>true</code> if collection is empty, <code>false</code>
	 *         otherwise;
	 */
	default boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns size of the collection
	 * 
	 * @return <code>int</code> size of the collection
	 */
	public int size();

	/**
	 * Adds object to the collection
	 * 
	 * @param value object that is added to collection
	 */
	public void add(Object value);

	/**
	 * Returns <code>true</code> if object is contained within the collection,
	 * <code>false</code> otherwise
	 * 
	 * @param value object that is checked if it exists in the collection
	 * @return <code>true</code> if object is contained within the collection,
	 *         <code>false</code> otherwise
	 */
	public boolean contains(Object value);

	/**
	 * Removes one occurrence of object from the collection
	 * 
	 * @param value object that is removed from the collection
	 * @return <code>true</code> if object exists in collection, <code>false</code>
	 *         otherwise
	 */
	public boolean remove(Object value);

	/**
	 * Allocates new array with size equal to the size of this collection, fills it
	 * with collection content and returns the array. This method never returns
	 * null. Throws UnsupportedOperationException
	 * 
	 * @return new array of objects from his collection
	 */
	public Object[] toArray();

	/**
	 * Calls processor.process for each element of this collection
	 * 
	 * @param processor processor who's process method will be used on every element
	 *                  of the collection
	 */
	default void forEach(Processor processor) {
		ElementsGetter getter = this.createElementsGetter();
		while (getter.hasNextElement()) {
			processor.process(getter.getNextElement());
		}
	}

	/**
	 * Method adds into the current collection all elements from the given
	 * collection. This other collection remains unchanged.
	 * 
	 * @param other
	 */
	default void addAll(Collection other) {
		other.forEach(element -> this.add(element));
	}

	/**
	 * Removes all elements from this collection
	 */
	public void clear();

	/**
	 * Returns object that returns one element of collection at a time.
	 * 
	 * @return object that returns one element of collection at a time
	 */
	public ElementsGetter createElementsGetter();

	/**
	 * Adds all elements from collection col to this collection for which tester
	 * returned true.
	 * 
	 * @param col    collection from which elements are being added
	 * @param tester condition for which elements added
	 */
	default void addAllSatisfying(Collection col, Tester tester) {
		if (col == null || tester == null) {
			throw new NullPointerException();
		}
		ElementsGetter getter = col.createElementsGetter();
		Object element;
		while (getter.hasNextElement()) {
			element = getter.getNextElement();
			if (tester.test(element)) {
				this.add(element);
			}
		}
	}
}
