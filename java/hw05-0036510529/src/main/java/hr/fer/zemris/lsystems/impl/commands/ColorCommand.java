package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
/**
 * Implementation of Command.
 * 
 * @author Benjamin Kušen
 *
 */
public class ColorCommand implements Command{
	
	/**
	 * Coloer
	 */
	private Color color;
	
	/**
	 * Receives collor in hex and converts it to rgb.
	 * 
	 * @param color
	 */
	public ColorCommand(String color) {
		String colorHex = "#" + color;
//		color.substring(0, 2), color.substring(2, 4), color.substring(4, 6)
		this.color = Color.decode(colorHex);
	}
	
	/**
	 * Sets color of the element that is on top of the stack.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setColor(color);		
	}
}
