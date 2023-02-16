package hr.fer.zemris.java.custom.scripting.nodes;

import java.util.Objects;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;

/**
 * For loop node
 * 
 * @author Benjamin Kušen
 *
 */
public class ForLoopNode extends Node {
	/**
	 * variable of loop
	 */
	private ElementVariable variable;
	/**
	 * start of loop
	 */
	private Element startExpression;
	/**
	 * end of loop
	 */
	private Element endExpression;
	/**
	 * Step of loop, can be null, default is 1
	 */
	private Element stepExpression;

	/**
	 * Receives variable, start expression, end expression and step expression, and
	 * adds them to their respective attributes.
	 * 
	 * @param variable        must be type of ElementVariable
	 * @param startExpression can be variable number or string
	 * @param endExpression   can be variable number or string
	 * @param stepExpression  can be null
	 */
	public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression,
			Element stepExpression) {

		if (variable == null || startExpression == null || endExpression == null) {
			throw new NullPointerException();
		}
		this.variable = variable;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		this.stepExpression = stepExpression;
	}

	/**
	 * @return variable
	 */
	public ElementVariable getVariable() {
		return variable;
	}

	/**
	 * @return startExpression
	 */
	public Element getStartExpression() {
		return startExpression;
	}

	/**
	 * @return endExpression
	 */
	public Element getEndExpression() {
		return endExpression;
	}

	/**
	 * @return stepExpression
	 */
	public Element getStepExpression() {
		return stepExpression;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(endExpression, startExpression, stepExpression, variable);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof ForLoopNode))
			return false;
		ForLoopNode other = (ForLoopNode) obj;
		return Objects.equals(endExpression, other.endExpression)
				&& Objects.equals(startExpression, other.startExpression)
				&& Objects.equals(stepExpression, other.stepExpression) && Objects.equals(variable, other.variable);
	}

}
