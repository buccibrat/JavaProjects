package hr.fer.zemris.java.custom.scripting.elems;

import java.util.Objects;

/**
 * Element of tokenizer type string
 * @author Benjamin Kušen
 *
 */
public class ElementString extends Element {
    /**
     * Value of string.
     */
	private String value;
    /**
     * Sets value of string input.
     * 
     * @param value string
     */
    public ElementString(String value) {
	super();
	this.value = value;
    }
    
    @Override
    public String asText() {
	return value;
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
	if (!(obj instanceof ElementString))
	    return false;
	ElementString other = (ElementString) obj;
	return Objects.equals(value, other.value);
    }
    
    
}
