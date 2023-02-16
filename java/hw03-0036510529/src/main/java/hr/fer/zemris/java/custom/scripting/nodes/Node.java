package hr.fer.zemris.java.custom.scripting.nodes;

import java.util.Objects;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;;

/**
 * Basic node type, Has internal collection that holds other nodes;
 * 
 * @author Benjamn Kušen
 *
 */
public class Node {
	/**
	 * Collection of nodes of this node.
	 */
	private ArrayIndexedCollection collection;

	/**
	 * Default constructor.
	 */
	public Node() {
	}

	/**
	 * Adds node to collection.
	 * 
	 * @param child node
	 */
	public void addChildNode(Node child) {
		if (collection == null) {
			collection = new ArrayIndexedCollection();
		}
		collection.add(child);
	}

	/**
	 * @return number of nodes that collection has reference to
	 */
	public int numberOfChildern() {
		return collection.size();
	}

	/**
	 * @param index
	 * @return returns child at a specific index
	 */
	public Node getChild(int index) {
		return (Node) collection.get(index);
	}

	@Override
	public int hashCode() {
		return Objects.hash(collection);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		return Objects.equals(collection, other.collection);
	}

}
