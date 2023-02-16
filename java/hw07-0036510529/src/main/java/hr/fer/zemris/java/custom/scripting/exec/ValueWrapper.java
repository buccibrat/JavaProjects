package hr.fer.zemris.java.custom.scripting.exec;

import java.util.function.BiFunction;

/**
 * Wrapper wraper for integer, double or string that when parsed is either of
 * two. If value is null it is interpreted as integer 0.
 * 
 * @author Benjamin Kušen
 *
 */
/**
 * @author korisnik
 *
 */
public class ValueWrapper {
	/**
	 * wrapped value
	 */
	private Object value;

	/**
	 * Default constructor.
	 * 
	 * @param value
	 */
	public ValueWrapper(Object value) {
		this.value = value;
		if (this.value instanceof String) {
			this.value = parseNumber(this.value);
		}
	}

	/**
	 * Returns value.
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets value.
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
		if (this.value instanceof String) {
			this.value = parseNumber(this.value);
		}
	}

	/**
	 * Adds incValue to the value of object. If both values are integer then sum
	 * will be saved as Integer. Otherwise sum will be saved as Double
	 * 
	 * @param incValue
	 */
	public void add(Object incValue) {
		if (incValue != null && !checkInstance(incValue)) {
			throw new RuntimeException();
		}

		operation(incValue, (n1, n2) -> n1 + n2);
	}

	/**
	 * Substracts decValue from value of object. If both values are integer then
	 * substraction will be saved as Integer. Otherwise substraction will be saved
	 * as Double
	 * 
	 * @param decValue
	 */
	public void subtract(Object decValue) {
		if (decValue != null && !checkInstance(decValue)) {
			throw new RuntimeException();
		}

		operation(decValue, (n1, n2) -> n1 - n2);
	}

	/**
	 * multiplies value of object by mulValue. If both values are integer then
	 * multiplication will be saved as Integer. Otherwise multiplication will be
	 * saved as Double
	 * 
	 * @param mulValue
	 */
	public void multiply(Object mulValue) {
		if (mulValue != null && !checkInstance(mulValue)) {
			throw new RuntimeException();
		}

		operation(mulValue, (n1, n2) -> n1 * n2);
	}

	/**
	 * Divides value of object by divValue. If both values are integer then division
	 * will be saved as Integer. Otherwise divison will be saved as Double
	 * 
	 * @param divValue
	 */
	public void divide(Object divValue) {
		if (divValue != null && !checkInstance(divValue)) {
			throw new RuntimeException();
		}

		operation(divValue, (n1, n2) -> n1 / n2); // Treba li se pazit da se ne dijeli s nulom?
	}

	/**
	 * Substracts withValue with value of object and returns the result of
	 * substraction.
	 * 
	 * @param withValue
	 * @return
	 */
	public int numCompare(Object withValue) {
		Object value = 0;
		Object otherValue = withValue;
		if (withValue != null && !checkInstance(withValue)) {
			throw new RuntimeException();
		}

		if (otherValue == null) {
			otherValue = 0;
		}
		if (otherValue instanceof String) {
			if (((String) otherValue).contains(".") || ((String) otherValue).contains("E")) {
				otherValue = Double.parseDouble((String) otherValue);
			} else {
				otherValue = Integer.parseInt((String) otherValue);
			}
		}
		if (this.value != null) {
			value = this.value;
		}

		return (int) (castToDouble(value) - castToDouble(otherValue));

	}

	/**
	 * Receives value that needs to do something to the value of object. Receives
	 * BiFunction that applies function to the value of object and received value.
	 * 
	 * @param outerValue
	 * @param consumer
	 */
	private void operation(Object outerValue, BiFunction<Double, Double, Double> consumer) {
		Object value = 0;
		Object otherValue = outerValue;
		if (otherValue == null) {
			otherValue = 0;
		}
		if (otherValue instanceof String) {
			otherValue = parseNumber(otherValue);
		}
		if (this.value != null) {
			value = this.value;
		}
		if (value instanceof Integer && otherValue instanceof Integer) {
			this.value = (int) (double) consumer.apply(((Integer) value).doubleValue(),
					((Integer) otherValue).doubleValue());
		} else {
			this.value = consumer.apply(castToDouble(value), castToDouble(otherValue));
		}
	}

	/**
	 * Parses String to double or integer
	 * 
	 * @param number
	 * @return
	 */
	private Object parseNumber(Object number) {
		if (((String) number).contains(".") || ((String) number).contains("E")) {
			try {
				number = Double.parseDouble((String) number);
			} catch (NumberFormatException e) {
				System.err.println("Unos nije valjan");
			}
		} else {
			try {
				number = Integer.parseInt((String) number);
			} catch (NumberFormatException e) {
				System.err.println("Unos nije valjan");
			}
		}
		return number;
	}

	/**
	 * Casts value to double.
	 * 
	 * @param number
	 * @return
	 */
	private double castToDouble(Object number) {
		if (number instanceof Double) {
			return (double) number;
		}
		return ((Integer) number).doubleValue();
	}

	/**
	 * Checks if value is instance of string, integer or double.
	 * 
	 * @param value
	 * @return
	 */
	private boolean checkInstance(Object value) {
		if (value instanceof String || value instanceof Integer || value instanceof Double) {
			return true;
		}
		return false;
	}

}
