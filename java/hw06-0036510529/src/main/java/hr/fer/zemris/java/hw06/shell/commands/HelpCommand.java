package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Expects zero or one argument. 
 * zero arguments -> writes out all avaliable commands. 
 * one argument -> writes the use of specific command.
 * 
 * @author Benjami Kušen
 *
 */
public class HelpCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.split("\\s+");
		Map<String, ShellCommand> map = env.commands();
		if (arguments.length() == 0) {
			for (Entry<String, ShellCommand> e : map.entrySet()) {
				env.writeln(e.getValue().getCommandName());
			}

		} else if (args.length == 1) {
			ShellCommand command = map.get(args[0]);
			if (command == null) {
				env.writeln("Unsuported command.");
			} else {
				env.writeln(command.getCommandName());
				List<String> string = command.getCommandDescription();
				for (String s : string) {
					env.writeln(s);
				}
			}
		}
		env.write(env.getPromptSymbol() + " ");
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Help command.");
		list.add("Accepts zero or one arguments");
		list.add("If number of arguments is zero lists names of all commands");
		list.add("If number of arguments is one outputs name and description of command");
		return Collections.unmodifiableList(list);
	}

}
