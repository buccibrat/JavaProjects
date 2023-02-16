package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Calculates factorial of a number in range from 3 to 20
 * 
 * @author Benjamin Kušen
 */
public class Factorial {
    public static void main(String[] args) {
	/**
	 * Input recievd through the console.
	 */
	String input;
	/**
	 * Scanner that reads from console.
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * Integer value of input.
	 */
	int value;
	while (true) {
	    System.out.printf("Unesite broj > ");
	    input = sc.next();
	    if (input.equals("kraj")) {
		break;
	    }
	    try {
		value = Integer.parseInt(input);
		if (value >= 3 && value <= 20) {
		    System.out.printf("%d! = %d\n", value, factorial(value));
		} else {
		    System.out.printf("'%d' nije broj u dozvoljenom rasponu.\n", value);
		}
	    } catch (NumberFormatException e) {
		System.out.printf("'%s' nije cijeli broj.\n", input);
	    }
	}
	System.out.println("Doviđenja.");
	sc.close();
    }

    /**
     * Returns factorial of given number if number is within range of 0 to 20
     * 
     * @param input input number which factorial method calculates
     * @return returns factorial of input
     */
    public static Long factorial(int input) {
	if (input < 0 || input > 20) {
	    throw new IllegalArgumentException();
	}
	long returnValue = 1;
	if (input == 0) {
	    return returnValue;
	}

	for (int i = 1; i <= input; i++) {
	    returnValue = returnValue * i;
	}
	return returnValue;
    }
}