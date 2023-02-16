package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

/**
 * Turtle.
 * 
 * @author Benjamin Kušen
 *
 */
public class TurtleState {	
	/**
	 * Current location of turtle.
	 */
	Vector2D location;
	/**
	 * Orientation of turtle. Represented by unit vector.
	 */
	Vector2D orientation;
	/**
	 * Color of turlte
	 */
	Color color;
	/**
	 * Shift for which turtle will move.
	 */
	double shit = 1; //Čitaj shift
	
	/**
	 * Constructs Turtle.
	 * 
	 * @param location
	 * @param orientation
	 * @param color
	 */
	public TurtleState(Vector2D location, Vector2D orientation, Color color) {
		this.location = location;
		this.orientation = orientation;
		this.color = color;
	}
	
	public TurtleState(Vector2D location, Vector2D orientation, Color color, double shift) {
		this.location = location;
		this.orientation = orientation;
		this.color = color;
		this.shit = shift;
	}
	
	/**
	 * Returns location.
	 * 
	 * @return
	 */
	public Vector2D getLocation() {
		return location;
	}

	/**
	 * Sets location.
	 * 
	 * @param location
	 */
	public void setLocation(Vector2D location) {
		this.location = location;
	}

	/**
	 * Returns orientation.
	 * 
	 * @return
	 */
	public Vector2D getOrientation() {
		return orientation;
	}

	/**
	 * Sets orientation.
	 * 
	 * @param angle
	 */
	public void setOrientation(double angle) {
		orientation.rotate(angle);;
	}

	/**
	 * Returns color.
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets color.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Retruns shift.
	 * 
	 * @return
	 */
	public double getShit() {
		return shit;
	}

	/**
	 * Sets shift.
	 * 
	 * @param shit
	 */
	public void setShit(double shit) {
		this.shit = shit;
	}
	
	/**
	 * Returns copy of turtle state.
	 * 
	 * @return
	 */
	public TurtleState copy() {
		return new TurtleState(location.copy(), orientation.copy(), color, shit);
	}
}
