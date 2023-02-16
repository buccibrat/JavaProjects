package hr.fer.zemris.java.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilTest {
	@Test
	public void hexToByteTest() {
		String array = "00112233445566778899AABBCCDDEEFF";
		byte[] byteArrayTest = { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 };
		byte[] byteArray = Util.hextobyte(array);
		assertEquals(byteArrayTest.length, byteArray.length);
		for (int i = 0; i < array.length() / 2; i++) {
			assertEquals(byteArrayTest[i], byteArray[i]);
		}
		assertThrows(IllegalArgumentException.class, () -> {
			Util.hextobyte("a");
		});
		assertNotNull(Util.hextobyte(""));
		assertThrows(IllegalArgumentException.class, () -> {
			Util.hextobyte("ghjk");
		});
		byte[] byteArrayTest2 = { 1, -82, 34 };
		byteArray = Util.hextobyte("01aE22");
		for (int i = 0; i < byteArrayTest2.length; i++) {
			assertEquals(byteArrayTest2[i], byteArray[i]);
		}

	}
	@Test
	public void byteToHexTest() {
		assertEquals("01ae22", Util.bytetohex(new byte[] {1, -82, 34}));
		assertEquals("00112233445566778899aabbccddeeff", Util.bytetohex(new byte[] { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 }));
	}
}
