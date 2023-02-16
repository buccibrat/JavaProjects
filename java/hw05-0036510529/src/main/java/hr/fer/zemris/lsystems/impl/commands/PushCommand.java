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
public class PushCommand implements Command {
	/**
	 * Default constructor.
	 */
	public PushCommand() {
	}
	/**
	 * Gets element at the top of the stack, without removing it, and pushes copy of it on stack.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.pushState(ctx.getCurrentState().copy());
	}
}
