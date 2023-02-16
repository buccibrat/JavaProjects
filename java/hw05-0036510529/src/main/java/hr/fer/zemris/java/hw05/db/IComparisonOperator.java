package hr.fer.zemris.java.hw05.db;

/**
 * Defines a strategy for comparison operator.
 * 
 * @author Benjamin Kušen
 *
 */
public interface IComparisonOperator {
	/**
	 * Compares two arguments and returns true if the result of comparison is within
	 * the condition of comparison operator, false otherwise
	 * 
	 * @param value1
	 * @param value2
	 * @return true if arguments meet the condition of comparison operator
	 */
	public boolean satisfied(String value1, String value2);
}
