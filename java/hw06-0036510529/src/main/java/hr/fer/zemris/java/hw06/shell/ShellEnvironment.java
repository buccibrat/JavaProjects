package hr.fer.zemris.java.hw06.shell;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import hr.fer.zemris.java.hw06.shell.commands.*;

public class ShellEnvironment implements Environment {

	private Scanner sc = new Scanner(System.in);
	private Character prompt = '>';
	private Character multiline = '|';
	private Character moreline = '\\';
	private SortedMap<String, ShellCommand> commands;

	public ShellEnvironment() {
		System.out.println("Welcome to MyShell v 1.0");
		System.out.print(prompt + " ");
		commands = new TreeMap<String, ShellCommand>();
		putCommands();
	}

	@Override
	public String readLine() throws ShellIOException {
		StringBuilder bob = new StringBuilder();
		String line = sc.nextLine();
		while (line.endsWith(multiline.toString())) {
			bob.append(line, 0, line.length() - 1);
			System.out.print(multiline + " ");
			line = sc.nextLine();
		}
		bob.append(line);
		return bob.toString();
	}

	@Override
	public void write(String text) throws ShellIOException {
		System.out.print(text);

	}

	@Override
	public void writeln(String text) throws ShellIOException {
		System.out.println(text);

	}

	@Override
	public SortedMap<String, ShellCommand> commands() {
		return commands;
	}

	@Override
	public Character getMultilineSymbol() {
		return multiline;
	}

	@Override
	public void setMultilineSymbol(Character symbol) {
		multiline = symbol;

	}

	@Override
	public Character getPromptSymbol() {
		return prompt;
	}

	@Override
	public void setPromptSymbol(Character symbol) {
		prompt = symbol;
	}

	@Override
	public Character getMorelinesSymbol() {
		return moreline;
	}

	@Override
	public void setMorelinesSymbol(Character symbol) {
		moreline = symbol;

	}

	private void putCommands() {
		ShellCommand charset = new CharsetsCommand();
		commands.put(charset.getCommandName(), charset);
		ShellCommand symbol = new SymbolCommand();
		commands.put(symbol.getCommandName(), symbol);
		ShellCommand exit = new ExitCommand();
		commands.put(exit.getCommandName(), exit);
		ShellCommand cat = new CatCommand();
		commands.put(cat.getCommandName(), cat);
		ShellCommand copy = new CopyCommand();
		commands.put(copy.getCommandName(), copy);
		ShellCommand ls = new LsCommand();
		commands.put(ls.getCommandName(), ls);
		ShellCommand mkdir = new MkdirCommand();
		commands.put(mkdir.getCommandName(), mkdir);
		ShellCommand hexdump = new HexdumpCommand();
		commands.put(hexdump.getCommandName(), hexdump);
		ShellCommand tree = new TreeCommand();
		commands.put(tree.getCommandName(), tree);
		ShellCommand help = new HelpCommand();
		commands.put(help.getCommandName(), help);
	}
}
