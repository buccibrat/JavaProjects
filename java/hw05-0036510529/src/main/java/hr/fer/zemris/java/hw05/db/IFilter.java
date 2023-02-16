package hr.fer.zemris.java.hw05.db;
/**
 * Defines a strategy for filtering student records.
 * 
 * @author Benjamin Kušen
 *
 */
public interface IFilter {
	/**
	 * Returns true if student record meets the conditions of filter, false otherwise
	 * 
	 * @param record
	 * @return true if student record meets the conditions of filter, false otherwise
	 */
	public boolean accepts(StudentRecord record);
}
