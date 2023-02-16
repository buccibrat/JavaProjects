package hr.fer.zemris.java.custom.scripting.elems;

import java.util.Objects;

/**
 * Number element of tokenizer type Integer
 * @author Benjamin Kušen
 *
 */
public class ElementConstantInteger extends Element {
    /**
     * Value of number
     */
    private int value;
    
    /**
     * Accepts number type int and saves it in value field 
     * @param value saves it in value field
     */
    public ElementConstantInteger(int value) {
	super();
	this.value = value;
    }

    @Override
    public String asText() {
	return Integer.toString(value);
    }

    @Override
    public int hashCode() {
	return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof ElementConstantInteger))
	    return false;
	ElementConstantInteger other = (ElementConstantInteger) obj;
	return value == other.value;
    }
    
    
}
