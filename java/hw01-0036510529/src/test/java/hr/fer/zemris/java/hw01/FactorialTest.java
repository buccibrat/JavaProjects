package hr.fer.zemris.java.hw01;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hr.fer.zemris.java.hw01.Factorial;
public class FactorialTest {
    @Test
    public void factorialTest() {
	assertEquals(1, Factorial.factorial(0));
	assertEquals(1, Factorial.factorial(1));
	assertEquals(2432902008176640000L, Factorial.factorial(20));
	assertThrows(IllegalArgumentException.class, () -> {
	    Factorial.factorial(-1);
	});
	
	assertThrows(IllegalArgumentException.class, () -> {
	    Factorial.factorial(21);
	});
    }
}
