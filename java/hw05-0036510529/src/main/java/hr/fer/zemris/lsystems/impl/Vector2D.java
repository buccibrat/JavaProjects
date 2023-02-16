package hr.fer.zemris.lsystems.impl;

import java.util.Objects;

/**
 * Vector2D class represents vector with its start at coordinates (0, 0) in
 * Cartesian coordinate system.
 * 
 * @author Benjamin Kušen
 *
 */
public class Vector2D {

	private static final double THRESHOLD = 1E-8;
	/**
	 * X coordinate of vector
	 */
	private double x;
	/**
	 * Y coordinate of vector
	 */
	private double y;

	/**
	 * Receives x and y coordinates of vector.
	 * 
	 * @param x x coordinate of vector
	 * @param y y coordinate of vector
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns value of x coordinate
	 * 
	 * @return value of x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns value of y coordinate
	 * 
	 * @return value of y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Translates vector by offset of another vector
	 * 
	 * @param offset vector for which original vector is to be translated
	 */
	public void translate(Vector2D offset) {
		x += offset.getX();
		y += offset.getY();
	}

	/**
	 * Creates new instance of Vector2D that is equal to this vector translated by
	 * offset of another vector
	 * 
	 * @param offset vector for which original vector is to be translated
	 * @return returns new vector
	 */
	public Vector2D translated(Vector2D offset) {
		Vector2D vector = this.copy();
		vector.translate(offset);
		return vector;
	}

	/**
	 * Rotates vector for angle. angle needs to be in radians.
	 * 
	 * @param angle angle for which vector is rotated, needs to be in radians
	 */
	public void rotate(double angle) {
		double angleNew = Math.atan2(y, x) + angle;
		double length = Math.sqrt(x * x + y * y);
		x = length * Math.cos(angleNew);
		y = length * Math.sin(angleNew);
	}

	/**
	 * Returns new instance of Vector2D that is original vector rotated for angle.
	 * angle needs to be in radians.
	 * 
	 * @param angle angle for which vector is rotated, needs to be in radians
	 * @return new instance of Vector2D
	 */
	public Vector2D rotated(double angle) {
		Vector2D vector = copy();
		vector.rotate(angle);
		return vector;
	}

	/**
	 * Scales vector for a given scaler.
	 * 
	 * @param scaler number type double
	 */
	public void scale(double scaler) {
		x = x * scaler;
		y = y * scaler;
	}

	/**
	 * Retruns new instance of Vector2D that this vector scaled by scaler.
	 * 
	 * @param scaler number type double
	 * @return new Instance of Vector2D
	 */
	public Vector2D scaled(double scaler) {
		Vector2D vector = copy();
		vector.scale(scaler);
		return vector;
	}

	/**
	 * Returns new instance of Vector2D that is copy of this vector.
	 * 
	 * @return new instance of Vector2D that is copy of this vector.
	 */
	public Vector2D copy() {
		return new Vector2D(this.x, this.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * Two vectors are equal if the difference between their x, and y coordinates is
	 * lesser than 1E-8
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector2D))
			return false;
		Vector2D other = (Vector2D) obj;
		if (Math.abs(this.x - other.getX()) <= THRESHOLD && Math.abs(this.y - other.getY()) <= THRESHOLD) {
			return true;
		}
		return false;
	}

}
