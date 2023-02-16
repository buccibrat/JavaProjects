package hr.fer.zemris.java.custom.collections;

/**
 * Class <code>Collection</code> represents general collection of objects
 * 
 * @author Benjamin Kušen
 *
 */
public class Collection {

    /**
     * Default constructor
     */
    protected Collection() {
    }

    /**
     * Function that returns <code>true</code> if collection is empty,
     * <code>false</code> otherwise
     * 
     * @return <code>true</code> if collection is empty, <code>false</code>
     *         otherwise;
     */
    public boolean isEmpty() {
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
    public int size() {
	return 0;
    }

    /**
     * Adds object to the collection
     * 
     * @param value object that is added to collection
     */
    public void add(Object value) {

    }

    /**
     * Returns <code>true</code> if object is contained within the collection,
     * <code>false</code> otherwise
     * 
     * @param value object that is checked if it exists in the collection
     * @return <code>true</code> if object is contained within the collection,
     *         <code>false</code> otherwise
     */
    public boolean contains(Object value) {
	return false;
    }

    /**
     * Removes one occurrence of object from the collection
     * 
     * @param value object that is removed from the collection
     * @return <code>true</code> if object exists in collection, <code>false</code>
     *         otherwise
     */
    public boolean remove(Object value) {
	return false;
    }

    /**
     * Allocates new array with size equal to the size of this collection, fills it
     * with collection content and returns the array. This method never returns
     * null. Throws UnsupportedOperationException
     * 
     * @return new array of objects from his collection
     */
    public Object[] toArray() {
	throw new UnsupportedOperationException();
    }

    /**
     * Calls processor.process for each element of this collection
     * 
     * @param processor processor whos process method will be used on every element
     *                  of the collection
     */
    public void forEach(Processor processor) {

    }

    /**
     * Method adds into the current collection all elements from the given
     * collection. This other collection remains unchanged.
     * 
     * @param other
     */
    public void addAll(Collection other) {
	/**
	 * Class that extends Processor
	 */
	class MyProcessor extends Processor {
	    Collection collection;

	    /**
	     * Default constructor
	     */
	    private MyProcessor(Collection collection) {
		super();
		this.collection = collection;
	    }

	    /**
	     * uses Collection.add method to add an object to the collection
	     */
	    @Override
	    public void process(Object value) {
		collection.add(value);

	    }
	}
	other.forEach(new MyProcessor(this));
    }

    /**
     * Removes all elements from this collection
     */
    public void clear() {

    }

}
