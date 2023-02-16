package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class StudentDatabaseTest {

	@Test
	public void forJMBAGTest() {
		List<String> list = new LinkedList();
		list.add("0000000016	Glumac	Milan	5");
		list.add("0000000031	Krušelj Posavec	Bojan	4");
		list.add("0000000015	Glavinić Pecotić	Kristijan	4");

		StudentDatabase database = new StudentDatabase(list);
		assertNotNull(database);
		assertEquals(new StudentRecord("0000000016", "Glumac", "Milan", "5"), database.forJMBAG("0000000016"));
		assertEquals(new StudentRecord("0000000031", "Krušelj Posavec", "Bojan", "4"), database.forJMBAG("0000000031"));
		assertEquals(new StudentRecord("0000000015", "Nije", "Bitno", "1"), database.forJMBAG("0000000015"));
	}
	
	@Test
	public void filterTest() {
		List<String> list = new LinkedList();
		list.add("0000000016	Glumac	Milan	5");
		list.add("0000000031	Krušelj Posavec	Bojan	4");
		list.add("0000000015	Glavinić Pecotić	Kristijan	4");

		StudentDatabase database = new StudentDatabase(list);
		assertEquals(3, database.filter(new IFilterTrue()).size());
		assertEquals(0, database.filter(new IFilterFalse()).size());
		
	}
	
	
	private class IFilterTrue implements IFilter {
		public IFilterTrue() {
		}

		@Override
		public boolean accepts(StudentRecord recor) {
			return true;
		}

	}
	
	private class IFilterFalse implements IFilter {
		public IFilterFalse() {
		}

		@Override
		public boolean accepts(StudentRecord recor) {
			return false;
		}

	}
}
