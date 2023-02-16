package hr.fer.zemris.java.hw07.observer2;

public class DoubleValue implements IntegerStorageObserver {
	int n;

	public DoubleValue(int n) {
		this.n = n;
	}

	@Override
	public void valueChanged(IntegerStorageChange istorageChange) {
		if (n == 0) {
			istorageChange.getIntegerStorage().removeObserver(this);
		} else {
			int value = istorageChange.getIntegerStorage().getValue();
			System.out.println("Double value: " + 2 * value);
			n--;
		}
	}
}
