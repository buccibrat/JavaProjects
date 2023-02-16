package hr.fer.zemris.java.hw07.observer1;

import java.util.ArrayList;
import java.util.List;

public class IntegerStorage {
	private int value;
	private List<IntegerStorageObserver> observers; // use ArrayList here!!!

	public IntegerStorage(int initialValue) {
		this.value = initialValue;
		observers = new ArrayList<>();
	}

	public void addObserver(IntegerStorageObserver observer) {
		if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public void removeObserver(IntegerStorageObserver observer) {
		observers.remove(observer);
	}

	public void clearObservers() {
		observers.clear();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		// Only if new value is different than the current value:
		if (this.value != value) {
			// Update current value
			this.value = value;
			// Notify all registered observers
			if (observers != null) {
//				List<IntegerStorageObserver> copyOfObservers = new ArrayList<IntegerStorageObserver>(observers);
//				for (IntegerStorageObserver observer : copyOfObservers) {
//					observer.valueChanged(this);
//				}
				int observersSize = observers.size(); 
				for(int i = 0; i < observersSize; i++) {
					observers.get(i).valueChanged(this);
					if(observersSize != observers.size()) {
						observersSize--;
						i--;
					}
				}
			}
		}
	}

}
