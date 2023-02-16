package hr.fer.zemris.java.hw05.db;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Holds database of all student records.
 * 
 * @author Benjamin Kušen
 *
 */
public class StudentDatabase {
	/**
	 * List of student records.
	 */
	List<StudentRecord> records;
	/**
	 * Map of student records. Key of map is jmbag, value is student record.
	 */
	Map<String, StudentRecord> mapOfRecords;

	/**
	 * Convets input into an list of student records.
	 * 
	 * @param input
	 */
	public StudentDatabase(List<String> input) {
		records = new LinkedList<StudentRecord>();
		mapOfRecords = new HashMap<>(input.size());
		for (String s : input) {
			String[] array = s.split("\t");
			StudentRecord student;

			checkGrade(array[3]);
			student = new StudentRecord(array[0], array[1], array[2], array[3]);

			if (mapOfRecords.containsKey(student.getJmbag())) {
				System.err.println("Same Jmbag is repeated more than once!");
				System.exit(-1);
			}
			records.add(student);
			mapOfRecords.put(student.getJmbag(), student);

		}
	}

	/**
	 * Returns StudentRecord for jmbag
	 * 
	 * @param jmbag
	 * @return StudentRecord
	 */
	public StudentRecord forJMBAG(String jmbag) {
		return mapOfRecords.get(jmbag);
	}

	/**
	 * Returns new list of student records from database that passed through filter
	 * 
	 * @param filter
	 * @return list of student records
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> list = new LinkedList<>();
		for (StudentRecord student : records) {
			if (filter.accepts(student)) {
				list.add(student);
			}
		}
		return list;
	}

	/**
	 * Checkes if grade is in range of 1 to 5 inclusive.
	 * 
	 * @param finalGrade
	 */
	private void checkGrade(String finalGrade) {
		try {
			int grade = Integer.parseInt(finalGrade);
			if (grade > 5 || grade < 1) {
				System.err.println("Jedna od ocjena nije u intervalu [1, 5]");
				System.exit(-1);
			}
		} catch (NumberFormatException e) {
			System.out.println("Ocjena nije ispravnog tipa");
		}
	}
}
