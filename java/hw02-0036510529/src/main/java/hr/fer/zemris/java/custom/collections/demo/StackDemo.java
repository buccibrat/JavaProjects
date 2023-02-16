package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Class that gives small demo of how <code>ObjectStack</code> class could be
 * used.
 * 
 * @author Benjamin Kušen
 *
 */
public class StackDemo {

    /**
     * Shows a small demo wher instance of <code>ObjectStack</code> class is used
     * for simple postfix mathematical operations.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
	if (args.length != 1) {
	    System.out.println("Broj argumenata nije valjan");
	    return;
	}

	String[] strings = args[0].split(" ");
	ObjectStack stack = new ObjectStack();
	for (String s : strings) {
	    try {
		stack.push(Integer.parseInt(s));
	    } catch (NumberFormatException e) {
		try {
		    int numberOne = (int) stack.pop();
		    int numberTwo = (int) stack.pop();
		    if (s.equals("+")) {
			stack.push(numberTwo + numberOne);
		    } else if (s.equals("-")) {
			stack.push(numberTwo - numberOne);
		    } else if (s.equals("%")) {
			if (numberOne == 0) {
			    throw new IllegalArgumentException();
			}
			stack.push(numberTwo % numberOne);
		    } else if (s.equals("*")) {
			stack.push(numberTwo * numberOne);
		    } else if (s.equals("/")) {
			if (numberOne == 0) {
			    throw new IllegalArgumentException();
			}
			stack.push(numberTwo / numberOne);
		    }
		} catch (EmptyStackException | IllegalArgumentException ex) {
		    System.out.println("Vaš Izraz nije valjan.");
		    System.exit(-1);
		}
	    }
	}
	if (stack.size() != 1) {
	    System.out.println("Error");
	} else {
	    System.out.println("Rješenje je " + stack.pop());
	}
    }
}
