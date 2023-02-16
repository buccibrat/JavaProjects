package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ValueWrapperTest {
	@Test
	public void addTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		v1.add(v2.getValue()); // v1 now stores Integer(0); v2 still stores null.
		assertEquals(0, v1.getValue());
		assertEquals(null, v2.getValue());
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.add(v4.getValue()); // v3 now stores Double(13); v4 still stores Integer(1).
		assertEquals(13.0, v3.getValue());
		assertEquals(Double.class, v3.getValue().getClass());
		assertEquals(1, v4.getValue());
		assertEquals(Integer.class, v4.getValue().getClass());
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper(Integer.valueOf(1));
		v5.add(v6.getValue()); // v5 now stores Integer(13); v6 still stores Integer(1).
		assertEquals(13, v5.getValue());
		assertEquals(Integer.class, v5.getValue().getClass());
		assertEquals(1, v6.getValue());
		assertEquals(Integer.class, v6.getValue().getClass());
		ValueWrapper v7 = new ValueWrapper("Ankica");
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		assertThrows(RuntimeException.class, () -> {v7.add(v8.getValue());}); // throws RuntimeException
	}
	
	@Test
	public void subTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		v1.subtract(v2.getValue()); 
		assertEquals(0, v1.getValue());
		assertEquals(null, v2.getValue());
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.subtract(v4.getValue()); 
		assertEquals(11.0, v3.getValue());
		assertEquals(Double.class, v3.getValue().getClass());
		assertEquals(1, v4.getValue());
		assertEquals(Integer.class, v4.getValue().getClass());
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper("1");
		v5.subtract(v6.getValue()); 
		assertEquals(11, v5.getValue());
		assertEquals(Integer.class, v5.getValue().getClass());
		assertEquals(1, v6.getValue());
		assertEquals(Integer.class, v6.getValue().getClass());
	}
	
	@Test
	public void mulTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		v1.multiply(v2.getValue()); 
		assertEquals(0, v1.getValue());
		assertEquals(null, v2.getValue());
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.multiply(v4.getValue()); 
		assertEquals(12.0, v3.getValue());
		assertEquals(Double.class, v3.getValue().getClass());
		assertEquals(1, v4.getValue());
		assertEquals(Integer.class, v4.getValue().getClass());
		ValueWrapper v5 = new ValueWrapper("5");
		ValueWrapper v6 = new ValueWrapper("5");
		v5.multiply(v6.getValue()); 
		assertEquals(25, v5.getValue());
		assertEquals(Integer.class, v5.getValue().getClass());
		assertEquals(5, v6.getValue());
		assertEquals(Integer.class, v6.getValue().getClass());
	}
	
	@Test
	public void divTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		v1.divide(v2.getValue()); 
		assertEquals(0, v1.getValue());
		assertEquals(null, v2.getValue());
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.divide(v4.getValue()); 
		assertEquals(12.0, v3.getValue());
		assertEquals(Double.class, v3.getValue().getClass());
		assertEquals(1, v4.getValue());
		assertEquals(Integer.class, v4.getValue().getClass());
		ValueWrapper v5 = new ValueWrapper("25");
		ValueWrapper v6 = new ValueWrapper("5");
		v5.divide(v6.getValue()); 
		assertEquals(5, v5.getValue());
		assertEquals(Integer.class, v5.getValue().getClass());
		assertEquals(5, v6.getValue());
		assertEquals(Integer.class, v6.getValue().getClass());
	}
	
	@Test
	public void compareTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		assertEquals(0, v1.numCompare(v2.getValue())); 
		assertEquals(null, v1.getValue());
		assertEquals(null, v2.getValue());
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		assertEquals(11, v3.numCompare(v4.getValue())); 
		assertEquals(12.0, v3.getValue());
		assertEquals(Double.class, v3.getValue().getClass());
		assertEquals(1, v4.getValue());
		assertEquals(Integer.class, v4.getValue().getClass());
		ValueWrapper v5 = new ValueWrapper("5");
		ValueWrapper v6 = new ValueWrapper("25");
		assertEquals(-20, v5.numCompare(v6.getValue())); 
		assertEquals(5, v5.getValue());
		assertEquals(Integer.class, v5.getValue().getClass());
		assertEquals(25, v6.getValue());
		assertEquals(Integer.class, v6.getValue().getClass());
	}
}
