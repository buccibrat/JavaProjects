package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ObjectMultistackTest {
	@Test
	public void pushTest() {
		ObjectMultistack stack = new ObjectMultistack();
		stack.push("Štefica", new ValueWrapper(7));
		stack.push("Ivanka", new ValueWrapper(11));
		stack.push("Filip", new ValueWrapper(19));
		assertEquals(7, stack.peek("Štefica").getValue());
		stack.push("Štefica", new ValueWrapper(10));
		assertEquals(10, stack.peek("Štefica").getValue());
	}
	
	@Test
	public void popTest() {
		ObjectMultistack stack = new ObjectMultistack();
		stack.push("Štefica", new ValueWrapper(7));
		stack.push("Ivanka", new ValueWrapper(11));
		stack.push("Filip", new ValueWrapper(19));
		assertEquals(19, stack.pop("Filip").getValue());
		stack.push("Štefica", new ValueWrapper(8));
		assertEquals(8, stack.pop("Štefica").getValue());
		assertEquals(7, stack.pop("Štefica").getValue());
	}
	
	@Test
	public void peekTest() {
		ObjectMultistack stack = new ObjectMultistack();
		stack.push("Štefica", new ValueWrapper(7));
		stack.push("Ivanka", new ValueWrapper(11));
		stack.push("Filip", new ValueWrapper(19));
		assertEquals(7, stack.peek("Štefica").getValue());
		stack.push("Štefica", new ValueWrapper(21));
		assertEquals(21, stack.peek("Štefica").getValue());
	}
	
	@Test
	public void isEmpty() {
		ObjectMultistack stack = new ObjectMultistack();
		assertTrue(stack.isEmpty("Štefica"));
		stack.push("Štefica", new ValueWrapper(7));
		assertFalse(stack.isEmpty("Štefica"));
		stack.pop("Štefica");
		assertTrue(stack.isEmpty("Štefica"));
	}
}
