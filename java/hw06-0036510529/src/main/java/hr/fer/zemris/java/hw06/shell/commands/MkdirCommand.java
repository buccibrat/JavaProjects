package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.SimpleShellParser;
/**
 * Makes directory. Name of directory is passed through argument
 * @author Benjamin kušen
 *
 */
public class MkdirCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.split("\\s+");
		if(args.length != 1) {
			env.writeln("Number of arguments is invalid!");
	        env.write(env.getPromptSymbol() + " ");
	        return ShellStatus.CONTINUE;
		}
		
		Path dir = Paths.get(SimpleShellParser.parsePath(args[0]));
		try{
			Files.createDirectories(dir);
		} catch (IOException e) {
			env.writeln("There was a problem with creating direcotry!");
		}
		env.write(env.getPromptSymbol() + " ");
        return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "mkdir";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("mkdir Command.");
		list.add("Takes one argument that is directory name.");
		list.add("Creates that directory.");
		return Collections.unmodifiableList(list);
	}

}
