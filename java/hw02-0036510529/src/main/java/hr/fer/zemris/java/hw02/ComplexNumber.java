package hr.fer.zemris.java.hw02;

import java.lang.Math;

/**
 * This class represents implementation of Complex number.
 * 
 * @author Benjamin Kušen
 *
 */
public class ComplexNumber {
    /**
     * Real part of complex number.
     */
    private double real;
    /**
     * Imaginary part of complex number.
     */
    private double imaginary;
    /**
     * Constant for comparing complex numbers with E-6 precision.
     */
    private final double THRESHOLD = 0.000001;

    /**
     * 
     * @param real      real part of complex number
     * @param imaginary imaginary part of complex number
     */
    public ComplexNumber(double real, double imaginary) {
	this.real = real;
	this.imaginary = imaginary;
    }

    /**
     * 
     * Parses string to complex number if possible, otherwise throws
     * IllegalArgumentException.
     * 
     * @param s string that is parsed to complex number if it possible
     * @return new complex number
     */
    public static ComplexNumber parse(String s) {

	try {
	    return new ComplexNumber(Double.parseDouble(s), 0);
	} catch (NumberFormatException e) {
	    if (s.endsWith("i")) {
		if (s.length() == 1) {
		    return new ComplexNumber(0, 1);
		} else if(s.length() == 2 && s.startsWith("-")) {
		    return new ComplexNumber(0, -1);
		}
		int indexOfOperator = s.indexOf("+");

		if (indexOfOperator == -1) {
		    indexOfOperator = s.indexOf("-");
		    if (indexOfOperator != -1) {
			int indexOfOperatorSecond = s.indexOf("-", 1);

			if (indexOfOperatorSecond != -1) {
			    return parseToNumber(s, indexOfOperatorSecond);
			}
		    }

		    return new ComplexNumber(0, Double.parseDouble(s.substring(0, s.length() - 1)));
		}

		return parseToNumber(s, indexOfOperator);
	    }
	    throw new IllegalArgumentException();
	}
    }

    private static ComplexNumber parseToNumber(String s, int index) {
	String s1;
	String s2;
	s1 = s.substring(0, index);
	s2 = s.substring(index, s.length() - 1);
	return new ComplexNumber(Double.parseDouble(s1), Double.parseDouble(s2));
    }

    /**
     * Creats complex number with real part equaling to argument and imaginary part
     * equaling to zero.
     * 
     * @param real real part of complex number
     * @return new complex number
     */
    public static ComplexNumber fromReal(double real) {
	return new ComplexNumber(real, 0);
    }

    /**
     * Creates complex number with imaginary part equaling to argument and real part
     * equaling to zero.
     * 
     * @param imaginary imaginary part of complex number
     * @return new complex number
     */
    public static ComplexNumber fromImaginary(double imaginary) {
	return new ComplexNumber(0, imaginary);
    }

    /**
     * Takes polar coordinates and turns them to cartesian coordinates.
     * 
     * @param magnitude magnitude of complex number
     * @param angle     angle of complex number
     * @return complex number with real and imaginary part
     */
    public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
	if (magnitude < 0) {
	    throw new IllegalArgumentException();
	}
	return new ComplexNumber(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }

    /**
     * Returns real part of complex number.
     * 
     * @return real part of complex number
     */
    public double getReal() {
	return real;
    }

    /**
     * Returns imaginary part of complex number.
     * 
     * @return imaginary part of complex number
     */
    public double getImaginary() {
	return imaginary;
    }

    /**
     * Returns magnitude of complex number.
     * 
     * @return magnitude of complex number
     */
    public double getMagnitude() {
	return Math.sqrt(real * real + imaginary * imaginary);
    }

    /**
     * Returns angle of complex number from 0 to 2 pi.
     * 
     * @return angle of complex number
     */
    public double getAngle() {
	double angle = Math.atan2(imaginary, real);
	if (angle < 0) {
	    return angle + 2 * Math.PI;
	}
	return angle;
    }
/**
 * Adds complex number to this complex number and returns new complex number that is value of addition.
 * @param c complex number that is added to this complex number
 * @return new complex number that is value of addition
 */
    public ComplexNumber add(ComplexNumber c) {
	checkIfNull(c);
	return new ComplexNumber(real + c.getReal(), imaginary + c.getImaginary());
    }
/**
 * Substitutes complex number with this complex number and returns new compex number that is value of substitution.
 * @param c complex number that is substituted from this complex number
 * @return new complex number that is value of substitution
 */
    public ComplexNumber sub(ComplexNumber c) {
	checkIfNull(c);
	return new ComplexNumber(real - c.getReal(), imaginary - c.getImaginary());
    }
/**
 * Multiplies two complex numbers and returns new complex number.
 * @param c complex number that is multiplied with this complex number
 * @return new complex number
 */
    public ComplexNumber mul(ComplexNumber c) {
	checkIfNull(c);
	return new ComplexNumber(real * c.getReal() - imaginary * c.getImaginary(),
		real * c.getImaginary() + imaginary * c.getReal());
    }
/**
 * Divides two complex numbers and returns new complex number.
 * @param c complex number with which this complex number will be divided
 * @return new complex number
 */
    public ComplexNumber div(ComplexNumber c) {
	checkIfNull(c);
	return new ComplexNumber((this.mul(complement(c)).getReal()) / (c.mul(complement(c)).getReal()),
		(this.mul(complement(c)).getImaginary()) / (c.mul(complement(c)).getReal()));
    }
/**
 * Returns complement of complex number passed to the method.
 * @param c complex number
 * @return new complex number that is complement of parametar
 */
    private ComplexNumber complement(ComplexNumber c) {
	return new ComplexNumber(c.getReal(), -c.getImaginary());
    }
/**
 * Returns new complex number that is this complex number raised to the power of parametar.
 * @param n exponent
 * @return the value this complex number to the power of n
 */
    public ComplexNumber power(int n) {
	if (n < 0) {
	    throw new IllegalArgumentException();
	}

	return new ComplexNumber(Math.pow(getMagnitude(), n) * Math.cos(n * getAngle()),
		Math.pow(getMagnitude(), n) * Math.sin(n * getAngle()));
    }
/**
 * returns array of complex numbers with the root value of this complex number
 * @param n exponent
 * @return nth root of this complex number
 */
    public ComplexNumber[] root(int n) {
	if (n <= 0) {
	    throw new IllegalArgumentException();
	}
	ComplexNumber[] array = new ComplexNumber[n];

	for (int i = 0; i < n; i++) {
	    array[i] = new ComplexNumber(Math.pow(getMagnitude(), (double)1/n) * Math.cos((getAngle() + 2 * i * Math.PI) / n),
		    Math.pow(getMagnitude(), (double)1/n) * Math.sin((getAngle() + 2 * i * Math.PI) / n));
	}
	return array;
    }
/**
 * Returns complex number writen in string.
 */
    public String toString() {
	if(imaginary < 0) {
	    return real + "-" + -imaginary +"i";
	}
	return real + "+" + imaginary + "i";
    }
    
    /**
     * If c is null throws exception.
     * @param c ComplexNumber
     */
    private void checkIfNull(ComplexNumber c) {
	if(c == null) {
	    throw new NullPointerException();
	}
    }
/**
 * Calculates hashcode
 */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(imaginary);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(real);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }
/**
 * Compares two complex numbers by comparing their real and imaginary fields with E-6 precision.
 */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ComplexNumber other = (ComplexNumber) obj;
	if (Math.abs(real - other.getReal()) < THRESHOLD && Math.abs(imaginary - other.getImaginary()) < THRESHOLD) {
	    return true;
	}
	return false;
    }

}
