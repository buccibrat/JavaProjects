package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hr.fer.zemris.java.hw05.db.lexer.QueryLexer;
import hr.fer.zemris.java.hw05.db.lexer.Token;
import hr.fer.zemris.java.hw05.db.lexer.TokenType;

/**
 * Class that parses input given with the command query.
 * 
 * @author Benjamin Kušen.
 *
 */
public class QueryParser {

	/**
	 * Lexer.
	 */
	private QueryLexer lexer;
	/**
	 * List that holds Conditional expressions.
	 */
	private List<ConditionalExpression> list;
	/**
	 * Last token received by lexer.
	 */
	private Token token;
	/**
	 * List of tokens. Used to remember last three tokens for comparison.
	 */
	private List<Token> listOfTokens;

	/**
	 * Initializes lexer and starts parsing.
	 * 
	 * @param input String that needs to be parsed 
	 */
	public QueryParser(String input) {
		try {
			lexer = new QueryLexer(input);
		} catch (NullPointerException e) {
			System.out.println("Unos nije valjan!");
		}
		list = new LinkedList<ConditionalExpression>();
		listOfTokens = new ArrayList<>();
		startParse();
	}
	
	/**
	 * Returns true if query only asks for retrieval of one record for one jmbag.
	 * 
	 * @return true if query is direct query, false otherwise
	 */
	public boolean isDirectQuery() {
		if (list.size() == 1 && list.get(0).getFieldGetter() == FieldValueGetters.JMBAG
				&& list.get(0).getComparisonOperator() == ComparisonOperators.EQUALS) {
			return true;
		}
		return false;
	}

	/**
	 * If query is direct query then returns jmbag in String type.
	 * 
	 * @return String
	 */
	public String getQueriedJMBAG() {
		if (!isDirectQuery()) {
			throw new IllegalStateException();
		}
		return list.get(0).getStringLiteral();
	}

	/**
	 * Returns list of conditional expressions.
	 * 
	 * @return
	 */
	public List<ConditionalExpression> getQuery() {
		return list;
	}

	/**
	 * Begines parsing.
	 */
	private void startParse() {
		token = lexer.nextToken();

		while (token.getType() != TokenType.EOF) {
			if (token.getType() == TokenType.ATTRIBUTE_NAME || token.getType() == TokenType.OPERATOR
					|| token.getType() == TokenType.STRING_LITERAL) {
				listOfTokens.add(token);
				token = lexer.nextToken();
				if (token.getType() == TokenType.EOF) {
					createConditionalExpression();
				}
			} else {
				createConditionalExpression();
				token = lexer.nextToken();
			}
		}
	}

	/**
	 * if token is correct type returns FieldValueGetter for that type, otherwise throws QueryParserException
	 * 
	 * @param token
	 * @return FieldValueGetters
	 */
	private IFieldValueGetter fieldValueGetters(Token token) {
		switch (token.getValue()) {
		case "firstName":
			return FieldValueGetters.FIRST_NAME;

		case "lastName":
			return FieldValueGetters.LAST_NAME;

		case "jmbag":
			return FieldValueGetters.JMBAG;

		default:
			throw new QueryParserException();
		}
	}

	/**
	 * if token is correct type returns ComparisonOperatr for that type, otherwise throws QueryParserException
	 * 
	 * @param token
	 * @return IComparisonOperator
	 */
	private IComparisonOperator comparisonOperators(Token token) {
		switch (token.getValue()) {
		case "<":
			return ComparisonOperators.LESS;

		case "<=":
			return ComparisonOperators.LESS_OR_EQUALS;

		case ">":
			return ComparisonOperators.GREATER;

		case ">=":
			return ComparisonOperators.GREATER_OR_EQUALS;

		case "=":
			return ComparisonOperators.EQUALS;

		case "!=":
			return ComparisonOperators.NOT_EQUALS;

		case "LIKE":
			return ComparisonOperators.LIKE;

		default:
			throw new QueryParserException();
		}
	}

	/**
	 * If order of tokens is correct creates ConditionalExpression and adds it to the list. Throws QueryParserException otherwise.
	 */
	private void createConditionalExpression() {
		if (listOfTokens.size() != 3 || listOfTokens.get(0).getType() != TokenType.ATTRIBUTE_NAME
				&& listOfTokens.get(1).getType() != TokenType.OPERATOR
				&& listOfTokens.get(2).getType() != TokenType.STRING_LITERAL) {
			throw new QueryParserException();

		} else {

			list.add(new ConditionalExpression(fieldValueGetters(listOfTokens.get(0)), listOfTokens.get(2).getValue(),
					comparisonOperators(listOfTokens.get(1))));
			listOfTokens.clear();

		}

	}
}
