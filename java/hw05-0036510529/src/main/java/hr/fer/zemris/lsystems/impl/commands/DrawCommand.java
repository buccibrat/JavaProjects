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
public class DrawCommand implements Command{
	/**
	 * Step for which turtle will move
	 */
	private double step;
	
	/**
	 * @param step
	 */
	public DrawCommand(double step) {
		this.step = step;
	}
	
	/**
	 * Moves turtle for step in a single line and draws it.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState state = ctx.getCurrentState();
		Vector2D location = state.getLocation();
		Vector2D orientation = state.getOrientation().copy();
		orientation.scale(state.getShit() * step);
		Vector2D locationTranslated = location.translated(orientation);
		painter.drawLine(location.getX(), location.getY(), locationTranslated.getX(), locationTranslated.getY(), state.getColor(), 1f);
		state.setLocation(locationTranslated);
	}
	
}
