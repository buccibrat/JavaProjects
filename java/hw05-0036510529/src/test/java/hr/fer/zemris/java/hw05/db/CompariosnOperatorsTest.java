package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CompariosnOperatorsTest {
	@Test
	public void lessOperator() {
		IComparisonOperator oper = ComparisonOperators.LESS;
		assertTrue(oper.satisfied("Ana", "Jasna"));		
		assertFalse(oper.satisfied("Jasna", "Ana"));		
	}
	
	@Test
	public void lessOrEqualsOperator() {
		IComparisonOperator oper = ComparisonOperators.LESS_OR_EQUALS;
		assertTrue(oper.satisfied("Ana", "Jasna"));		
		assertTrue(oper.satisfied("Ana", "Ana"));	
		assertFalse(oper.satisfied("Jasna", "Ana"));
	}
	
	@Test
	public void greaterOperator() {
		IComparisonOperator oper = ComparisonOperators.GREATER;
		assertFalse(oper.satisfied("Ana", "Jasna"));		
		assertFalse(oper.satisfied("Ana", "Ana"));	
		assertTrue(oper.satisfied("Jasna", "Ana"));
	}
	
	@Test
	public void greaterOrEqualsOperator() {
		IComparisonOperator oper = ComparisonOperators.GREATER_OR_EQUALS;
		assertFalse(oper.satisfied("Ana", "Jasna"));		
		assertTrue(oper.satisfied("Ana", "Ana"));	
		assertTrue(oper.satisfied("Jasna", "Ana"));
	}
	
	@Test
	public void equalsOperator() {
		IComparisonOperator oper = ComparisonOperators.EQUALS;
		assertFalse(oper.satisfied("Ana", "Jasna"));		
		assertTrue(oper.satisfied("Ana", "Ana"));	
		assertFalse(oper.satisfied("Jasna", "Ana"));
	}
	
	@Test
	public void notEqualsOperator() {
		IComparisonOperator oper = ComparisonOperators.NOT_EQUALS;
		assertTrue(oper.satisfied("Ana", "Jasna"));		
		assertFalse(oper.satisfied("Ana", "Ana"));	
		assertTrue(oper.satisfied("Jasna", "Ana"));
	}
	
	@Test
	public void likeOperator() {
		IComparisonOperator oper = ComparisonOperators.LIKE;
		assertFalse(oper.satisfied("Zagreb", "Aba*")); // false
		assertFalse(oper.satisfied("AAA", "AA*AA")); // false
		assertTrue(oper.satisfied("AAAA", "AA*AA")); // true
		assertTrue(oper.satisfied("AAA", "*"));
	}
}
