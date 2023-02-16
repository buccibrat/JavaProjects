package hr.fer.zemris.java.hw06.shell;

import java.util.List;

/**
 * Interface representing commands that are used in shell
 * 
 * @author Benjami Kušen
 *
 */
public interface ShellCommand {
	/**
	 * Executes command.
	 * 
	 * @param env
	 * @param arguments
	 * @return
	 */
	ShellStatus executeCommand(Environment env, String arguments);
	/**
	 * @return command name
	 */
	String getCommandName();
	/**
	 * @return List containing String objects that describe what the command does
	 */
	List<String> getCommandDescription();
}
