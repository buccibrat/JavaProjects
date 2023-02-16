package hr.fer.zemris.java.hw02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hr.fer.zemris.java.hw02.ComplexNumber;

public class ComplexNumberTest {
    @Test
    public void constructorTest() {
	assertNotNull(new ComplexNumber(2, 3));
    }

    @Test
    public void fromRealTest() {
	assertEquals(new ComplexNumber(2, 0), ComplexNumber.fromReal(2));
    }

    @Test
    public void fromImaginaryTest() {
	assertEquals(new ComplexNumber(0, 2), ComplexNumber.fromImaginary(2));
    }

    @Test
    public void fromMagnitudeAndAngleThrows() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ComplexNumber.fromMagnitudeAndAngle(-1, 70);
	});
    }

    @Test
    public void fromMagnitudeAndAngleTest() {
	assertEquals(new ComplexNumber(Math.sqrt(2), Math.sqrt(2)),
		ComplexNumber.fromMagnitudeAndAngle(2, Math.PI / 4));
    }

    @Test
    public void fromMagnitudeAndAngleTest2() {
	assertEquals(new ComplexNumber(2, 0), ComplexNumber.fromMagnitudeAndAngle(2, Math.PI * 2));
    }

    @Test
    public void fromMagnitudeAndAngleTest3() {
	assertEquals(new ComplexNumber(0, -2), ComplexNumber.fromMagnitudeAndAngle(2, Math.PI * 3 / 2));
    }

    @Test
    public void parseTest() {
	assertEquals(new ComplexNumber(3.51, 0), ComplexNumber.parse("3.51"));
	assertEquals(new ComplexNumber(-3.17, 0), ComplexNumber.parse("-3.17"));
	assertEquals(new ComplexNumber(0, -2.71), ComplexNumber.parse("-2.71i"));
	assertEquals(new ComplexNumber(0, 1), ComplexNumber.parse("i"));
	assertEquals(new ComplexNumber(1, 0), ComplexNumber.parse("1"));
	assertEquals(new ComplexNumber(-2.71, -3.15), ComplexNumber.parse("-2.71-3.15i"));
    }

    @Test
    public void getRealTest() {
	assertEquals(5, new ComplexNumber(5, 0).getReal());

    }

    @Test
    public void getImaginaryTest() {
	assertEquals(5, new ComplexNumber(0, 5).getImaginary());

    }

    @Test
    public void getMagnitudeTest() {
	assertEquals(5 * Math.sqrt(2), new ComplexNumber(5, 5).getMagnitude());
    }

    @Test
    public void getAngleTest() {
	assertEquals(Math.PI / 4, new ComplexNumber(1, 1).getAngle());
	assertEquals(Math.PI * 3 / 4, new ComplexNumber(-1, 1).getAngle());
	assertEquals(Math.PI * 5 / 4, new ComplexNumber(-1, -1).getAngle());
	assertEquals(Math.PI * 7 / 4, new ComplexNumber(1, -1).getAngle());
    }

    @Test
    public void addTest() {
	assertEquals(new ComplexNumber(10, 10), new ComplexNumber(5, 5).add(new ComplexNumber(5, 5)));
    }

    @Test
    public void subTest() {
	assertEquals(new ComplexNumber(0, 0), new ComplexNumber(5, 5).sub(new ComplexNumber(5, 5)));
    }

    @Test
    public void mulTest() {
	assertEquals(new ComplexNumber(-17.82, 14.89), new ComplexNumber(2.7, 7.6).mul(new ComplexNumber(1, 2.7)));
	assertEquals(new ComplexNumber(0, 50), new ComplexNumber(5, 5).mul(new ComplexNumber(5, 5)));
	assertEquals(new ComplexNumber(50, 0), new ComplexNumber(5, 5).mul(new ComplexNumber(5, -5)));
    }

    @Test
    public void divTest() {
	assertEquals(new ComplexNumber(-0.1058106, -0.7535868),
		(new ComplexNumber(5, -2.7)).div(new ComplexNumber(2.6, 7)));
	assertEquals(new ComplexNumber(0, 1), new ComplexNumber(5, 5).div(new ComplexNumber(5, -5)));
    }

    @Test
    public void powerTest() {
	assertEquals(new ComplexNumber(15.65, -182.817), new ComplexNumber(5, -2.7).power(3));
	assertEquals(new ComplexNumber(41.71, 37.8), new ComplexNumber(-7, -2.7).power(2));
    }

    @Test
    public void rootTest() {
	ComplexNumber number = new ComplexNumber(2, -3.71);
	ComplexNumber[] numberArray = number.root(4);
	ComplexNumber[] array = new ComplexNumber[4];
	array[0] = new ComplexNumber(0.380927, 1.381260);
	array[1] = new ComplexNumber(-1.381260, 0.380927);
	array[2] = new ComplexNumber(-0.380927, -1.381260);
	array[3] = new ComplexNumber(1.381260, -0.380927);

	assertEquals(array[0], numberArray[0]);
	assertEquals(array[1], numberArray[1]);
	assertEquals(array[2], numberArray[2]);
	assertEquals(array[3], numberArray[3]);
    }
    
    @Test
    public void rootTest2() {
	ComplexNumber number = new ComplexNumber(16, 0);
	ComplexNumber[] numberArray = number.root(4);
	ComplexNumber[] array = new ComplexNumber[4];
	array[0] = new ComplexNumber(2, 0);
	array[1] = new ComplexNumber(0, 2);
	array[2] = new ComplexNumber(-2, 0);
	array[3] = new ComplexNumber(0, -2);

	assertEquals(array[0], numberArray[0]);
	assertEquals(array[1], numberArray[1]);
	assertEquals(array[2], numberArray[2]);
	assertEquals(array[3], numberArray[3]);

    }
    
    @Test
    public void toStringTest() {
	assertEquals(new String("2.0-5.0i"), new ComplexNumber(2, -5).toString());
	assertEquals(new String("0.0+1.0i"), new ComplexNumber(0, 1).toString());
    }
    
    @Test
    public void equalsTest() {
	new ComplexNumber(1.000100, 2).equals(new ComplexNumber(1.010000, 2));
    }
}
