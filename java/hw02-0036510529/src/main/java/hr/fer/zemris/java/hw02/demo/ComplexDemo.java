package hr.fer.zemris.java.hw02.demo;

import hr.fer.zemris.java.hw02.ComplexNumber;;
/**
 * Demo for class <code>ComplexNumber</code>
 * @author Benjamin Kušen
 *
 */
public class ComplexDemo {
    /**
     * Main method showing a small demo of ComplexNumber class.
     * @param args command line arguments
     */
    public static void main(String[] args) {
	ComplexNumber c1 = new ComplexNumber(2, 3);
	ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
	ComplexNumber c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57)).div(c2).power(3).root(2)[1];
	System.out.println(c3);
	ComplexNumber c4 = ComplexNumber.parse("i");
	System.out.println(c4.toString());
	c4 = ComplexNumber.parse("-i");
	System.out.println(c4.toString());
	c4 = ComplexNumber.parse("1+i");
	System.out.println(c4.toString());
    }
}
