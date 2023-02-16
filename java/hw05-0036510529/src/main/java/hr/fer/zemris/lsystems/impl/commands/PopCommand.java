package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
/**
 * Implementation of Command.
 * 
 * @author Benjamin Kušen
 *
 */
public class PopCommand implements Command{
	/**
	 * Default constructor
	 */
	public PopCommand() {
	}
	/**
	 * Removes element at the top of the stack.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.popState();
	}
}
