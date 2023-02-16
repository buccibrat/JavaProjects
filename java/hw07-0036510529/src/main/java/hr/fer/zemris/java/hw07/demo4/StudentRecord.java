package hr.fer.zemris.java.hw07.demo4;

/**
 * Student record that contains jmbag, first name, last name, number of points
 * scored on midterm exam, number of points scored on final exam, number of
 * points scored on laboratory practicum and grade.
 * 
 * @author Benjami Kušen
 *
 */
public class StudentRecord {
	/**
	 * JMBAG
	 */
	private String jmbag;
	/**
	 * Last name
	 */
	private String prezime;
	/**
	 * First name
	 */
	private String ime;
	/**
	 * number of points scored on midterm exam
	 */
	private double brojBodovaMI;
	/**
	 * number of points scored on final exam
	 */
	private double brojBodovaZI;
	/**
	 * number of points scored on laboratory practicum
	 */
	private double brojBodovaLab;
	/**
	 * Grade
	 */
	private int ocjena;
	/**
	 * Array of all informations.
	 */
	private String[] informations;

	/**
	 * Constructor that receives string with all information about student separated
	 * with one tab whitespace.
	 * 
	 * @param information
	 */
	public StudentRecord(String information) {
		String[] informations = information.split("\t");
		if (informations.length != 7) {
			return;
		}
		this.informations = informations;
		jmbag = informations[0];
		prezime = informations[1];
		ime = informations[2];
		brojBodovaMI = Double.parseDouble(informations[3]);
		brojBodovaZI = Double.parseDouble(informations[4]);
		brojBodovaLab = Double.parseDouble(informations[5]);
		ocjena = Integer.parseInt(informations[6]);
	}

	/**
	 * @return jmvag
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * @return last name
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * @return first name
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * @return number of points scored on midterm exam
	 */
	public double getBrojBodovaMI() {
		return brojBodovaMI;
	}

	/**
	 * @return number of points scored on final exam
	 */
	public double getBrojBodovaZI() {
		return brojBodovaZI;
	}

	/**
	 * @return number of points scored on laboratory practicum
	 */
	public double getBrojBodovaLab() {
		return brojBodovaLab;
	}

	/**
	 * @return grade
	 */
	public int getOcjena() {
		return ocjena;
	}

	/**
	 * Makes string of student information like the one passed to the constructor.
	 */
	public String toString() {
		StringBuilder bob = new StringBuilder();
		for (String s : informations) {
			bob.append(s);
			bob.append("\t");
		}
		bob.insert(bob.length() - 1, "\n");
		return bob.toString();
	}
}
