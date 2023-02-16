package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DictionaryTest {
	@Test
	public void constructorTest() {
		assertNotNull(new Dictionary<String, String>());
	}

	@Test
	public void isEmptyTest() {
		Dictionary<String, Integer> dictionary = new Dictionary<String, Integer>();
		assertTrue(dictionary.isEmpty());
		dictionary = initDictionary();
		assertFalse(dictionary.isEmpty());
	}

	@Test
	public void sizeTest() {
		Dictionary<String, Integer> dictionary = initDictionary();
		assertEquals(5, dictionary.size());
		dictionary.put("Jedan", 1);
		assertEquals(6, dictionary.size());
	}

	@Test
	public void clearTest() {
		Dictionary<String, Integer> dictionary = initDictionary();
		assertEquals(5, dictionary.size());
		dictionary.clear();
		assertEquals(0, dictionary.size());
	}

	@Test
	public void putTest() {
		Dictionary<String, Integer> dictionary = initDictionary();
		assertEquals(5, dictionary.size());
		assertEquals(27, dictionary.get("Štefica"));
	}
	
	@Test
	public void putAndGetTest() {
		Dictionary<String, Integer> dictionary = initDictionary();
		assertEquals(1, dictionary.get("Jank"));
		assertEquals(27, dictionary.get("Štefica"));
		assertEquals(null, dictionary.get("Ništa"));
	}

	@Test
	private Dictionary<String, Integer> initDictionary() {
		Dictionary<String, Integer> dictionary = new Dictionary<String, Integer>();
		dictionary.put("Štefica", 27);
		dictionary.put("Jank", 1);
		dictionary.put("Milica", 36);
		dictionary.put("Trkatorka", 11);
		dictionary.put("LOL", 69);
		return dictionary;
	}
}
