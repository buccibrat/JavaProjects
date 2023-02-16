package hr.fer.zemris.java.custom.scripting.parser;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SmartScriptParserTest {

    @Test
    public void ParserTest1() {
	assertThrows(SmartScriptParserException.class, () -> {new SmartScriptParser("{$ FOR 1 1 $}");});
	
    }
    
    @Test
    public void ParserTest2() {
	assertThrows(SmartScriptParserException.class, () -> {new SmartScriptParser("{$ FOR 1 1 1 1 1 $}");});
	
    }
    
    @Test
    public void ParserTest3() {
	assertThrows(SmartScriptParserException.class, () -> {new SmartScriptParser("{$OR$}");});
	
    }
    
    @Test
    public void ParserTest4() {
	assertThrows(SmartScriptParserException.class, () -> {new SmartScriptParser("{$foR 1.2.2.2.3.1.1$}");});
    }
    
    @Test
    public void ParserTest5() {
	assertThrows(SmartScriptParserException.class, () -> {new SmartScriptParser("\\9");});
    }
    
    @Test
    public void ParserTest6() {
	assertThrows(SmartScriptParserException.class, () -> {new SmartScriptParser("{$=\\9}");});
    }

}
