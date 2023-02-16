package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple implementation of Hashtable similar to java.util Hashtable with less
 * methods.
 * 
 * @author BenjaminKušen
 * 
 * @param <K> Key
 * @param <V> Value
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>> {
	/**
	 * Default size of table.
	 */
	private final static int DEFAULT_LENGTH = 16;
	/**
	 * An array of all entries.
	 */
	private TableEntry<K, V>[] table;
	/**
	 * Number of elements in table.
	 */
	private int size;
	/**
	 * Number of how many times table has been modified.
	 */
	private int modificationCount;

	/**
	 * Default constructor. Allocates table with capacity of 16.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable() {
		table = (TableEntry<K, V>[]) new TableEntry[DEFAULT_LENGTH];
	}

	/**
	 * Allocates table with given capacity.
	 * 
	 * @param capacity capacity of table
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException();
		} else if (capacity == 1) {
			table = (TableEntry<K, V>[]) new TableEntry[capacity];
		} else if (capacity % 2 == 0) {
			table = (TableEntry<K, V>[]) new TableEntry[capacity];
		} else {
			int newCapacity = 2;
			while (newCapacity < capacity) {
				newCapacity *= 2;
			}
			table = (TableEntry<K, V>[]) new TableEntry[newCapacity];
		}
	}

	/**
	 * Adds new entry to table, if key already exists in the table then its value is
	 * overwritten.
	 * 
	 * @param key   key
	 * @param value value
	 */
	@SuppressWarnings("unchecked")
	public void put(K key, V value) {
		keyCheck(key);
		if (size < table.length * 0.75) {
			int hash = calculateHash(key, table.length);
			boolean modified = false;
			if (table[hash] == null) {
				table[hash] = new TableEntry<K, V>(key, value);
				size++;
				modificationCount++;
			} else {
				TableEntry<K, V> entry = table[hash];
				while (entry.next != null) {
					if (entry.getKey().equals(key)) {
						entry.setValue(value);
						modified = true;
						break;
					}
					entry = entry.next;
				}
				if (modified == false) {
					if (entry.getKey().equals(key)) {
						entry.setValue(value);
					} else {
						entry.next = new TableEntry<K, V>(key, value);
						size++;
						modificationCount++;
					}
				}

			}
		} else {
			TableEntry<K, V>[] array = hashToArray();
			table = (TableEntry<K, V>[]) new TableEntry[size * 2];
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					table[calculateHash(array[i].key, table.length)] = array[i];
				}
			}
			put(key, value);
		}

	}

	/**
	 * Returns value for key
	 * 
	 * @param key key
	 * @return value for key
	 */
	public V get(Object key) {
		return getEntry(key).getValue();
	}

	/**
	 * Returns number of elements in table.
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * Checks wheter the key is contained within the table or not.
	 * 
	 * @param key key whose existence within the table is checked
	 * @return <code>true</code> if key is contained within table,
	 *         <code>false</code> otherwise
	 */
	public boolean containsKey(Object key) {

		TableEntry<K, V> entry = table[calculateHash(key, table.length)];
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				return true;
			}
			entry = entry.next;
		}

		return false;
	}

	/**
	 * Retruns <code>true</code> if value is in table, <code>false</code> otherwise.
	 * 
	 * @param value value
	 * @return <code>true</code> if value is in table, <code>false</code> otherwise
	 */
	public boolean containsValue(Object value) {
		for (int i = 0; i < table.length; i++) {
			TableEntry<K, V> entry = table[i];
			while (entry != null) {
				if (entry.getValue().equals(value)) {
					return true;
				}
				entry = entry.next;
			}
		}
		return false;
	}

	/**
	 * Removes element of key if the key is in table.
	 * 
	 * @param key key
	 */
	public void remove(Object key) {
		modificationCount++;
		int hash = Math.abs(key.hashCode() % table.length);
		if (table[hash] == null) {
			return;
		} else if (table[hash].getKey().equals(key)) {
			table[hash] = table[hash].next;
			size--;
		} else {
			TableEntry<K, V> entry = table[hash];
			while (entry != null) {
				if (entry.next.next == null && entry.next.getKey().equals(key)) {
					entry.next = null;
					size--;
					break;
				} else if (entry.next.getKey().equals(key)) {
					entry = entry.next.next;
					size--;
					break;
				}
				entry = entry.next;
			}
		}
	}

	/**
	 * Returns <code>true</code> if table is empty, <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if table is empty, <code>false</code> otherwise
	 */
	public boolean isEmpty() {
		if (table.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Clears all elements from the table.
	 */
	public void clear() {
		modificationCount++;
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		size = 0;
	}

	/**
	 * Returns iterator of this table.
	 */
	@Override
	public Iterator<TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}

	/**
	 * Returns string that contains all entries of table. Returns them in format
	 * key=value.
	 */
	public String toString() {
		StringBuilder bob = new StringBuilder();
		TableEntry<K, V> entry;
		for (int i = 0; i < table.length; i++) {
			bob.append("[");
			entry = table[i];
			while (entry != null) {
				bob.append(entry.getKey().toString());
				bob.append("=");
				bob.append(entry.getValue().toString());
			}
			bob.append("]\n");
		}
		return bob.toString();
	}

	/**
	 * Returns entry of table for given key.
	 * 
	 * @param key key
	 * @return entry with given key
	 */
	private TableEntry<K, V> getEntry(Object key) {
		int hash = Math.abs(key.hashCode() % table.length);
		if (table[hash] == null) {
			return null;
		} else {
			TableEntry<K, V> entry = table[hash];
			while (entry != null) {
				if (entry.getKey().equals(key)) {
					return entry;
				}
				entry = entry.next;
			}
			return null;
		}
	}

	/**
	 * Calculates hashcode of a key for given size of collection.
	 * 
	 * @param key  key
	 * @param size size of hashtable for which it calculates hashCode
	 * @return
	 */
	private int calculateHash(Object key, int size) {
		return Math.abs(key.hashCode() % size);
	}

	/**
	 * Creates new array of TableEntries that is the size of table and adds them to
	 * the array.
	 * 
	 * @return array of all of TableEntries from Hashtable
	 */
	@SuppressWarnings("unchecked")
	private TableEntry<K, V>[] hashToArray() {
		int arrayIndex = 0;
		TableEntry<K, V> entry;
		TableEntry<K, V>[] array = (TableEntry<K, V>[]) new TableEntry[table.length];
		for (int i = 0; i < table.length; i++) {
			entry = table[i];
			while (entry != null) {
				array[arrayIndex] = entry;
				arrayIndex++;
				entry = entry.next;
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				array[i].next = null;
			}
		}
		return array;
	}

	/**
	 * Checks if key is null;
	 * 
	 * @param key
	 */
	private void keyCheck(Object key) {
		if (key == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Iterator for SimpleHashtable.
	 *
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>> {

		private int tableSize;
		private int size;
		private int index;
		private TableEntry<K, V> currentEntry;
		private int modificationCount;

		public IteratorImpl() {
			modificationCount = SimpleHashtable.this.modificationCount;
			tableSize = SimpleHashtable.this.size;
		}

		public boolean hasNext() {
			if(modificationCount != SimpleHashtable.this.modificationCount) {
				throw new ConcurrentModificationException();
			}
			if (size >= tableSize) {
				return false;
			} else {
				return true;
			}
		}

		@SuppressWarnings("rawtypes")
		public SimpleHashtable.TableEntry next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {

				if (currentEntry != null && currentEntry.next != null) {
					if (currentEntry.next != null) {
						currentEntry = currentEntry.next;
						if (currentEntry.next == null) {
							index++;
						}
					}
				} else {
					for (int i = index; i < table.length; i++) {
						if (table[i] == null) {
							index++;
						} else {
							currentEntry = table[i];
							if (currentEntry.next == null) {
								index++;
							}
							break;
						}
					}
				}
			}
			size++;
			return currentEntry;
		}

		public void remove() {
			if(modificationCount != SimpleHashtable.this.modificationCount) {
				throw new ConcurrentModificationException();
			}
			modificationCount++;
			if (containsKey(currentEntry.key)) {
				SimpleHashtable.this.remove((Object) currentEntry.key);
			} else {
				throw new IllegalStateException();
			}
		}
	}

	/**
	 * Holds value of the given key and reference to the next element that has same
	 * hash value.
	 *
	 * @param <K> Type of key
	 * @param <V> Type of value
	 */
	public static class TableEntry<K, V> {
		/**
		 * key
		 */
		private K key;
		/**
		 * value
		 */
		private V value;

		/**
		 * Reference to the next element with same hash value.
		 */
		private TableEntry<K, V> next;

		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * Gets value of entry.
		 * 
		 * @return value
		 */
		public V getValue() {
			return value;
		}

		/**
		 * Sets Value of the entry.
		 * 
		 * @param value new value
		 */
		public void setValue(V value) {
			this.value = value;
		}

		/**
		 * Returns key of entry
		 * 
		 * @return key of entry
		 */
		public K getKey() {
			return key;
		}

	}

}
