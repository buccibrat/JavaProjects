package hr.fer.zemris.java.custom.collections;

/**
 * Class <code>Processor</code> does some operation with on the passed object
 * 
 * @author korisnik
 *
 */
public interface Processor<T> {

	/**
	 * Does something with object passed to it
	 * 
	 * @param value object passed to the <code>Processor</code>
	 */
	public void process(T value);

}
