package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
/**
 * Writes in shell all available charets.
 * @author Benjamin Kušen
 *
 */
public class CharsetsCommand implements ShellCommand {
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if (arguments.length() != 0) {
			env.writeln("0 arguments expected!");
		} else {
			SortedMap<String, Charset> charsetMap = Charset.availableCharsets();
			for (String s : charsetMap.keySet()) {
				env.writeln(s);
			}
		}
		env.write(env.getPromptSymbol() + " ");
		return ShellStatus.CONTINUE;
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Charset Command.");
		list.add("Takes no arguments.");
		list.add("Lists name of suported charsets for your Java platform.");
		list.add(" A single charset name is written per line.");
		return Collections.unmodifiableList(list);
	}

	@Override
	public String getCommandName() {
		return "charsets";
	}
}
