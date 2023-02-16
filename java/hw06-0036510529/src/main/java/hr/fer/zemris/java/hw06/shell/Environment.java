package hr.fer.zemris.java.hw06.shell;

import java.util.SortedMap;

/**
 * Interface that defines a strategy of comunicating with shell
 * 
 * @author Benjamin Kušen
 *
 */
public interface Environment {
	/**
	 * Reads line from shell.
	 * 
	 * @return
	 * @throws ShellIOException
	 */
	String readLine() throws ShellIOException;

	/**
	 * Writes text to shell
	 * 
	 * @param text
	 * @throws ShellIOException
	 */
	void write(String text) throws ShellIOException;

	/**
	 * Writes text to shell and adds \n at the end.
	 * 
	 * @param text
	 * @throws ShellIOException
	 */
	void writeln(String text) throws ShellIOException;

	/**
	 * @return SortedMap of all commands supported by shell
	 */
	SortedMap<String, ShellCommand> commands();

	/**
	 * @return multiline symbol
	 */
	Character getMultilineSymbol();

	/**
	 * sets multiline symbol
	 * 
	 * @param symbol
	 */
	void setMultilineSymbol(Character symbol);

	/**
	 * @return prompt symbol
	 */
	Character getPromptSymbol();

	/**
	 * sets prompt symbol
	 * 
	 * @param symbol
	 */
	void setPromptSymbol(Character symbol);

	/**
	 * @return morelines symbol
	 */
	Character getMorelinesSymbol();

	/**
	 * sets morelines symbol
	 * 
	 * @param symbol
	 */
	void setMorelinesSymbol(Character symbol);
}
