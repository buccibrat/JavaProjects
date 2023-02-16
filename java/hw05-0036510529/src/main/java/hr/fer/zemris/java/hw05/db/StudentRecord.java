package hr.fer.zemris.java.hw05.db;

import java.util.Objects;

/**
 * Holds information about a student.
 * 
 * @author Benjamin Kušen
 *
 */
public class StudentRecord {
	/**
	 * JMBAG of student.
	 */
	private String jmbag;
	/**
	 * Last name of student.
	 */
	private String lastName;
	/**
	 * First name of student.
	 */
	private String firstName;
	/**
	 * Students final grade.
	 */
	private String finalGrade;
	
	/**
	 * Constructs StudentRecord.
	 * 
	 * @param jmbag
	 * @param lastName
	 * @param firstName
	 * @param finalGrade
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, String finalGrade) {
		super();	
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
	}

	/**
	 * Returns jmbag.
	 * 
	 * @return jmbag
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * Returns last name.
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns first name.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns final grade.
	 * 
	 * @return finalGrade
	 */
	public String getFinalGrade() {
		return finalGrade;
	}

	/**
	 * returns hashcoed of jmbag.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(jmbag);
	}

	/**
	 * Two students are equal if their jmbag's are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentRecord))
			return false;
		StudentRecord other = (StudentRecord) obj;
		return Objects.equals(jmbag, other.jmbag);
	}
	
	
	
}
