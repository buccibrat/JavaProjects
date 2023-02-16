package hr.fer.zemris.java.custom.scripting.elems;

import java.util.Objects;

/**
 * Element of tokenizer type operator
 * @author Benjamin Kušen
 *
 */
public class ElementOperator extends Element {
    /**
     * Contains one operator
     */
    private String symbol;

    /**
     * Adds operator symbol
     * @param operator
     */
    public ElementOperator(String operator) {
	super();
	this.symbol = operator;
    }
    
    @Override
    public String asText() {
        return symbol;
    }

    @Override
    public int hashCode() {
	return Objects.hash(symbol);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof ElementOperator))
	    return false;
	ElementOperator other = (ElementOperator) obj;
	return Objects.equals(symbol, other.symbol);
    }
    
    
}
