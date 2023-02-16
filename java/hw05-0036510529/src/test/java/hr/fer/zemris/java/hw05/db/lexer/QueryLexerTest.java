package hr.fer.zemris.java.hw05.db.lexer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QueryLexerTest {
	@Test 
	public void lexerTest() {
		QueryLexer lexer = new QueryLexer(" jmbag=\"0000000003\"");
		Token[] correctData = {
				new Token(TokenType.ATTRIBUTE_NAME, "jmbag"),
				new Token(TokenType.OPERATOR, "="),
				new Token(TokenType.STRING_LITERAL, "0000000003")
		};
		
		checkTokenStream(lexer, correctData);
	}
	
	@Test 
	public void lexerTest2() {
		QueryLexer lexer = new QueryLexer("firstName>\"A\" and lastName LIKE \"B*ć\"");
		Token[] correctData = {
				new Token(TokenType.ATTRIBUTE_NAME, "firstName"),
				new Token(TokenType.OPERATOR, ">"),
				new Token(TokenType.STRING_LITERAL, "A"),
				new Token(TokenType.LOGICAL_OPERATOR, "and"),
				new Token(TokenType.ATTRIBUTE_NAME, "lastName"),
				new Token(TokenType.OPERATOR, "LIKE"),
				new Token(TokenType.STRING_LITERAL, "B*ć")
		};
		
		checkTokenStream(lexer, correctData);
	}
	@Test 
	public void lexerTest3() {
		
		assertThrows(QueryLexerException.class, () -> {
			QueryLexer lexer = new QueryLexer("firstName>\"A\"+");
			for(int i = 0; i < 4; i ++) {
			lexer.nextToken();}}); 
		
	}
	
	private void checkTokenStream(QueryLexer lexer, Token[] correctData) {
		int counter = 0;
		for(Token expected : correctData) {
			Token actual = lexer.nextToken();
			String msg = "Checking token "+counter + ":";
			assertEquals(expected.getType(), actual.getType(), msg);
			assertEquals(expected.getValue(), actual.getValue(), msg);
			counter++;
		}
	}
}
