package hr.fer.zemris.java.custom.scripting.nodes;

import java.util.Objects;

import hr.fer.zemris.java.custom.scripting.elems.ElementString;

/**
 * String type node
 * 
 * @author Benjamin Kušen
 *
 */
public class TextNode extends Node {
	/**
	 * ElementString of text node
	 */
	private ElementString text;

	/**
	 * sets ElementString to ElementString received by constructor
	 * 
	 * @param text
	 */
	public TextNode(ElementString text) {
		super();
		this.text = text;
	}

	/**
	 * @return returns string
	 */
	public ElementString getText() {
		return text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(text);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TextNode))
			return false;
		TextNode other = (TextNode) obj;
		return Objects.equals(text, other.text);
	}

}
