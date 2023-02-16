package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;

public class LinkedListIndexedCollectionTest {

    @Test
    public void defaultConstructorTest() {
	assertNotNull(new LinkedListIndexedCollection());
    }

    @Test
    public void collectionConstructorTest() {
	assertNotNull(new LinkedListIndexedCollection(new Collection()));
	assertThrows(NullPointerException.class, () -> {
	    new LinkedListIndexedCollection(null);
	});
    }

    @Test
    public void sizeTest() {
	LinkedListIndexedCollection collection = initiateCollection();
	assertEquals(6, collection.size());
	collection.remove(0);
	assertEquals(5, collection.size());
    }

    @Test
    public void addTest() {//!!!!!
	LinkedListIndexedCollection collection = initiateCollection();
	for(int i = 0; i < collection.size(); i++) {
	    assertEquals(i, (int)collection.get(i));
	}
    }
    
    @Test
    public void containsTest() {
	LinkedListIndexedCollection collection = initiateCollection();
	assertTrue(collection.contains(3));
	assertFalse(collection.contains(-1));
    }
    
    @Test
    public void removeBoolean() {
	LinkedListIndexedCollection collection = initiateCollection();
	assertTrue(collection.remove((Object)0));
	assertFalse(collection.remove((Object)7));
    }
    
    @Test
    public void removeVoid() {
	LinkedListIndexedCollection collection = initiateCollection();
	assertThrows(IndexOutOfBoundsException.class, () ->{
	    collection.remove(-1);
	});
	assertThrows(IndexOutOfBoundsException.class, () ->{
	    collection.remove(collection.size());
	});
	
	collection.remove(0);
	assertNotEquals(0, collection.get(0));
	
    }
    
    @Test
    public void toArray() {
	LinkedListIndexedCollection collection = initiateCollection();
	Object[] array = collection.toArray();
	for (int i = 0; i < collection.size(); i++) {
	    assertEquals(array[i], collection.get((int) i));
	}
    }
    
    @Test
    public void forEachTest() {
	LinkedListIndexedCollection collection = initiateCollection();
	collection.forEach(new Processor() {

	    @Override
	    public void process(Object value) {
		assertNotNull(value);
	    }
	});
    }
    
    @Test
    public void clearTest() {
	LinkedListIndexedCollection collection = initiateCollection();
	collection.clear();
	assertEquals(0, collection.size());
    }
    
    @Test
    public void getTest() {
	LinkedListIndexedCollection collection = initiateCollection();
	assertEquals(3, collection.get(3));
	assertThrows(IndexOutOfBoundsException.class, () ->{
	    collection.get(collection.size());
	});
	
	assertThrows(IndexOutOfBoundsException.class, () ->{
	    collection.get(-1);
	});
	
    }
    
    @Test
    public void insertTest() {
	LinkedListIndexedCollection collection = initiateCollection();
	
	assertThrows(IndexOutOfBoundsException.class, () ->{
	    collection.insert(9, collection.size()+1);
	});
	assertThrows(IndexOutOfBoundsException.class, () ->{
	    collection.insert(9, -1);
	});
	collection.insert(-5, 0);
	assertEquals(-5, collection.get(0));
	collection.insert(-5, 3);
	assertEquals(-5, collection.get(3));
	assertEquals(2, collection.get(4));
    }
    
    @Test
    public void indexOfTest() {
	LinkedListIndexedCollection collection = initiateCollection();
	assertEquals(-1, collection.indexOf("Štefica"));
	assertEquals(3, collection.indexOf((Object)3));
    }
    
    private LinkedListIndexedCollection initiateCollection() {
	LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
	collection.add(0);
	collection.add(1);
	collection.add(2);
	collection.add(3);
	collection.add(4);
	collection.add(5);
	return collection;
    }

}
