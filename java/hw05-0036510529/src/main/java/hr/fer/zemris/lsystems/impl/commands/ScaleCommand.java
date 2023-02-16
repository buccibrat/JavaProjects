package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
/**
 * Implementation of Command.
 * 
 * @author Benjamin Kušen
 *
 */
public class ScaleCommand implements Command{
	/**
	 * Factor of scaling.
	 */
	private double factor;
	
	/**
	 * @param factor
	 */
	public ScaleCommand(double factor) {
		this.factor = factor;
	}
	
	/**
	 * Scales element that is on top of the stack.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState state = ctx.getCurrentState();
		double shift = state.getShit();
		state.setShit(shift * factor);
	}
}
