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
public class RotateCommand implements Command{
	/**
	 * Angle of rotation
	 */
	private double angle;
	
	/**
	 * @param angle
	 */
	public RotateCommand(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Rotates object at the top of the stack for angle.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setOrientation(angle);
	}
}
