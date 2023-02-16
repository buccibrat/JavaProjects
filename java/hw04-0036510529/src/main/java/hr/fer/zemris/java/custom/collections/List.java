package hr.fer.zemris.java.custom.collections;

/**
 * Interface that implements 4 methods for working with collections
 * 
 * @author Benjamin Kušen
 *
 */
public interface List<E> extends Collection<E> {
	/**
	 * returns object at an index
	 * 
	 * @param index index of object
	 * @return object
	 */
	E get(int index);

	/**
	 * Insert value at position in collection
	 * 
	 * @param value    value
	 * @param position position
	 */
	void insert(E value, int position);

	/**
	 * returns index of value
	 * 
	 * @param value value
	 * @return index of value
	 */
	int indexOf(Object value);

	/**
	 * Removes element on index from collection
	 * 
	 * @param index index
	 */
	void remove(int index);
}
