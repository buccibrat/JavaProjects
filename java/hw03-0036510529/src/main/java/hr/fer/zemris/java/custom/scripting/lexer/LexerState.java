package hr.fer.zemris.java.custom.scripting.lexer;

public enum LexerState {
	/**
	 * accepts nubmers, letters and \\ and \*
	 */
	STRING,
	/**
	 * is read as a method
	 */
	TAG;
}
