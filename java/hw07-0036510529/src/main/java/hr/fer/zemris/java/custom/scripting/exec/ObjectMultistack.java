package hr.fer.zemris.java.custom.scripting.exec;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * Class that combines maps and stacks. Adds value to the key. There can be
 * multiple values on the key but only the top one can be accessed.
 * 
 * @author Benjamin Kušen
 *
 */
public class ObjectMultistack {
	/**
	 * Internal map used for storing values.
	 */
	private Map<String, MultimapEntry> map;
	/**
	 * Default constructor.
	 */
	public ObjectMultistack() {
		map = new HashMap<String, ObjectMultistack.MultimapEntry>();
	}
	/**
	 * Pushes value to the top of key.
	 * @param keyName
	 * @param valueWrapper
	 */
	public void push(String keyName, ValueWrapper valueWrapper) {
		Objects.requireNonNull(keyName);
		Objects.requireNonNull(valueWrapper);
		map.put(keyName, new MultimapEntry(valueWrapper, map.get(keyName)));
	}
	/**
	 * Returns top value on key and removes it from key.
	 * @param keyName
	 * @return value
	 */
	public ValueWrapper pop(String keyName) {
		if (map.get(keyName) == null) {
			throw new EmptyStackException();
		}
		ValueWrapper returnValue = map.get(keyName).valueWrapper;
		map.put(keyName, map.get(keyName).next);
		return returnValue;

	}
	/**
	 * Returns top value on key, doesnt remove value.
	 * @param keyName
	 * @return value
	 */
	public ValueWrapper peek(String keyName) {
		if (map.get(keyName) == null) {
			throw new EmptyStackException();
		}
		ValueWrapper returnValue = map.get(keyName).valueWrapper;
		return returnValue;
	}
	/**
	 * Returns true if there are no elements assigned to the key, false otherwise.
	 * @param keyName
	 * @return 
	 */
	public boolean isEmpty(String keyName) {
		return map.get(keyName) == null;
	}

	/**
	 * Class that has value stored on the key and pointer to the previous entry on
	 * the key.
	 * 
	 *
	 */
	private static class MultimapEntry {
		/**
		 * Value on the key
		 */
		private ValueWrapper valueWrapper;
		/**
		 * Pointer to the previous entry.
		 */
		private MultimapEntry next;
		/**
		 * Constructor
		 * @param valueWrapper
		 * @param next
		 */
		public MultimapEntry(ValueWrapper valueWrapper, MultimapEntry next) {
			this.valueWrapper = valueWrapper;
			this.next = next;
		}

	}
}
