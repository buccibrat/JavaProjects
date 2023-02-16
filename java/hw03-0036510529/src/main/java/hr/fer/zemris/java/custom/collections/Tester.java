package hr.fer.zemris.java.custom.collections;

/**
 * Has one method that receives an object, and calls test on it.
 * 
 * @author Benjamin Kušen
 *
 */
public interface Tester {
	/**
	 * Tests object.
	 * 
	 * @param obj object
	 * @return true if object meets the condition, false otherwise.
	 */
	boolean test(Object obj);
}
