package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hr.fer.zemris.java.hw05.db.lexer.QueryLexerException;

public class QueryParserTest {
	@Test
	public void directQueryTest() {
		QueryParser parser = new QueryParser(" jmbag        =\"0123456789\"         ");
		assertTrue(parser.isDirectQuery());
		assertEquals("0123456789", parser.getQueriedJMBAG());
		assertEquals(1, parser.getQuery().size());
	}
	
	@Test
	public void directQueryThrowsTest() {
		QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName>\"J\"");
		assertFalse(qp2.isDirectQuery()); // false
		assertThrows(IllegalStateException.class, () -> {qp2.getQueriedJMBAG();});
		assertEquals(2, qp2.getQuery().size()); 
	}
	
	@Test
	public void QueryParserThrowsTest() {
		assertThrows(QueryParserException.class, () -> {QueryParser parser = new QueryParser("ddrm=\"0123456789\" and fmf>\"J\"");});
		assertThrows(QueryLexerException.class, () -> {QueryParser parser = new QueryParser("jmbag=\\\"0123456789\\\" and");});
		assertThrows(QueryLexerException.class, () -> {QueryParser parser = new QueryParser("jmbag=\\\"0123456789\\\" and jmbag");});
	}
}
