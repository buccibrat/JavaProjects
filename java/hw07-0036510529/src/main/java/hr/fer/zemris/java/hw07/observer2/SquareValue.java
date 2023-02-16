package hr.fer.zemris.java.hw07.observer2;

public class SquareValue implements IntegerStorageObserver {
	public SquareValue() {

	}

	@Override
	public void valueChanged(IntegerStorageChange istorageChange) {
		int value = istorageChange.getNewValue();
		System.out.printf("Provided new value: %d, square is %d\n", istorageChange.getPreviousValue(), value * value);
	}
}
