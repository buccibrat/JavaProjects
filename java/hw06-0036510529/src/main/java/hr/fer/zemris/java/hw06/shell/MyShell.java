package hr.fer.zemris.java.hw06.shell;
/**
 * Shell
 * @author korisnik
 *
 */
public class MyShell {
	public static void main (String[] args) {
		 Environment environment = new ShellEnvironment();
	        ShellStatus status = ShellStatus.CONTINUE;
	        do {
	        	StringBuilder bob = new StringBuilder();
	            String l = environment.readLine();
	            while(l.endsWith(environment.getMorelinesSymbol().toString())) { //Checking for multiline symbol
	            	environment.write(environment.getMorelinesSymbol().toString() + " ");
	            	bob.append(l.substring(0, l.length()-1));
	            	l = environment.readLine();
	            }
	            bob.append(l);
	            l = bob.toString();
	            if(l.trim().length() == 0) {
	            	environment.write(environment.getPromptSymbol() + " ");
	            	continue;
	            }
	            String commandName = SimpleShellParser.parseName(l);
	            String arguments = l.substring(commandName.length()).trim();

	            ShellCommand command = environment.commands().get(commandName);

	            if (command == null) {
	                environment.writeln("Unknown command!");
	                environment.write(environment.getPromptSymbol() + " ");
	            } else {
	                status = command.executeCommand(environment, arguments);
	            }
	        } while (status != ShellStatus.TERMINATE);
		
	}
}
