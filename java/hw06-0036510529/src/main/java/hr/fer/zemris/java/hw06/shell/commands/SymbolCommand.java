package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
/**
 * Receives type of symbol and changes it to the new symbol passed by argmuent.
 * @author korisnik
 *
 */
public class SymbolCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.split("\\s+");
		if(args.length == 1) {
			char symbol;
			switch (args[0]) {
			case "PROMPT":
				symbol = env.getPromptSymbol();
				break;
			case "MORELINES":
				symbol = env.getMorelinesSymbol();
				break;
			case "MULTILINE":
				symbol = env.getMultilineSymbol();
				break;
			default:
				env.writeln("Symbol command not valid.");
				env.write(env.getPromptSymbol() + " ");
				return ShellStatus.CONTINUE;
			}
			env.writeln(String.format("Symbol for %s is '%c' ", args[0], symbol));
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}
		
		if(args.length != 2 || args[1].length() != 1) {
			env.writeln("Input is invalid.");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}
		
		char symbol = args[1].charAt(0);
		switch (args[0]) {
		case "PROMPT":
			env.writeln(String.format("Symbol for PROMPT changed from '%c' to '%c'", env.getPromptSymbol(), symbol));
			env.setPromptSymbol(symbol);
			break;
		case "MORELINES":
			env.writeln(String.format("Symbol for MORELINES changed from '%c' to '%c'", env.getMorelinesSymbol(), symbol));
			env.setMorelinesSymbol(symbol);
			break;
		case "MULTILINE":
			env.writeln(String.format("Symbol for MULTILINE changed from '%c' to '%c'", env.getMultilineSymbol(), symbol));
			env.setMultilineSymbol(symbol);
			break;
		default:
			env.writeln("Symbol name is invalid.");
		}
		env.write(env.getPromptSymbol() + " ");
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "symbol";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Symbol command.");
		list.add("If used with one argument it prints symbol of MyShell for given symbol name.");
		list.add("If used with two arguments, it sets symbol of given name to given character.");
		return Collections.unmodifiableList(list);
	}

}
