package hr.fer.zemris.java.hw06.shell;

/**
 * Parses input from shell
 * 
 * @author Benjamin Kušen
 *
 */
public class SimpleShellParser {
	
	/**
	 * Parses name of command
	 * 
	 * @param args
	 * @return
	 */
	public static String parseName(String args) {
		String[] strings = args.split("\\s+");
		if(strings.length != 0) {
			return strings[0];
		}
		throw new ShellIOException();
	}
	
	/**
	 * parses path
	 * 
	 * @param args
	 * @return
	 */
	public static String parsePath(String args) {
		if (args.startsWith("\"") && args.endsWith("\"")){
			String subString = args.substring(1, args.length() - 1);
			if (subString.matches("([a-zA-Z]:)?(/[a-zA-Z0-9_.-]+)+/?")) {
				throw new IllegalArgumentException();
			}
			return subString;
		} else {
			if (args.matches("([a-zA-Z]:)?(/[a-zA-Z0-9_.-]+)+/?")) {
				throw new IllegalArgumentException();
			}
			return args;
		}
	}
}
