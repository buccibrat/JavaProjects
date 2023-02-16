package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;
import hr.fer.zemris.java.hw06.shell.SimpleShellParser;

/**
 * This class represents hexdump command. It takes a single argument which has to be file name.
 * It produces hex-output of given file.
 * 
 * 
 * @author Benjami Kušen
 *
 */
public class HexdumpCommand implements ShellCommand {
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Path file = Paths.get(SimpleShellParser.parsePath(arguments));

		if (Files.isDirectory(file)) {
			env.writeln("Input can only be file");
			env.write(env.getPromptSymbol() + "");
			return ShellStatus.CONTINUE;
		}

		try (InputStream inputStream = Files.newInputStream(file, StandardOpenOption.READ)) {
			byte[] buffer = new byte[16];
			int currentPosition = 0x0;
			StringBuilder bob = new StringBuilder();
			while (true) {
				int readBytes = inputStream.read(buffer);
				if (readBytes == -1) {
					break;
				}
				bob.append(String.format("%08X", currentPosition));
				bob.append(": ");
				for (int i = 0; i < 16; i++) {
					if (i >= readBytes) {
						bob.append("   ");
					} else {
						bob.append(String.format("%02X", buffer[i])).append(" ");
					}

					if (i == 7) {
						bob.setLength(bob.length() - 1);
						bob.append("|");
					}
				}
				bob.append(" | ");
				for (int i = 0; i < readBytes; i++) {
					if (buffer[i] < 32 && buffer[i] > 127) {
						buffer[i] = 46;
					}
				}
				bob.append(new String(buffer));
				env.writeln(bob.toString());
				bob.setLength(0);
				currentPosition += 0X10;
			}
		} catch (IOException e) {
			env.write("Problem occurd while reading file.");
		}
		env.write(env.getPromptSymbol() + " ");
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "hexdump";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Hexdump command.");
		list.add("Takes one argument that is file name.");
		list.add("Writes hex-output where evry character outside of [32, 127] bounds is replaced with .");
		return Collections.unmodifiableList(list);
	}

}
