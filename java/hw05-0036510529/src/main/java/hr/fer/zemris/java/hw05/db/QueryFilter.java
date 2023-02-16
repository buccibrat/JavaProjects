package hr.fer.zemris.java.hw05.db;

import java.util.List;

/**
 * Implementation IFilter. Receives a list of ConditionalExpressions and checks
 * if student record meets all of the conditions from list
 * 
 * @author Benjamin Kušen
 *
 */
public class QueryFilter implements IFilter {
	/**
	 * List of ConditionalExpression.
	 */
	private List<ConditionalExpression> list;

	/**
	 * Constructs queryFilter with list.
	 * 
	 * @param list of ConditionalExpressions
	 */
	public QueryFilter(List<ConditionalExpression> list) {
		this.list = list;
	}

	/**
	 * Checks if student record meets all of the conditions within list. If it does
	 * then returns true, returns false otherwise.
	 */
	@Override
	public boolean accepts(StudentRecord record) {
		for (ConditionalExpression expression : list) {
			if (!expression.getComparisonOperator().satisfied(expression.getFieldGetter().get(record),
					expression.getStringLiteral())) {
				return false;
			}
		}
		return true;
	}
}