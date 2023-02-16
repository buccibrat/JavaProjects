package hr.fer.zemris.java.hw07.demo2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generates n number of prime numbers starting from 2. Generates them one by
 * one when asked.
 * 
 * @author korisnik
 *
 */
public class PrimesCollection implements Iterable<Integer> {
	/**
	 * Number of prime numbers to be generated.
	 */
	int numberOfGenerations;

	/**
	 * default constructor
	 * 
	 * @param numberOfGenerations
	 */
	public PrimesCollection(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
	}

	/**
	 * Internal iterator used for generating one prime number.
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new PrimesIterator();
	}

	private class PrimesIterator implements Iterator<Integer> {
		private int counter;
		private int lastPrime;

		public PrimesIterator() {
		}

		/**
		 * if number of generated numbers is smaller than number of numbers that need to
		 * be generated returns true, false otherwise.
		 */
		@Override
		public boolean hasNext() {
			if (counter < numberOfGenerations) {
				return true;
			}
			return false;
		}

		/**
		 * Generates next prime number.
		 */
		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (counter == 0) {
				lastPrime = 2;
				counter++;
				return 2;
			}
			if (counter == 1) {
				lastPrime = 3;
				counter++;
				return 3;
			}

			do {
				lastPrime++;
			} while (lastPrime % 2 == 0 || lastPrime % 3 == 0);
			counter++;
			return lastPrime;
		}
	}
}
