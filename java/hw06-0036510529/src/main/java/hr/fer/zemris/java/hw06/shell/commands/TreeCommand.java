package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Expects a single argument that is directory. Writes tree of that directory
 * indenting for 2 whitespaces for n+1 depth.
 * 
 * @author Benjamin Kušen
 *
 */
public class TreeCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.split("\\s+");
		if (args.length != 1) {
			env.writeln("Number of arguments is invalid!");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}

		Path path = Paths.get(args[0]);
		if (!Files.isDirectory(path)) {
			env.writeln("Given argument isn't directory");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}
		try {
			Files.walkFileTree(path, new ShellFileVisitor(env));
		} catch (IOException e) {
			env.writeln("Error while reading from directory!");
		}

		env.write(env.getPromptSymbol() + " ");
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "tree";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Tree command.");
		list.add("Takes one argument that is path to directory.");
		list.add("Writes out tree of directory, every level shifted for two spaces to the right");
		return Collections.unmodifiableList(list);
	}
	/**
	 * 
	 * Private class that defines communication with Files.walkFileTree method.
	 *
	 */
	private static class ShellFileVisitor extends SimpleFileVisitor<Path> {
		private Environment env;
		private int depth = 1;

		public ShellFileVisitor(Environment env) {
			super();
			this.env = env;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			env.writeln(String.format("%" + depth + "s%s", "", dir.getFileName()));
			depth += 2;
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			depth -= 2;
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			env.writeln(String.format("%" + depth + "s%s", "", file.getFileName()));
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			return FileVisitResult.CONTINUE;
		}

	}
}
