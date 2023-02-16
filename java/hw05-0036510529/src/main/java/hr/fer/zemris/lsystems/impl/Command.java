package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * 
 * 
 * @author Benjamin Kušenx
 *
 */
public interface Command {
	/**
	 * Executes command.
	 * 
	 * @param ctx
	 * @param painter
	 */
	void execute(Context ctx, Painter painter);
}
