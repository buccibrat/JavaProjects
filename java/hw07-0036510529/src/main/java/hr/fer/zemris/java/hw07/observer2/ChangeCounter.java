package hr.fer.zemris.java.hw07.observer2;

public class ChangeCounter implements IntegerStorageObserver {
	private int change = 0;

	public ChangeCounter() {

	}

	@Override
	public void valueChanged(IntegerStorageChange istorageChange) {
		change++;
		System.out.println("Number of value changes since tracking: " + change);
	}
}
