package hr.fer.zemris.java.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Class that encrypts and decrypts data using the AES cryptoalgorithm and the
 * 128-bit encryption key
 * 
 * @author Benjamin Kušen
 *
 */
public class Crypto {
	/**
	 * Size of buffer array
	 */
	private static final int BUFFER_SIZE = 4096;
	/**
	 * Source path
	 */
	private static Path path;

	/**
	 * Main method that receives arguments from command line. Like this: "java
	 * hr.fer.zemris.java.hw06.crypto.Crypto checksha" hw06test.bin
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Input is invalid!");
		} else if (args.length == 2 && args[0].endsWith("checksha")) {
			path = Paths.get(args[1]);
			digest();
		} else if (args.length == 3 && args[0].endsWith("encrypt")) {
			path = Paths.get(args[1]);
			run(Paths.get(args[2]), true);
		} else if (args.length == 3 && args[0].endsWith("decrypt")) {
			path = Paths.get(args[1]);
			run(Paths.get(args[2]), false);
		} else {
			System.err.println("Input is invalid!");
		}
	}

	/**
	 * Starts encryption/decryption
	 * 
	 * @param outFile file in which encrypted/decrypted data is written
	 * @param encrypt if true then source will be encrypted, if false then source
	 *                will be decrypted
	 */
	public static void run(Path outFile, boolean encrypt) {
		System.out.printf("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):%n> ");
		String keyString = readText();
		byte[] key = Util.hextobyte(keyString);
		System.out.printf("Please provide initialization vector as hex-encoded text (32 hex-digits):%n> ");
		String ivString = readText();
		byte[] iv = Util.hextobyte(ivString);
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException e) {
			System.exit(-1);
		}
		boolean status = encryptDecrypt(outFile, cipher);
		if (status) {
			System.out.print(encrypt ? "Encryption" : "Decryption");
			System.out.printf(" completed. Generated file %s based on file %s.%n", outFile.getFileName(),
					path.getFileName());
		}
	}
	/**
	 * Uses cipher and streams to create new encrypted/decrypted file
	 * @param outFile
	 * @param cipher
	 * @return
	 */
	private static boolean encryptDecrypt(Path outFile, Cipher cipher) {
		try {
			InputStream input = Files.newInputStream(path, StandardOpenOption.CREATE_NEW);
			OutputStream output = Files.newOutputStream(outFile, StandardOpenOption.CREATE_NEW);
			byte[] buffer = new byte[BUFFER_SIZE];
			int read = 0;
			while ((read = input.read(buffer)) != -1) {
				byte[] current = cipher.update(buffer, 0, read);
				if (current != null) {
					output.write(current);
				}
			}
			byte[] current = cipher.doFinal();
			if (current != null) {
				output.write(current);
			}
		} catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
			return false;
		}
		return true;
	}
	/**
	 * Digests input
	 */
	private static void digest() {
		byte[] inputBytes = null;
		MessageDigest digest = null;

		try (FileInputStream input = new FileInputStream(path.toString())) {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] buffer = new byte[4096];
			int readBytes = 0;
			while ((readBytes = input.read(buffer)) != -1) {
				digest.update(buffer, 0, readBytes);
			}
			inputBytes = digest.digest();
		} catch (IOException | NoSuchAlgorithmException e) {
			System.exit(-1);
		}
		System.out.printf("Please provide expected sha signature for file %s:%n> ", path.getFileName());
		byte[] digestedBytes = Util.hextobyte(readText());
		System.out.printf("Digesting completed. Digest of %s ", path.getFileName());
		if (Arrays.equals(inputBytes, digestedBytes)) {
			System.out.println("matches expected digest.");
		} else {
			System.out.println("does not match the expected digest. Digest was:" + Util.bytetohex(inputBytes));
		}
	}
	/**
	 * Reads input and checkes if all characters are hex.
	 * @return input
	 */
	private static String readText() {
		String input = null;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new BufferedInputStream(System.in), StandardCharsets.UTF_8));
		try {
			input = reader.readLine();
			Objects.requireNonNull(input);
			input = input.trim();
			if (!input.matches("[0-9a-fA-f]+")) {
				System.err.println("Illegal hex string");
				System.exit(-1);
			}

		} catch (IOException e) {
			System.exit(-1);
		}
		return input;
	}
}
