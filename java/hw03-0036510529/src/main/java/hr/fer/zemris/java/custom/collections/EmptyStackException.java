package hr.fer.zemris.java.custom.collections;
/**
 * Exception that is ment to be throw if the stack is empty
 * @author Benjamin Kušen
 *
 */
public class EmptyStackException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public EmptyStackException() {
	super();
    }
    /**
     * Constructor with message.
     * @param string message
     */
    public EmptyStackException(String string) {
	super(string);
	
    }
    
}
