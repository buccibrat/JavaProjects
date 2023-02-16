package hr.fer.zemris.java.hw07.observer1;

/**
 * Writes to the standard output value stored in IntegerStorage multiplied by
 * two. Through constructor receives number times this class should be activated
 * before it removes itself from IntegerSotrages sorage of observers.
 * 
 * @author korisnik
 *
 */
public class DoubleValue implements IntegerStorageObserver {
	int n;

	public DoubleValue(int n) {
		this.n = n;
	}

	@Override
	public void valueChanged(IntegerStorage istorage) {
		if (n == 0) {
			istorage.removeObserver(this);
		} else {
			int value = istorage.getValue();
			System.out.println("Double value: " + 2 * value);
			n--;
		}
	}
}
