package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ConditionalExpressionTest {
	@Test
	public void test() {
		ConditionalExpression expr = new ConditionalExpression(
				 FieldValueGetters.LAST_NAME,
				 "Bos*",
				 ComparisonOperators.LIKE
				);
				StudentRecord record = new StudentRecord("0000000015", "Glavinić Pecotić", "Kristijan", "4");
				assertEquals("Glavinić Pecotić", expr.getFieldGetter().get(record));
				assertEquals("Bos*", expr.getStringLiteral());
	}
}
