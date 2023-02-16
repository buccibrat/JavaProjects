package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.collections.Collection;;

public class ArrayIndexedCollectionTest {
    @Test
    public void defaultConstructorNotNull() {
	assertNotNull(new ArrayIndexedCollection());
    }

    @Test
    public void initialCapacityConstructorThrows() {
	assertThrows(IllegalArgumentException.class, () -> new ArrayIndexedCollection(0));
    }

    @Test
    public void initialCapacityConstructorNotNull() {
	assertNotNull(new ArrayIndexedCollection(1));
    }

    @Test
    public void collectionConstructorThrows() {
	assertThrows(NullPointerException.class, () -> new ArrayIndexedCollection((Collection) null));
    }

    @Test
    public void collectionCounstructorNotNull() {
	assertNotNull(new ArrayIndexedCollection(new Collection()));
    }

    @Test
    public void initialCapacityCollectionConstructorThrows() {
	assertThrows(NullPointerException.class, () -> new ArrayIndexedCollection((Collection) null, 10));
    }

    @Test
    public void initialCapacityCollectionConstructorNotNull() {
	ArrayIndexedCollection collection = initiateCollection();
	assertNotNull(new ArrayIndexedCollection(collection, 2));
    }

    @Test
    public void size() {
	ArrayIndexedCollection collection = initiateCollection();
	assertEquals(5, collection.size());
    }

    @Test
    public void size2() {
	ArrayIndexedCollection collection = initiateCollection();
	assertEquals(5, collection.size());
    }

    @Test
    public void addAssertThrows() {

	assertThrows(NullPointerException.class, () -> {
	    ArrayIndexedCollection collection = new ArrayIndexedCollection();
	    collection.add(null);
	});
    }

    @Test
    public void add() {
	ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
	collection.add(1);
	assertEquals(1, collection.size());
	collection.add(1);
	assertEquals(2, collection.size());
	collection.add(1);
	assertEquals(3, collection.size());
    }

    @Test
    public void contains() {
	ArrayIndexedCollection collection = new ArrayIndexedCollection();
	collection.add(2);
	collection.add(5);
	collection.add(2);
	collection.add("žaba");
	assertTrue(collection.contains("žaba"));
	assertFalse(collection.contains("aba"));
	assertTrue(collection.contains(2));
	assertFalse(collection.contains(null));
    }

    @Test
    public void get() {
	ArrayIndexedCollection collection = initiateCollection();

	assertEquals(2, (int) collection.get(1));
	assertThrows(IndexOutOfBoundsException.class, () -> {
	    collection.get(-1);
	});
	assertThrows(IndexOutOfBoundsException.class, () -> {
	    collection.get(collection.size());
	});
    }

    @Test
    public void clear() {
	ArrayIndexedCollection collection = initiateCollection();

	collection.clear();
	assertEquals(5, collection.getCapacity());
	assertEquals(0, collection.size());
    }

    @Test
    public void insert() {
	ArrayIndexedCollection collection = initiateCollection();

	collection.insert(20, 1);
	assertEquals(20, collection.get(1));
	assertThrows(IndexOutOfBoundsException.class, () -> {
	    collection.insert(10, -1);
	});
	assertThrows(IndexOutOfBoundsException.class, () -> {
	    collection.insert(10, collection.size() + 1);
	});

    }

    @Test
    public void indexOf() {
	ArrayIndexedCollection collection = initiateCollection();
	assertEquals(-1, collection.indexOf(null));
	assertEquals(2, collection.indexOf(3));
	assertEquals(-1, collection.indexOf("Štefica"));
    }

    @Test
    public void removeVoid() { 
	ArrayIndexedCollection collection = initiateCollection();
	collection.remove((int) 2);
	assertEquals(4, collection.size());
	assertEquals(4, collection.get(2));

	assertThrows(IndexOutOfBoundsException.class, () -> {
	    collection.remove((int) -1);
	});
	assertThrows(IndexOutOfBoundsException.class, () -> {
	    collection.remove(collection.size());
	});
    }

    @Test
    public void removeBoolean() {
	ArrayIndexedCollection collection = initiateCollection();
	assertFalse(collection.remove((Object) 100));
	assertTrue(collection.remove((Object) 3));

    }

    @Test
    public void toArray() {
	ArrayIndexedCollection collection = initiateCollection();
	Object[] array = collection.toArray();
	for (int i = 0; i < collection.size(); i++) {
	    assertEquals(array[i], collection.get((int) i));
	}
    }

    @Test
    public void forEach() {
	ArrayIndexedCollection collection = new ArrayIndexedCollection();
	collection.forEach(new Processor() {

	    @Override
	    public void process(Object value) {
		assertEquals(null, value);
	    }
	});
    }

    @Test
    public void addAll() {
	ArrayIndexedCollection collection = initiateCollection();
	ArrayIndexedCollection anotherCollection = new ArrayIndexedCollection();
	anotherCollection.add(6);
	anotherCollection.add(7);
	collection.addAll(anotherCollection);
	for (int i = 1; i <= collection.size(); i++) {
	    assertEquals(i, collection.get((int) i - 1));
	}
    }

    public ArrayIndexedCollection initiateCollection() {
	ArrayIndexedCollection collection = new ArrayIndexedCollection(5);
	collection.add(1);
	collection.add(2);
	collection.add(3);
	collection.add(4);
	collection.add(5);
	return collection;
    }

    @Test
    public void getCapacity() {
	assertEquals(16, new ArrayIndexedCollection().getCapacity());
    }
}
