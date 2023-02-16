package lab4;

import java.util.Scanner;

public class Parser {

	private static int index = 0;
	private static String input;
	private static StringBuilder bob = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		input = sc.next();

		obradiS(false);
		System.out.println(bob.toString());
		if (index < input.length()) {
			System.out.println("NE");
		} else {
			System.out.println("DA");
		}
	}

	private static void obradiS(boolean obradujemSeUB) {
		bob.append("S");
		if (index >= input.length()) {
			System.out.println(bob.toString());
			System.out.println("NE");
			System.exit(-1);
		}
		char c = input.charAt(index++);
		if (c == 'b' && input.charAt(index) == 'c' && obradujemSeUB == true) {
			return;
		}
		if (c == 'a') {
			obradiA();
			obradiB();
			if (index >= input.length() && obradujemSeUB == true) {
				System.out.println(bob.toString());
				System.out.println("NE");
				System.exit(-1);
			} else if (index >= input.length() && obradujemSeUB == false) {
				return;
			} else if (index >= input.length() - 1) {
				System.out.println(bob.toString());
				System.out.println("NE");
				System.exit(-1);
			}
			c = input.charAt(index);
			if (c == 'b' && input.charAt(index + 1) == 'c') {
				if (obradujemSeUB == true) {
					index += 2;
					return;
				} else {
					System.out.println(bob.toString());
					System.out.println("NE");
					System.exit(-1);
				}
			}
		} else if (c == 'b') {
			obradiB();
			obradiA();
			if (index >= input.length() && obradujemSeUB == true) {
				System.out.println(bob.toString());
				System.out.println("NE");
				System.exit(-1);
			} else if (index >= input.length() && obradujemSeUB == false) {
				return;
			} else if (index >= input.length() - 1) {
				System.out.println(bob.toString());
				System.out.println("NE");
				System.exit(-1);
			}
			c = input.charAt(index);
			if (c == 'b' && input.charAt(index + 1) == 'c') {
				if (obradujemSeUB == true) {
					index += 2;
					return;
				} else {
					System.out.println(bob.toString());
					System.out.println("NE");
					System.exit(-1);
				}
			}
		} else {
			System.out.println(bob.toString());
			System.out.println("NE");
			System.exit(-1);
		}
	}

	private static void obradiA() {
		bob.append("A");
		if (index >= input.length()) {
			System.out.println(bob.toString());
			System.out.println("NE");
			System.exit(-1);
		}
		char c = input.charAt(index++);
		if (c == 'b') {
			obradiC();
		} else if (c == 'a') {
			return;
		} else {
			System.out.println(bob.toString());
			System.out.println("NE");
			System.exit(-1);
		}
	}

	private static void obradiB() {
		bob.append("B");
		if (index >= input.length()) {
			System.out.println(bob.toString());
			System.out.println("DA");
			System.exit(-1);
		}
		char c = input.charAt(index++);
		if (c == 'c' && input.charAt(index) == 'c') {
			index++;
			obradiS(true);
		} else {
			index--;
			return;
		}

	}

	private static void obradiC() {
		bob.append("C");
		obradiA();
		obradiA();
	}

}
