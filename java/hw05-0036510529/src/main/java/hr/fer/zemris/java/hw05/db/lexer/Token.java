package hr.fer.zemris.java.hw05.db.lexer;

/**
 * Token
 * 
 * @author Benjamin Kušen
 *
 */
public class Token {
	/**
	 * Value of token.
	 */
	private String value;
	/**
	 * Type of token.
	 */
	private TokenType type;

	/**
	 * Constructs token.
	 * 
	 * @param type
	 * @param value
	 */
	public Token(TokenType type, String value) {
		super();
		this.value = value;
		this.type = type;
	}

	/**
	 * Returns value of token.
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Returns type of token.
	 * 
	 * @return type
	 */
	public TokenType getType() {
		return type;
	}

}
