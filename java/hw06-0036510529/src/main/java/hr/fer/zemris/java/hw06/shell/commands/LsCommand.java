package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.SimpleShellParser;
/**
 *  Writes a directory listing (not recursive).
 *  the format is
 *  drwx
 *   or 	depends on the file
 *  ---- 
 *  
 *  d - directory
 *  r - readable
 *  w - writable
 *  x - executable
 *  
 *  then writes size, date, time and name.
 * @author korisnik
 *
 */
public class LsCommand implements ShellCommand {
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.split("\\s+");
		if (args.length != 1) {
			env.writeln("Number of arguments is invalid");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}

		Path path = Paths.get(SimpleShellParser.parsePath(args[0]));
		if (!Files.isDirectory(path)) {
			env.writeln("Argument given isn't directory!");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}

		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
			for (Path p : dirStream) {
				StringBuilder bob = new StringBuilder();

				if (Files.isDirectory(p)) {
					bob.append("d");
				} else {
					bob.append("-");
				}

				if (Files.isReadable(p)) {
					bob.append("r");
				} else {
					bob.append("-");
				}

				if (Files.isWritable(p)) {
					bob.append("w");
				} else {
					bob.append("-");
				}

				if (Files.isExecutable(p)) {
					bob.append("x");
				} else {
					bob.append("-");
				}

				bob.append(" ");
				long size = Files.size(p);
				bob.append(String.format("%10d", size));
				bob.append(" ");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				BasicFileAttributeView faView = Files.getFileAttributeView(p, BasicFileAttributeView.class,
						LinkOption.NOFOLLOW_LINKS);
				BasicFileAttributes attributes = faView.readAttributes();
				FileTime fileTime = attributes.creationTime();
				String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
				bob.append(formattedDateTime);
				bob.append(" ");
				bob.append(p.getFileName());
				env.writeln(bob.toString());
			}
		} catch (IOException e) {
			env.writeln("Error while reading from directory!");
			env.write(env.getPromptSymbol() + " ");
			return ShellStatus.CONTINUE;
		}
		env.write(env.getPromptSymbol() + " ");
        return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "ls";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("ls Command.");
		list.add("Takes one argument and outputs directory listing (not recursive).");
		list.add("Writes out 4 columns.");
		list.add("First column indicates if current object is directory (d), readable (r), writable (w) and executable (x)");
		list.add("Second column contains object size in bytes that is right aligned and occupies 10 characters");
		list.add("Follows file creation date/time and finally file name.");
		return Collections.unmodifiableList(list);
	}

}
