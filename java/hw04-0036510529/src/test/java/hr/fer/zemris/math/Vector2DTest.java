package hr.fer.zemris.math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Vector2DTest {

    @Test
    public void constructorTest() {
	assertNotNull(new Vector2D(12, 15.34));
    }

    @Test
    public void gettersTest() {
	Vector2D vector = new Vector2D(17, 23.5);
	assertEquals(17, vector.getX());
	assertEquals(23.5, vector.getY());
    }

    @Test
    public void translateTest() {
	Vector2D vector = new Vector2D(17, 23.5);
	Vector2D vector2 = new Vector2D(-5, 22);
	vector.translate(vector2);
	assertEquals(12, vector.getX());
	assertEquals(45.5, vector.getY());
    }

    @Test
    public void translatedTest() {
	Vector2D vector = new Vector2D(17, 23.5);
	Vector2D vector2 = vector.translated(new Vector2D(1.5, 3.0));
	vector.translate(new Vector2D(1.5, 3));
	assertEquals(vector, vector2);

    }

    @Test
    public void rotateTest() {
	Vector2D vector = new Vector2D(-2, 2);
	vector.rotate(Math.PI / 4);
	Vector2D vector2 = new Vector2D(-2.828427125, 0);
	assertEquals(vector, vector2);
    }
    
    @Test
    public void rotateTest2() {
	Vector2D vector = new Vector2D(-2, 2);
	vector.rotate(Math.PI * 2 + Math.PI / 4);
	Vector2D vector2 = new Vector2D(-2.828427125, 0);
	assertEquals(vector, vector2);
    }

    @Test
    public void rotatedTest() {
	Vector2D vector = new Vector2D(-2, 2);
	Vector2D vector2 = vector.rotated(Math.PI / 4);
	vector.rotate(Math.PI / 4);
	assertEquals(vector, vector2);
    }

    @Test
    public void scaleTest() {
	Vector2D vector = new Vector2D(4, 2);
	vector.scale(2.0);
	assertEquals(8, vector.getX());
	assertEquals(4, vector.getY());
    }

    
    @Test
    public void scaledTest() {
	Vector2D vector = new Vector2D(4, 2);
	Vector2D vector2 = vector.scaled(2.0);
	vector.scale(2.0);
	assertEquals(vector, vector2);
    }

    
    @Test
    public void copyTest() {
	Vector2D vector = new Vector2D(4, 2);
	Vector2D vector2 = vector.copy();
	assertEquals(vector, vector2);
    }

}
