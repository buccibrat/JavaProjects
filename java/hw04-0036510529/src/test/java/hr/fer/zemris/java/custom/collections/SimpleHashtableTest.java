package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimpleHashtableTest {
    
    @Test
    public void putGetTest() {
	SimpleHashtable<Integer, String> table = new SimpleHashtable<Integer, String>();
	table.put(5, "Štefica");
	table.put(2, "Joško");
	assertEquals("Štefica", table.get(5));
	table.put(5, "NijeŠtefica");
	assertEquals("NijeŠtefica", table.get(5));
    }
    
    @Test
    public void sizeTest() {
	SimpleHashtable<Integer, String> table = new SimpleHashtable<Integer, String>();
	table.put(5, "Štefica");
	table.put(2, "Joško");
	assertEquals(2, table.size());
	table.put(5, "NijeŠtefica");
	assertEquals(2, table.size());
	table.remove(5);
	assertEquals(1, table.size());
    }
    
    @Test
    public void removeContainsValueAndKeyTest() {
	SimpleHashtable<Integer, String> table = new SimpleHashtable<Integer, String>(1);
	table.put(1, "Štefica");
	table.put(2, "Joško");
	table.put(3, "NijeŠtefica");
	table.put(4, "NijeŠtefica");
	table.put(5, "NijeŠtefica");
	table.put(6, "NijeŠtefica");
	table.put(7, "NijeŠtefica");
	table.remove(7);
	assertEquals(6, table.size());
	table.remove(5);
	assertEquals(5, table.size());
	assertTrue(table.containsValue("Štefica"));
	assertFalse(table.containsValue("Ovo Nije Štefica"));
	assertTrue(table.containsKey(3));
	table.put(8, null);
	assertTrue(table.containsKey(8));
	assertFalse(table.containsKey(9));
    }
}
