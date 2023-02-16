package hr.fer.zemris.java.hw05.db;
/**
 * Defines a strategy for retrieving attribute of student record.
 * 
 * @author Benjamin Kušen
 *
 */
public interface IFieldValueGetter {
	/**
	 * Retrieves attribute of student record.
	 * 
	 * @param record
	 * @return attribute of student record
	 */
	String get(StudentRecord record);
}
