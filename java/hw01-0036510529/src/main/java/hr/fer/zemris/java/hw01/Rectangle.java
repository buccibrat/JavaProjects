package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Receives lengths of sides of a rectangle from arguments or console, if input
 * is correct it calculates area and circumference of a rectangle
 * 
 * @author Benjamin Kušen
 *
 */

public class Rectangle {
    /**
     * Main method that checks if arguments are valid. If there are only two
     * arguments method takes them and calculates area and circumstance. If there
     * are no arguments method asks for user input from console.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);

	if (args.length == 2) {
	    if (isValidNumber(args[0]) && isValidNumber(args[1]))
		print(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
	} else if (args.length == 0) {
	    print(scanner(sc, "širinu"), scanner(sc, "visinu "));
	} else {
	    System.out.println("Broj argumenata nije valjan.");
	}
	sc.close();
    }

    /**
     * Method that scans input from console and returns when input is double.
     * 
     * @param sc scanner
     * @param s  string that will be printed out before user input
     * @return double
     */
    public static double scanner(Scanner sc, String s) {
	while (true) {
	    System.out.printf("Unesite %s > ", s);
	    String input = sc.next();
	    if (isValidNumber(input)) {
		return Double.parseDouble(input);

	    }
	}
    }

    /**
     * Checks if the input is valid
     * 
     * @param input <code>String</code> that we want to check if it is a number, and
     *              if it is a valid number
     * @return <code>true</code> if number is valid, <code>false</code> otherwise
     */
    public static boolean isValidNumber(String input) {
	try {
	    double number = Double.parseDouble(input);
	    if (number >= 0)
		return true;
	    else
		System.out.println("Unijeli ste negativnu vrijednost");
	    return false;
	} catch (NumberFormatException e) {
	    System.out.println("'" + input + "'" + " se ne može protumačiti kao broj.");
	}

	return false;
    }

    /**
     * Prints out area and circumference of a rectangle.
     * 
     * @param a width of rectangle
     * @param b height of rectangle
     */
    public static void print(double a, double b) {
	System.out.printf("Pravokutni širine %.1f i visine %.1f ima površinu %.1f te opseg %.1f", a, b, a * b,
		2 * a + 2 * b);
    }

}
