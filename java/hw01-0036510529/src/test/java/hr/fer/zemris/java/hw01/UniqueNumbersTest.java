package hr.fer.zemris.java.hw01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hr.fer.zemris.java.hw01.UniqueNumbers;;

public class UniqueNumbersTest {
  
    @Test
    public void TreeSizeTest() {
	UniqueNumbers.TreeNode head = null;
	head = UniqueNumbers.addNode(head, 4);
	head = UniqueNumbers.addNode(head, 4);
	head = UniqueNumbers.addNode(head, 7);
	head = UniqueNumbers.addNode(head, 15);
	head = UniqueNumbers.addNode(head, -2);
	head = UniqueNumbers.addNode(head, 15);
	
	assertEquals(4, UniqueNumbers.treeSize(head));	
    }
    
    @Test
    public void addTest() {
	UniqueNumbers.TreeNode head = null;
	head = UniqueNumbers.addNode(head, 4);
	assertNotNull(head);	
    }
    
    @Test
    public void containsTestTrue() {
	UniqueNumbers.TreeNode head = null;
	head = UniqueNumbers.addNode(head, 4);
	head = UniqueNumbers.addNode(head, 4);
	head = UniqueNumbers.addNode(head, 7);
	head = UniqueNumbers.addNode(head, 15);
	head = UniqueNumbers.addNode(head, -2);
	head = UniqueNumbers.addNode(head, 15);
	
	assertTrue(UniqueNumbers.containsValue(head, 15));
    }
    
    @Test
    public void containsTestFalse() {
	UniqueNumbers.TreeNode head = null;
	head = UniqueNumbers.addNode(head, 4);
	head = UniqueNumbers.addNode(head, 4);
	head = UniqueNumbers.addNode(head, 7);
	head = UniqueNumbers.addNode(head, 15);
	head = UniqueNumbers.addNode(head, -2);
	head = UniqueNumbers.addNode(head, 15);
	
	assertFalse(UniqueNumbers.containsValue(head, 25));
    }
}
