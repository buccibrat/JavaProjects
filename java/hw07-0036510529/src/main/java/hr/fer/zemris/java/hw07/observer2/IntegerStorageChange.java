package hr.fer.zemris.java.hw07.observer2;

public class IntegerStorageChange {
	private IntegerStorage storage;
	private int previousValue;
	private int newValue;
	public IntegerStorageChange(IntegerStorage storage, int prevValue, int newValue) {
	 this.storage = storage;
	 this.previousValue = prevValue;
	 this.newValue = newValue;
	}
	
	public IntegerStorage getIntegerStorage() {
		return storage;
	}
	
	public int getPreviousValue() {
		return previousValue;
	}
	
	public int getNewValue() {
		return newValue;
	}
	
	
}
