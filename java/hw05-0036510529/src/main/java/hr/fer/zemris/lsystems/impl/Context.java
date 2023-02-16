package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Similar to stack
 * 
 * @author Benjamin Kušen
 *
 */
public class Context {
	/**
	 * Stack
	 */
	ObjectStack<TurtleState> stack;
	
	/**
	 * Initializes stack.
	 */
	public Context() {
		stack = new ObjectStack<TurtleState>();
	}
	
	/**
	 * Returns element at top of the stack without poping it.
	 * 
	 * @return
	 */
	public TurtleState getCurrentState() {
		return stack.peak();
	}
	/**
	 * Pushes state on stack.
	 * 
	 * @param state
	 */
	public void pushState(TurtleState state) {
		stack.push(state);
	}
	/**
	 * Pops state from stack.
	 */
	public void popState() {
		stack.pop();
	}
}
