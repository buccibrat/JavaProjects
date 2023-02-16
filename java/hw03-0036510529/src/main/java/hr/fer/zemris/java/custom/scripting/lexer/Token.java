package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * Represents one small segment of input
 * 
 * @author Benjamin Kušen
 *
 */
public class Token {
	/**
	 * Value of token
	 */
	Object value;
	/**
	 * type of token
	 */
	TokenType type;

	/**
	 * Sets value and type of token.
	 * 
	 * @param type
	 * @param value
	 */
	public Token(TokenType type, Object value) {
		this.value = value;
		this.type = type;
	}

	/**
	 * Returns value of token.
	 * 
	 * @return value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Returns type of token.
	 * 
	 * @return token
	 */
	public TokenType getType() {
		return type;
	}
}
