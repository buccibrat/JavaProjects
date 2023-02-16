package hr.fer.zemris.java.hw05.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import hr.fer.zemris.java.hw05.db.lexer.QueryLexerException;

public class StudentDB {

	public static void main(String[] args) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("src/main/resources/database.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		Scanner sc = new Scanner(System.in);
		StudentDatabase database = new StudentDatabase(lines);
		System.out.printf("> ");
		String nextLine = sc.nextLine();
		while (!nextLine.equalsIgnoreCase("exit")) {
			if (nextLine.startsWith("query")) {
				try {
					QueryParser parser = new QueryParser(nextLine.substring(5));
					if (parser.isDirectQuery()) {
						StudentRecord r = database.forJMBAG(parser.getQueriedJMBAG());
						if (r != null) {
							System.out.println(tableTopBottom(r.getLastName().length(), r.getFirstName().length()));
							System.out.println(output(r, r.getLastName().length(), r.getFirstName().length()));
							System.out.println(tableTopBottom(r.getLastName().length(), r.getFirstName().length()));
							System.out.println("Records selected 1");
						} else {
							System.out.println("Records selected 0");
						}
					} else {
						int longestName = 0;
						int longestLastName = 0;
						List<StudentRecord> listOfRecords = new LinkedList<StudentRecord>();
						for (StudentRecord r : database.filter(new QueryFilter(parser.getQuery()))) {
							if (r.getFirstName().length() > longestName) {
								longestName = r.getFirstName().length();
							}
							if (r.getLastName().length() > longestLastName) {
								longestLastName = r.getLastName().length();
							}
							listOfRecords.add(r);
						}
						if (listOfRecords.size() != 0) {
							System.out.println(tableTopBottom(longestLastName, longestName));
							for (StudentRecord r : listOfRecords) {
								System.out.println(output(r, longestLastName, longestName));
							}
							System.out.println(tableTopBottom(longestLastName, longestName));
							System.out.println("Records selected " + listOfRecords.size());
						} else {
							System.out.println("Records selected 0");
						}
					}
				} catch (QueryParserException | QueryLexerException e) {
					System.out.println("Input is invalid!");
				}
			} else {
				System.out.println("Input is Invalid");
			}
			System.out.printf("> ");
			nextLine = sc.nextLine();
		}
		sc.close();
		System.out.println("Goodbye!");
	}

	/**
	 * Creates output for individual student record
	 * 
	 * @param record
	 * @param longestLastName
	 * @param LongestName
	 * @return
	 */
	private static String output(StudentRecord record, int longestLastName, int LongestName) {
		StringBuilder bob = new StringBuilder();
		bob.append("| " + record.getJmbag() + " | ");
		char[] array = record.getLastName().toCharArray();
		for (int i = 0; i < longestLastName; i++) {
			if (i < array.length) {
				bob.append(array[i]);
				continue;
			}
			bob.append(" ");
		}
		bob.append(" | ");
		array = record.getFirstName().toCharArray();
		for (int i = 0; i < LongestName; i++) {
			if (i < array.length) {
				bob.append(array[i]);
				continue;
			}
			bob.append(" ");
		}
		bob.append(" | ");
		bob.append(record.getFinalGrade() + " |");
		return bob.toString();
	}

	/**
	 * Creates output for top and bottom of ascii table.
	 * 
	 * @param longestLastName
	 * @param longestName
	 * @return
	 */
	private static String tableTopBottom(int longestLastName, int longestName) {
		StringBuilder bob = new StringBuilder();
		bob.append("+============+=");
		for (int i = 0; i < longestLastName; i++) {
			bob.append("=");
		}
		bob.append("=+=");
		for (int i = 0; i < longestName; i++) {
			bob.append("=");
		}
		bob.append("=+===+");
		return bob.toString();
	}
}
