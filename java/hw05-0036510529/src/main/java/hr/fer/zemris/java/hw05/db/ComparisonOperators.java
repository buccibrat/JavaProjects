package hr.fer.zemris.java.hw05.db;

import java.util.regex.Pattern;

/**
 * Class with all implementations of {@code IComparisonOperator} interface needed for parseing.
 * 
 * @author Benjamin Kušen
 *
 */
public class ComparisonOperators {
	
	/**
	 * If value1 is lesser than value2 returns true, returns false otherwise.
	 */
	public static final IComparisonOperator LESS = (value1, value2) -> value1.compareTo(value2) < 0;

	/**
	 * If value1 is lesser than or equal to value 2 returns true, returns false otherwise.
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = (value1, value2) -> value1.compareTo(value2) <= 0; 

	/**
	 * If value 1 is greater than value 2 returns true, returns false otherwise.
	 */
	public static final IComparisonOperator GREATER = (value1, value2) -> value1.compareTo(value2) > 0;

	/**
	 * If value 1 is greater than or equal to value 2 returns true, returns false othrwise
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = (value1, value2) -> value1.compareTo(value2) >= 0;
	
	/**
	 * If value1 is equal to value2 returns true, returns false otherwise.
	 */
	public static final IComparisonOperator EQUALS = (value1, value2) -> value1.equals(value2);

	/**
	 * If value1 is not equal to value2 returns true, returns false otherwise.
	 */
	public static final IComparisonOperator NOT_EQUALS = (value1, value2) -> !value1.equals(value2);

	/**
	 * If value1 is like value2 returns true, false otherwise. Value2 is regular expression. 
	 */
	public static final IComparisonOperator LIKE = (value1, value2) -> 	Pattern.matches(value2.replace("*", ".*"), value1); 
	
}
