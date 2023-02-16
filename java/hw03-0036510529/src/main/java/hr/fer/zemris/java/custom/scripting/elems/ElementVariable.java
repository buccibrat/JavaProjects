package hr.fer.zemris.java.custom.scripting.elems;

import java.util.Objects;

/**
 * Element of Tokenizer type variable
 * @author Benjamin Kušen
 *
 */
public class ElementVariable extends Element {
    private String name;
    
    public ElementVariable(String name) {
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
	if (!(obj instanceof ElementVariable))
	    return false;
	ElementVariable other = (ElementVariable) obj;
	return Objects.equals(name, other.name);
    }
    
    
}
