package hr.fer.zemris.java.custom.scripting.elems;

import java.util.Objects;

/**
 * Number element of tokenizer. Number is the type of double
 * @author Benjamin Kušen
 *
 */
public class ElementConstantDouble extends Element {
    
    /**
     * Double value of element
     */
    double value;
    
    /**
     * Accepts double value and saves it in value variable
     * @param value
     */
    public ElementConstantDouble(double value) {
	super();
	this.value = value;
    }

    @Override
    public String asText() {
	return Double.toString(value);
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
	if (!(obj instanceof ElementConstantDouble))
	    return false;
	ElementConstantDouble other = (ElementConstantDouble) obj;
	return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
    }
    
    
}
