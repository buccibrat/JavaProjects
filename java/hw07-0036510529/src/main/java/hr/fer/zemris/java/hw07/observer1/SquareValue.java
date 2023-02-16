package hr.fer.zemris.java.hw07.observer1;

/**
 * write a square of the integer stored in the IntegerStorage to the standard
 * output (but the stored integer itself is not modified!)
 * 
 * @author Benjamin Kušen
 *
 */
public class SquareValue implements IntegerStorageObserver {
	public SquareValue() {

	}

	/**
	 * writes a square of integer stored in the IntegerStorage to the standrad output. 
	 * 
	 */
	@Override
	public void valueChanged(IntegerStorage istorage) {
		int value = istorage.getValue();
		System.out.printf("Provided new value: %d, square is %d\n", value, value * value);
	}
}
