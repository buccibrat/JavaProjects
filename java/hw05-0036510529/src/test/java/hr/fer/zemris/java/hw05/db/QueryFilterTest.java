package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class QueryFilterTest {
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
