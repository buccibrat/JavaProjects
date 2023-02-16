package hr.fer.zemris.java.custom.scripting.nodes;

import java.util.Arrays;

import hr.fer.zemris.java.custom.scripting.elems.Element;

/**
 * Node type echo
 * 
 * @author Benjamin Kušen
 *
 */
public class EchoNode extends Node {
	/**
	 * Array of elements contained in echo node, can be variable, string, operator
	 * or number
	 */
	private Element[] elements;

	/**
	 * Initializes elements.
	 * 
	 * @param elements
	 */
	public EchoNode(Element[] elements) {
		this.elements = elements;
	}

	/**
	 * Returs reference to array of elements
	 * 
	 * @return
	 * 
	 */
	public Element[] getElements() {
		return elements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(elements);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof EchoNode))
			return false;
		EchoNode other = (EchoNode) obj;
		return Arrays.equals(elements, other.elements);
	}

}
