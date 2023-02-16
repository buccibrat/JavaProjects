package hr.fer.zemris.java.custom.scripting.lexer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LexerTest {

    @Test
    public void constructorTest() {
	Lexer lexer = new Lexer("");
	assertNotNull(lexer.nextToken());
    }

    @Test
    public void constructorNullInput() {
	assertThrows(NullPointerException.class, () -> {
	    new Lexer(null);
	});
    }

    @Test
    public void testEmpty() {
	Lexer lexer = new Lexer("");

	assertEquals(TokenType.EOF, lexer.nextToken().getType());
    }

    @Test
    public void testGetReturnsLastNext() {
	// Calling getToken once or several times after calling nextToken must return
	// each time what nextToken returned...
	Lexer lexer = new Lexer("");

	Token token = lexer.nextToken();
	assertEquals(token, lexer.getToken(), "getToken returned different token than nextToken.");
	assertEquals(token, lexer.getToken(), "getToken returned different token than nextToken.");
    }

    @Test
    public void testRadAfterEOF() {
	Lexer lexer = new Lexer("");

	// will obtain EOF
	lexer.nextToken();
	// will throw!
	assertThrows(LexerException.class, () -> lexer.nextToken());
    }

    @Test
    public void testNoActualContent() {
	// When input is only of spaces, tabs, newlines, etc...
	Lexer lexer = new Lexer("   \r\n\t    ");

	assertEquals(TokenType.STRING, lexer.nextToken().getType(),
		"Input had no content. Lexer should generated only EOF token.");
    }

    @Test
    public void testFor() {
	// Lets check for several words...
	Lexer lexer = new Lexer("{$ FOR 3 1 10 1 $}");

	lexer.setState(LexerState.TAG);
	// We expect the following stream of tokens
	Token correctData[] = { new Token(TokenType.TAG, "{$"), new Token(TokenType.VARIABLE, "FOR"),
		new Token(TokenType.CONSTANT_INTEGER, 3), new Token(TokenType.CONSTANT_INTEGER, 1),
		new Token(TokenType.CONSTANT_INTEGER, 10), new Token(TokenType.CONSTANT_INTEGER, 1),
		new Token(TokenType.TAG, "$}"), new Token(TokenType.EOF, null), };

	checkTokenStream(lexer, correctData);
    }

    @Test
    public void testForDouble() {
	// Lets check for several words...
	Lexer lexer = new Lexer("{$= i i * @sin \"0.000\" @decfmt $}");

	lexer.setState(LexerState.TAG);
	// We expect the following stream of tokens
	Token correctData[] = { new Token(TokenType.TAG, "{$"), new Token(TokenType.EQUALSIGN, "="),
		new Token(TokenType.VARIABLE, "i"), new Token(TokenType.VARIABLE, "i"),
		new Token(TokenType.OPERATOR, "*"), new Token(TokenType.FUNCTION, "sin"),
		new Token(TokenType.CONSTANT_DOUBLE, 0.000), new Token(TokenType.FUNCTION, "decfmt"),
		new Token(TokenType.TAG, "$}"), };

	checkTokenStream(lexer, correctData);
    }

    @Test
    public void testEnd() {
	// Lets check for several words...
	Lexer lexer = new Lexer("{$END$}");
	lexer.nextToken();
	lexer.setState(LexerState.TAG);
	assertEquals("END", lexer.nextToken().getValue());
	assertEquals(TokenType.VARIABLE, lexer.getToken().getType());
    }

    
    @Test
    public void testEcho() {
	// Lets check for several words...
	Lexer lexer = new Lexer("{$ FOR 3 1 10 1 $}");

	lexer.setState(LexerState.TAG);
	// We expect the following stream of tokens
	Token correctData[] = { new Token(TokenType.TAG, "{$"), new Token(TokenType.VARIABLE, "FOR"),
		new Token(TokenType.CONSTANT_INTEGER, 3), new Token(TokenType.CONSTANT_INTEGER, 1),
		new Token(TokenType.CONSTANT_INTEGER, 10), new Token(TokenType.CONSTANT_INTEGER, 1),
		new Token(TokenType.TAG, "$}"), new Token(TokenType.EOF, null), };

	checkTokenStream(lexer, correctData);
    }
    
    @Test
    public void testVariable() {
	// Lets check for several words...
	Lexer lexer = new Lexer("{$ FOR i i * @sin 10 $}");

	lexer.setState(LexerState.TAG);
	// We expect the following stream of tokens
	Token correctData[] = { new Token(TokenType.TAG, "{$"), new Token(TokenType.VARIABLE, "FOR"),
		new Token(TokenType.VARIABLE, "i"), new Token(TokenType.VARIABLE, "i"),
		new Token(TokenType.OPERATOR, "*"), new Token(TokenType.FUNCTION, "sin"), new Token(TokenType.CONSTANT_INTEGER, 10),
		new Token(TokenType.TAG, "$}"), new Token(TokenType.EOF, null), };

	checkTokenStream(lexer, correctData);
    }
    @Test 
    public void testForWithVariableAndString() {
	// Lets check for several words...
	Lexer lexer = new Lexer("{$ FOR i \"1\" 10 1 $}");

	lexer.setState(LexerState.TAG);
	// We expect the following stream of tokens
	Token correctData[] = { new Token(TokenType.TAG, "{$"), new Token(TokenType.VARIABLE, "FOR"),
		new Token(TokenType.VARIABLE, "i"), new Token(TokenType.CONSTANT_INTEGER, 1),
		new Token(TokenType.CONSTANT_INTEGER, 10), new Token(TokenType.CONSTANT_INTEGER, 1),
		new Token(TokenType.TAG, "$}"), new Token(TokenType.EOF, null),};

	checkTokenStream(lexer, correctData);
    }

    private void checkTokenStream(Lexer lexer, Token[] correctData) {
	int counter = 0;
	for (Token expected : correctData) {
	    Token actual = lexer.nextToken();
	    String msg = "Checking token " + counter + ":";
	    assertEquals(expected.getType(), actual.getType(), msg);
	    assertEquals(expected.getValue(), actual.getValue(), msg);
	    counter++;
	}
    }

}
