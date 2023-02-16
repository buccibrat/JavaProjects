package hr.fer.zemris.java.custom.scripting.elems;

import java.util.Objects;

/**
 * Element of tokenize that represents function.
 * @author Benjamin Kušen
 *
 */
public class ElementFunction extends Element {
    private String name;
    
    /**
     * Saves name as name of te function
     * @param name type string
     */
    public ElementFunction(String name) {
	super();
	this.name = name;
    }

    @Override
    public String asText() {
	return name;
    }

    @Override
    public int hashCode() {
	return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof ElementFunction))
	    return false;
	ElementFunction other = (ElementFunction) obj;
	return Objects.equals(name, other.name);
    }
    
    
}
