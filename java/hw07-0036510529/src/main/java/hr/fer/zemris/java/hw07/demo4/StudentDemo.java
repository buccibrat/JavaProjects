package hr.fer.zemris.java.hw07.demo4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Demonstration of how stream api can be used
 * 
 * @author Benjamin Kušen
 *
 */
public class StudentDemo {
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("./studenti.txt"));
		} catch (IOException e) {
			System.err.println("File couldnt be accsesd");
		}
		List<StudentRecord> records = convert(lines);

		System.out.println("Zadatak 1");
		System.out.println("=========");
		System.out.println("Broj studenata koji imaju više od 25 bodova: " + vratiBodovaViseOd25(records));

		System.out.println("Zadatak 2");
		System.out.println("=========");
		System.out.println("Broj studenata koji su dobili ocjenu 5: " + vratiBrojOdlikasa(records));

		System.out.println("Zadatak 3");
		System.out.println("=========");
		System.out.println("Odlikaši koji su dobili ocjenu 5:");
		for (StudentRecord s : vratiListuOdlikasa(records)) {
			System.out.println(s.toString());
		}

		System.out.println("Zadatak 4");
		System.out.println("=========");
		System.out.println("Odlikači koji su dobili ocjenu 5 poredani po bodovima:");
		for (StudentRecord s : vratiSortiranuListuOdlikasa(records)) {
			System.out.println(s.toString());
		}

		System.out.println("Zadatak 5");
		System.out.println("=========");
		for (String s : vratiPopisNepolozenih(records)) {
			System.out.println(s);
		}

		System.out.println("Zadatak 6");
		System.out.println("=========");
		Map<Integer, List<StudentRecord>> map2 = razvrstajStudentePoOcjenama(records);
		for (Entry<Integer, List<StudentRecord>> e : map2.entrySet()) {
			for (StudentRecord s : e.getValue()) {
				System.out.println(e.getKey() + " => " + s.toString());
			}
		}

		System.out.println("Zadatak 7");
		System.out.println("=========");
		Map<Integer, Integer> map = vratiBrojStudenataPoOcjenama(records);
		for (Entry<Integer, Integer> i : map.entrySet()) {
			System.out.println(i.getKey() + " => " + i.getValue());
		}

		System.out.println("Zadatak 8");
		System.out.println("=========");
		for (Entry<Boolean, List<StudentRecord>> e : razvrstajProlazPad(records).entrySet()) {
			for (StudentRecord s : e.getValue()) {
				System.out.println(e.getKey() + " => " + s.toString());
			}
		}
	}

	/**
	 * Initializes list of student records.
	 * 
	 * @param list
	 * @return
	 */
	private static List<StudentRecord> convert(List<String> list) {
		List<StudentRecord> records = new LinkedList<>();
		for (String s : list) {
			if (s.split("\t").length == 7) {
				records.add(new StudentRecord(s));
			}
		}
		return records;
	}

	/**
	 * Returns number of students that have more than 25 overall points.
	 * 
	 * @param records
	 * @return
	 */
	private static long vratiBodovaViseOd25(List<StudentRecord> records) {
		return records.stream().filter(s -> s.getBrojBodovaMI() + s.getBrojBodovaLab() + s.getBrojBodovaZI() > 25)
				.count();
	}

	/**
	 * Returns number of students with grade 5.
	 * 
	 * @param records
	 * @return
	 */
	private static long vratiBrojOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(s -> s.getOcjena() == 5).count();
	}

	/**
	 * Returns list of studentrecords with grade 5.
	 * 
	 * @param records
	 * @return
	 */
	private static List<StudentRecord> vratiListuOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(s -> s.getOcjena() == 5).collect(Collectors.toList());
	}

	/**
	 * Returns list of studentrecords with grade 5 sorted by overall scored points.
	 * 
	 * @param records
	 * @return
	 */
	private static List<StudentRecord> vratiSortiranuListuOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(s -> s.getOcjena() == 5).sorted(new Comparator<StudentRecord>() {
			@Override
			public int compare(StudentRecord s1, StudentRecord s2) {
				Double d1 = s1.getBrojBodovaLab() + s1.getBrojBodovaMI() + s1.getBrojBodovaZI();
				Double d2 = s2.getBrojBodovaLab() + s2.getBrojBodovaMI() + s2.getBrojBodovaZI();
				return d1.compareTo(d2);
			}
		}).collect(Collectors.toList());
	}

	/**
	 * returns list of jmbags of students that didnt pass.
	 * 
	 * @param records
	 * @return
	 */
	private static List<String> vratiPopisNepolozenih(List<StudentRecord> records) {
		return records.stream().filter(s -> s.getOcjena() == 1).map(StudentRecord::getJmbag).sorted()
				.collect(Collectors.toList());
	}

	/**
	 * returns map studentRecords. Key of the map is grade of student.
	 * 
	 * @param records
	 * @return
	 */
	private static Map<Integer, List<StudentRecord>> razvrstajStudentePoOcjenama(List<StudentRecord> records) {
		return records.stream().collect(Collectors.groupingBy(StudentRecord::getOcjena));

	}

	/**
	 * Returns map of number of students that have a certain grade.
	 * 
	 * @param records
	 * @return
	 */
	private static Map<Integer, Integer> vratiBrojStudenataPoOcjenama(List<StudentRecord> records) {
		return records.stream().collect(Collectors.toMap(StudentRecord::getOcjena, (StudentRecord s) -> {
			return 1;
		}, (Integer a, Integer b) -> a + b));
	}

	/**
	 * Returns a map of students that has two keys, boolean true or false. Students
	 * that have passed have key true, others have false.
	 * 
	 * @param records
	 * @return
	 */
	private static Map<Boolean, List<StudentRecord>> razvrstajProlazPad(List<StudentRecord> records) {
		return records.stream().collect(Collectors.partitioningBy((StudentRecord s) -> s.getOcjena() > 1));
	}
}
