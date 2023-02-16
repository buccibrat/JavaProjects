package hr.fer.zemris.java.custom.collections;

/**
 * Interface is similar in function to the Iterator interface from java.util
 * 
 * @author Benjamin Kušen
 *
 */
public interface ElementsGetter {
	/**
	 * Checks if there is next element in collection.
	 * 
	 * @return true if next element exists, false otherwise.
	 */
	public boolean hasNextElement();

	/**
	 * Returns next element in collection.
	 * 
	 * @return next element in collection.
	 */
	public Object getNextElement();

	/**
	 * Calls processor for all elements after last element that was returned by
	 * {@code hasNextElement} method.
	 * 
	 * @param p processor.
	 */
	default void processRemaining(Processor p) {
		while (hasNextElement()) {
			p.process(getNextElement());
		}
	}
}
