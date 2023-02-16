package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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
 * Cat command that writes file to console with recieved charset
 * 
 * @author korisnik
 *
 */
public class CatCommand implements ShellCommand {
	
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] args = arguments.split("\\s+");
		if (arguments.length() < 1) {
			env.writeln("Number of arguments is invalid!");
			env.write(env.getPromptSymbol() + "");
			return ShellStatus.CONTINUE;
		} else if (args.length == 1 || args.length == 2) {
			Path path = Paths.get(SimpleShellParser.parsePath(args[0]));
			try (InputStream inputStream = Files.newInputStream(path, StandardOpenOption.READ)) {

				Charset charset;
				if (args.length == 2) {
					charset = Charset.forName(args[1]);
				} else {
					charset = Charset.defaultCharset();
				}
				byte[] buff = new byte[4096];

				while (true) {
					int r = inputStream.read(buff);
					if (r < 1)
						break;

					String output = new String(Arrays.copyOf(buff, r), charset);
					env.write(output);
				}
			} catch (IOException | IllegalArgumentException e) {
				env.writeln("Invalid charset!");
				env.write(env.getPromptSymbol() + " ");
				return ShellStatus.CONTINUE;
			}

		}
		env.writeln("");
		env.write(env.getPromptSymbol() + " ");
		return ShellStatus.CONTINUE;

	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Cat command.");
		list.add("Must take one argument, but can take two");
		list.add("First argument is path which contesnts are writen to shell.");
		list.add("Second argument is charset.");
		list.add("If second argument isn't provided then the default charset is used");
		return Collections.unmodifiableList(list);
	}

	@Override
	public String getCommandName() {
		return "cat";
	}
}
