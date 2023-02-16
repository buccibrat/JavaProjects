package hr.fer.zemris.java.hw05.db.lexer;

public enum TokenType {
	/**
	 * Attribute name
	 */
	ATTRIBUTE_NAME, 
	/**
	 * Accepted operators are  >, <, >=, <=, =, !=, LIKE.
	 */
	OPERATOR, 
	/**
	 * Begins with " and can't contain ". 
	 */
	STRING_LITERAL, 
	/**
	 * and operator
	 */
	LOGICAL_OPERATOR, // It would be better to only have operator, and when
	
																// parser gets operator it understands it as logical or
																// normal operator
	/**
	 * End of file.
	 */
	EOF;
}
