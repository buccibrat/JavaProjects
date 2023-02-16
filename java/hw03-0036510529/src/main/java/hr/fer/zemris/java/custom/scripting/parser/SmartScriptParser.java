package hr.fer.zemris.java.custom.scripting.parser;

import hr.fer.zemris.java.custom.scripting.lexer.Lexer;
import hr.fer.zemris.java.custom.scripting.lexer.LexerException;
import hr.fer.zemris.java.custom.scripting.lexer.LexerState;
import hr.fer.zemris.java.custom.scripting.lexer.Token;
import hr.fer.zemris.java.custom.scripting.lexer.TokenType;
import hr.fer.zemris.java.custom.scripting.nodes.*;
import hr.fer.zemris.java.custom.scripting.elems.*;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;

/**
 * Parses tokens that are recieved from lexer. Only variable types that this
 * Parser accepts are "for" and "end". For loop can have 3 or 4 arguments:
 * variable, startIndex, endIndex, step.
 * 
 * @author Benjamin Kušen
 *
 */
public class SmartScriptParser {

	/**
	 * Produces tokens
	 */
	private Lexer lexer;
	/**
	 * is root of parsed string
	 */
	DocumentNode documentNode;
	/**
	 * last token that lexer produced
	 */
	private Token token;
	/**
	 * Is true if token reads tag, false otherwise.
	 */
	private boolean inTag = false;
	/**
	 * Intern list used for caching elements.
	 */
	LinkedListIndexedCollection list = new LinkedListIndexedCollection();
	/**
	 * Internal stack used for building a tree
	 */
	private ObjectStack stack;

	public SmartScriptParser(String documentBody) {
		try {
			this.lexer = new Lexer(documentBody);
		} catch (NullPointerException e) {
			System.out.println("Izraz ne smije biti null");
			System.exit(-1);
		}
		startParse();
	}

	/**
	 * @return root node
	 */
	public DocumentNode getDocumentNode() {
		return documentNode;
	}

	/**
	 * Starts parsing immediately.
	 */
	private void startParse() {
		stack = new ObjectStack();
		documentNode = new DocumentNode();
		stack.push(documentNode);

		nextToken();

		while (token.getType() != TokenType.EOF) {
			if (token.getType() == TokenType.TAG) {
				changeTag();
				nextToken();
			} else {
				if (inTag == false) { // Reading TextNode
					if (token.getType() == TokenType.STRING) {
						readTextNode();
					}
				} else {
					if (token.getType() == TokenType.VARIABLE) { // Reading Variable

						if (token.getValue().toString().equalsIgnoreCase("for")) { // Reading ForLoopNode
							readForLoopNode();
						} else if (token.getValue().toString().equalsIgnoreCase("end")) {// Reading end inside tag
							readEnd();
						} else {
							throw new SmartScriptParserException();
						}
					} else {
						if (token.getType() == TokenType.EQUALSIGN) { // Reading equal sign
							readEqualSign();
						}
					}
				}
			}
		}
		try {
			stack.pop();
		} catch (EmptyStackException e) {
			throw new SmartScriptParserException();
		}
		if (!stack.isEmpty()) {
			throw new SmartScriptParserException();
		}
	}

	/**
	 * Reads textNode and adds it to the element at the peak of the stack.
	 */
	private void readTextNode() {
		((Node) stack.peak()).addChildNode(new TextNode(new ElementString(token.getValue().toString())));
		nextToken();
	}

	/**
	 * Reds for loop, pushes an pushes it to stack.
	 */
	private void readForLoopNode() {
		nextToken();
		while (token.getType() != TokenType.EOF && token.getType() != TokenType.TAG) {
			if (token.getType() == TokenType.STRING) {
				try {
					token = new Token(TokenType.CONSTANT_INTEGER, Integer.parseInt(token.getValue().toString()));
				} catch (NumberFormatException e) {
					try {
						token = new Token(TokenType.CONSTANT_DOUBLE, Double.parseDouble(token.getValue().toString()));
					} catch (NumberFormatException e2) {
						addElement();
						nextToken();
					}
				}
			} else {
				addElement();
				nextToken();
			}

		}
		if (token.getType() == TokenType.EOF) {
			throw new SmartScriptParserException();
		} else if (list.size() == 4 && list.get(0) instanceof ElementVariable) {
			ForLoopNode node = new ForLoopNode((ElementVariable) list.get(0), (Element) list.get(1),
					(Element) list.get(2), (Element) list.get(3));
			((Node) stack.peak()).addChildNode(node);
			stack.push(node);
			list.clear();
		} else if (list.size() == 3 && list.get(0) instanceof ElementVariable) {
			ForLoopNode node = new ForLoopNode((ElementVariable) list.get(0), (Element) list.get(1),
					(Element) list.get(2), null);
			((Node) stack.peak()).addChildNode(node);
			stack.push(node);
			list.clear();
		} else {
			throw new SmartScriptParserException();
		}
	}

	/**
	 * Reads end of nonempty tag and pops nonempty tag from stack
	 */
	private void readEnd() {
		nextToken();
		if (token.getType() == TokenType.TAG) {
			changeTag();
			stack.pop();
			nextToken();
		} else {
			throw new SmartScriptParserException();
		}
	}

	/**
	 * Reads equal sign in a tag and everything creates new echo node that contains
	 * everything in tag.
	 */
	private void readEqualSign() {
		nextToken();
		while (token.getType() != TokenType.EOF && token.getType() != TokenType.TAG) {
			addElement();
			nextToken();
		}
		if (token.getType() == TokenType.EOF) {
			throw new SmartScriptParserException();
		} else {
			((Node) stack.peak()).addChildNode(new EchoNode(toArray()));
			list.clear();
		}
	}

	/**
	 * Method that changes lexer's state
	 */
	private void changeTag() {
		if (token.getValue() == "{$") {
			inTag = true;
			lexer.setState(LexerState.TAG);
		} else {
			lexer.setState(LexerState.STRING);
			inTag = false;
		}
	}

	/**
	 * @return converts array of Objects to array of Element from echoNode
	 */
	private Element[] toArray() {
		Element[] elements = new Element[list.size()];
		for (int i = 0; i < list.size(); i++) {
			elements[i] = (Element) list.get(i);
		}
		return elements;
	}

	/**
	 * Takes next token from lexer
	 */
	private void nextToken() {
		try {
			token = lexer.nextToken();
		} catch (LexerException e) {
			throw new SmartScriptParserException();
		}
	}

	/**
	 * adds element to the internal list
	 */
	private void addElement() {
		switch (token.getType()) {
		case VARIABLE:
			list.add(new ElementVariable(token.getValue().toString()));
			break;
		case CONSTANT_INTEGER:
			list.add(new ElementConstantInteger((int) token.getValue()));
			break;
		case CONSTANT_DOUBLE:
			list.add(new ElementConstantDouble((double) token.getValue()));
			break;
		case EOF:
			break;
		case FUNCTION:
			list.add(new ElementFunction("@" + token.getValue().toString()));
			break;
		case OPERATOR:
			list.add(new ElementOperator(token.getValue().toString()));
			break;
		case STRING:
			list.add(new ElementString(token.getValue().toString()));
			break;
		default:
			break;
		}
	}

}
