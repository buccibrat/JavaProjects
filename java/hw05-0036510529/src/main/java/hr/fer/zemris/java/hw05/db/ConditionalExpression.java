package hr.fer.zemris.java.hw05.db;

/**
 * Combines stringLiteral with Implementations of {@code IFieldValueGetter} and {@code IComparisonOperatr} in one object. 
 * 
 * @author Benjamin Kušen
 *
 */
/**
 * @author korisnik
 *
 */
/**
 * @author korisnik
 *
 */
public class ConditionalExpression {
	/**
	 * Object that is instance of IFieldValueGetter
	 */
	private IFieldValueGetter fieldGetter;
	/**
	 * StringLiteral
	 */
	private String stringLiteral;
	/**
	 * Object that is instance of IComparisonOperator
	 */
	private IComparisonOperator comparisonOperator;

	/**
	 * Constructs ConditionalExpression from parametars.
	 * 
	 * @param fieldGetter
	 * @param stringLiteral
	 * @param comparisonOperator
	 */
	public ConditionalExpression(IFieldValueGetter fieldGetter, String stringLiteral,
			IComparisonOperator comparisonOperator) {
		super();
		this.fieldGetter = fieldGetter;
		this.stringLiteral = stringLiteral;
		this.comparisonOperator = comparisonOperator;
	}

	/**
	 * Returns IFieldValueGetter.
	 * 
	 * @return IFieldValueGetter.
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldGetter;
	}

	/**
	 * Returns StringLiteral
	 * 
	 * @return StringLiteral
	 */
	public String getStringLiteral() {
		return stringLiteral;
	}

	/**
	 * Returns ComparisonOperator.
	 * 
	 * @return ComparisonOperator
	 */
	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

}
