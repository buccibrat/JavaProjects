package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.SimpleShellParser;

/**
 * Copies given file to the new location. If file already exists gives user the
 * option to chose if he wants to overwrite it or not. Can't copy directories.
 * 
 * @author korisnik
 *
 */
public class CopyCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.split("\\s+");

		if (args.length != 2) {
			env.writeln("Number of arguments is invalid!");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}

		Path file = Paths.get(SimpleShellParser.parsePath(args[0]));
		Path destination = Paths.get(SimpleShellParser.parsePath(args[1]));

		if (Files.isDirectory(file)) {
			env.writeln("First argument isnt file!");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}

		if (Files.isDirectory(destination)) {
			destination = Paths.get(destination.toString() + "/" + SimpleShellParser.parsePath(args[0]));
		}
		if (file.equals(destination)) {
			env.writeln("Cannot copy file to itself!");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}

		if (Files.exists(destination)) {
			env.writeln("Do you want to overwrite file? Y/N");
			env.write(env.getPromptSymbol() + " ");
			String userInput = env.readLine();
			if (!userInput.equals("Y")) {
				env.writeln("File will not be copied.");
				env.write(env.getPromptSymbol() + " ");
				return ShellStatus.CONTINUE;
			}
		}

		try (InputStream inputStream = Files.newInputStream(file, StandardOpenOption.READ);
				OutputStream outputStream = Files.newOutputStream(destination, StandardOpenOption.CREATE)) {
			byte[] buff = new byte[4096];
			while (true) {
				int readBytes = inputStream.read(buff);
				if (readBytes == -1) {
					break;
				}
				outputStream.write(Arrays.copyOf(buff, readBytes));
			}

		} catch (IOException e) {
			env.writeln("There was a problem while copying file");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}

		env.writeln("File is copied!");
		env.write(env.getPromptSymbol() + " ");
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "copy";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Copy Command.");
		list.add("Takes two arguments, source file name and destination file name");
		list.add("If destination file exists asks user to overwrite it");
		list.add("If the second argument is directory copies file to that directory");
		return Collections.unmodifiableList(list);
	}

}
