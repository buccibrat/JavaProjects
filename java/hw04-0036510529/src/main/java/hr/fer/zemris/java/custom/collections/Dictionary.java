package hr.fer.zemris.java.custom.collections;


/**
 * Class that saves values with their key. Works like a map. The class isn't
 * very efficient, and is made for storing small amount of values.
 * 
 * @author Benjamin Kušen
 *
 * @param <K> - the type of key maintained by this map
 * @param <V> - the type of maped values
 */
public class Dictionary<K, V> {
	/**
	 * Collection used for storing instances of container.
	 */
	private ArrayIndexedCollection<Container> collection;

	/**
	 * Default constructor. Allocates memory for collection.
	 */
	public Dictionary() {
		collection = new ArrayIndexedCollection<Container>();
	}

	/**
	 * Returns <code>true</code> if dictionary is emoty, <code>false</code>
	 * otherwise.
	 * 
	 * @return <code>true</code> if dictionary is emoty, <code>false</code>
	 *         otherwise.
	 */
	public boolean isEmpty() {
		return collection.isEmpty();
	}

	/**
	 * Returns size of dictionary
	 * 
	 * @return size of dictionary
	 */
	public int size() {
		return collection.size();
	}

	/**
	 * Removes all objects from dictionary.
	 */
	public void clear() {
		collection.clear();
	}

	/**
	 * Associates the specified value with the specified key in this map. If there
	 * was some other value associated with the key, the new value overwrites it.
	 * 
	 * @param key   - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 */
	public void put(K key, V value) {
		ElementsGetter<Container> getter = collection.createElementsGetter();
		while (getter.hasNextElement()) {
			Container container = getter.getNextElement();
			if (container.key.equals(key)) {
				container.value = value;
			}
		}

		collection.add(new Container(key, value));
	}

	/**
	 * Returns value associated with the key.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or throws exception
	 *         if the value isn't contained in dictionary
	 */
	public V get(Object key) {
		if (key == null) {
			throw new NullPointerException();
		}

		ElementsGetter<Container> getter = collection.createElementsGetter();
		Container element;
		while (getter.hasNextElement()) {
			element = getter.getNextElement();
			if (element.key.equals(key)) {
				return element.value;
			}
		}
		return null;
	}

	/**
	 * Used for storing value with its respected key.
	 *
	 * @param <K> Key
	 * @param <V> Value
	 */
	private class Container { // jel bolje da je ovaj container bez <K, V>
		/**
		 * Key associated with the value.
		 */
		public K key;
		/**
		 * Value of the associated key.
		 */
		public V value;

		/**
		 * Receives key and value. If the key is null throws an exception.
		 * 
		 * @param key
		 * @param value
		 */
		public Container(K key, V value) {
			if (key == null) {
				throw new NullPointerException();
			}
			this.key = key;
			this.value = value;
		}

	}
}
