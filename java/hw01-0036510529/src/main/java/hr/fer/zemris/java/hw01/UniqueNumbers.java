package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Creates a tree structure from user input and writes it out from smallest to
 * biggest number and in reverse
 * 
 * @author Benjamin Kušen
 */

public class UniqueNumbers {

    public static void main(String[] args) {
	TreeNode head = null;
	String input;
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.printf("Unesite broj > ");
	    input = sc.next();
	    if (input.equals("kraj")) {
		break;
	    }
	    try {
		Integer.parseInt(input);
		if (containsValue(head, Integer.parseInt(input))) {
		    System.out.println("Broj već postoji. Preskačem.");
		} else {
		    head = addNode(head, Integer.parseInt(input));
		    System.out.println("Dodano.");
		}
	    } catch (NumberFormatException e) {
		System.out.println("'" + input + "' nije cijeli broj.");
	    }

	} while (true);
	sc.close();

	fromSmallest(head);
	System.out.println();
	fromBiggest(head);

    }

    /**
     * Adds value to the tree
     * 
     * @param head  root of tree
     * @param value that we want to add to the tree
     * @return root of the tree
     */
    public static TreeNode addNode(TreeNode head, int value) {
	if (head == null) {
	    head = new TreeNode();
	    head.value = value;
	    return head;
	}
	if (value < head.value) {
	    head.left = addNode(head.left, value);
	    return head;
	} else if (value > head.value) {
	    head.right = addNode(head.right, value);
	    return head;
	} else
	    return head;

    }

    /**
     * Calculates size of tree
     * 
     * @param head root of tree
     * @return size of tree
     */
    public static int treeSize(TreeNode head) {
	if (head == null) {
	    return 0;
	}
	return 1 + treeSize(head.left) + treeSize(head.right);
    }

    /**
     * checks if value is contained within the tree
     * 
     * @param head  root of tree
     * @param value that we check if it exists
     * @return <code>true</code> if value is contained within tree,
     *         <code>false</code> otherwise
     */
    public static boolean containsValue(TreeNode head, int value) {
	if (head == null) {
	    return false;
	}

	if (head.value == value) {
	    return true;
	}

	return containsValue(head.left, value) || containsValue(head.right, value);
    }

    /**
     * Prints out all values in a tree from smallest value to largest
     * 
     * @param head root of tree
     */
    public static void fromSmallest(TreeNode head) {
	if (head == null) {
	    return;
	}
	fromSmallest(head.left);
	System.out.printf("%d ", head.value);
	fromSmallest(head.right);

    }

    /**
     * Prints out all values in a tree from largest value to smallest
     * 
     * @param head root of tree
     */
    public static void fromBiggest(TreeNode head) {
	if (head == null) {
	    return;
	}

	fromBiggest(head.right);
	System.out.printf("%d ", head.value);
	fromBiggest(head.left);
    }

    /**
     * Class that represents node of a tree
     * 
     * @author Benjamin Kušen
     *
     */
    public static class TreeNode {
	TreeNode left;
	TreeNode right;
	int value;
    }

}
