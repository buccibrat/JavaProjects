package hr.fer.zemris.java.custom.scripting.lexer;

public enum TokenType {
    /**
     * String is variable if it begins with letter
     */
    VARIABLE, 
    /**
     * represents integer 
     */
    CONSTANT_INTEGER, 
    /**
     * represents double
     */
    CONSTANT_DOUBLE, 
    /**
     * represents String, if it is in tag than it can accept numbers and symbols, but only \" and \\ 
     */
    STRING, 
    /**
     * Begins with @, followed by letter. 
     */
    FUNCTION, 
    /**
     *  can be +, -, *, /, ^
     */
    OPERATOR, 
    /**
     * sign that indicates begining of echo 
     */
    EQUALSIGN, 
    /**
     * is represented by {$ or $}; 
     */
    TAG, 
    /**
     * end of file 
     */
    EOF;
}
