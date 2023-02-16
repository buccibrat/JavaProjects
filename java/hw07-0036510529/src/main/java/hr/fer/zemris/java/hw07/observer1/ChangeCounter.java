package hr.fer.zemris.java.hw07.observer1;

/**
 * Observer that counts (and writes to the standard output) the number of times
 * the value stored has been changed since this observer’s registration.
 * 
 * @author Benjamin Kušen
 *
 */
public class ChangeCounter implements IntegerStorageObserver {
	/**
	 * Used for storing change counter
	 */
	private int change = 0;

	public ChangeCounter() {

	}

	/**
	 * Counts changes and writes out number of changes.
	 */
	@Override
	public void valueChanged(IntegerStorage istorage) {
		change++;
		System.out.println("Number of value changes since tracking: " + change);
	}
}
