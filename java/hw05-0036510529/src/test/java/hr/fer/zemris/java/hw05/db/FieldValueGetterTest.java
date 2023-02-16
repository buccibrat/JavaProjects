package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FieldValueGetterTest {
	@Test
	public void getterTests() {
		StudentRecord record = new StudentRecord("0000000015", "Glavinić Pecotić", "Kristijan", "4");
		assertEquals("Kristijan", FieldValueGetters.FIRST_NAME.get(record));
		assertEquals("Glavinić Pecotić", FieldValueGetters.LAST_NAME.get(record));
		assertEquals("0000000015", FieldValueGetters.JMBAG.get(record));
	}
}
