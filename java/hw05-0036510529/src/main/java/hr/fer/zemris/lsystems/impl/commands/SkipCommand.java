package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.lsystems.impl.Vector2D;

/**
 * Implementation of Command.
 * 
 * @author Benjamin Kušen
 *
 */
public class SkipCommand implements Command{
	/**
	 * Step that turtle wil make
	 */
	private double step;
	/**
	 * @param step
	 */
	public SkipCommand(double step) {
		this.step = step;
	}
	
	/**
	 * Moves turtle for step without drawing.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState state = ctx.getCurrentState();
		Vector2D location = state.getLocation();
		Vector2D orientation = state.getOrientation().copy();
		orientation.scale(state.getShit() * step);
		state.setLocation(location.translated(orientation));
	}
	
}
