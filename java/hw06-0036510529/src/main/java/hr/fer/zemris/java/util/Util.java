package hr.fer.zemris.java.util;

import java.util.Objects;

/**
 * Utility class that converts byte array to string representation of hex value
 * and vice versa
 * 
 * @author Benjamin Kušen
 *
 */
public class Util {
	/**
	 * Converts string representation of hex value into byte array.
	 * 
	 * @param keyText
	 * @return byte array
	 */
	public static byte[] hextobyte(String keyText) {
		Objects.requireNonNull(keyText);
		int keyTextLength = keyText.length();
		if (keyTextLength == 0) {
			return new byte[0];
		}
		if (keyTextLength % 2 != 0) {
			throw new IllegalArgumentException();
		}
		byte[] byteArray = new byte[keyTextLength / 2];

		char[] keyTextArray = keyText.toCharArray();
		for (int i = 0; i < keyTextArray.length; i += 2) {
			if (checkChar(keyTextArray[i]) && checkChar(keyTextArray[i + 1])) {
				byteArray[i / 2] = (byte) ((Character.digit(keyTextArray[i], 16) << 4)
						+ Character.digit(keyTextArray[i + 1], 16));
			}
		}

		return byteArray;
	}

	/**
	 * Converts byte array into string representing it in hex value.
	 * 
	 * @param bytearray
	 * @return
	 */
	public static String bytetohex(byte[] bytearray) {
		StringBuilder bob = new StringBuilder();

		for (byte b : bytearray) {
			bob.append(String.format("%02x", b));
		}

		return bob.toString();
	}

	private static boolean checkChar(char c) {
		if (Character.isDigit(c) || c >= 'A' && c <= 'F' || c >= 'a' && c <= 'f') {
			return true;
		}

		throw new IllegalArgumentException();
	}
}
