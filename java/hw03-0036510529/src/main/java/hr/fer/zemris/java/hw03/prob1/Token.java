package hr.fer.zemris.java.hw03.prob1;

/**
 * Structure that represents token that has value and type.
 * 
 * @author Benjamin Kušen
 *
 */

public class Token {
	/**
	 * Type of token
	 */
	private TokenType type;
	/**
	 * Value of token.
	 */
	private Object value;

	/**
	 * Sets value and type of token.
	 * 
	 * @param type  type
	 * @param value value
	 */
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
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
	 * Returns type of token
	 * 
	 * @return type
	 */
	public TokenType getType() {
		return type;
	}
}
