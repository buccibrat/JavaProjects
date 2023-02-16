package hr.fer.zemris.java.custom.collections;

import hr.fer.zemris.java.custom.collections.EmptyStackException;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
/**
 * Class that has all functions of a stack
 * @author korisnik
 *
 */
public class ObjectStack {
    /**
     * Class uses internal <code>ArrayIndexedCOllection</code> for storing information.
     */
    private ArrayIndexedCollection collection;

    /**
     * Default constructor that allocates memory.
     */
    public ObjectStack() {
	collection = new ArrayIndexedCollection();
    }
    /**
     * Returns <code>true</code> if stack is empty, <code>false</code> otherwise.
     * @return <code>true</code> if stack is empty, <code>false</code> otherwise. 
     */
    public boolean isEmpty() {
	return collection.isEmpty();
    }
/**
 * Returns size of stack.
 * @return size of stack.
 */
    public int size() {
	return collection.size();
    }
/**
 * Adds value to the top of the stack.
 * @param value value that is added to the top of the stack.
 */
    public void push(Object value) {
	collection.add(value);
    }
/**
 * Removes the object at the top of the stack and then removes it.
 * @return object at the top of the stack
 */
    public Object pop() {
	emptyCheck();
	Object poped = collection.get(size() - 1);
	collection.remove(size() - 1);
	return poped;
    }
/**
 * Returns object at the top of the stack.
 * @return object at the top of the stack
 */
    public Object peak() {
	emptyCheck();
	return collection.get(size() - 1);
    }
/**
 * Empties out stack.
 */
    public void clear() {
	collection.clear();
    }

    private void emptyCheck() {
	if (isEmpty()) {
	    throw new EmptyStackException();
	}
    }
}
