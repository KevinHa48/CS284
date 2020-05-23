
/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 * Section: CS 284 A
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {
	
	
	@Test
	void testAddIntE() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dblink.add(-1, 2));
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dblink.add(69, 2));
		
		dblink.add(0, 4);
		assertEquals("[4]", dblink.toString());
		dblink.add(dblink.size(), 6);
		assertEquals("[4,6]", dblink.toString());
		dblink.add(0, 100);
		assertEquals("[100,4,6]", dblink.toString());
		dblink.add(1, 69);
		assertEquals("[100,69,4,6]", dblink.toString());
		dblink.add(4, 1337);
		assertEquals("[100,69,4,6,1337]", dblink.toString());
		
	}
	
	@Test
	void testAddE() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		dblink.add(420);
		assertEquals("[420]", dblink.toString());
		dblink.add(1048596);
		assertEquals("[1048596,420]", dblink.toString());
		
	}
	
	@Test
	void testAppend() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		dblink.append(177013);
		assertEquals("[177013]", dblink.toString());
		dblink.append(19);
		assertEquals("[177013,19]", dblink.toString());
	}
	
	@Test
	void testGet() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		assertThrows(IllegalStateException.class, () -> dblink.get(0));
		dblink.add(1);
		dblink.add(11);
		dblink.add(111);
		dblink.add(1111);
		assertEquals(111, dblink.get(1));
		assertEquals(11, dblink.get(2));
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dblink.get(-1));
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dblink.get(dblink.size()));
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> dblink.get(9001));
		
	}
	
	@Test
	void testGetHead() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		assertThrows(IllegalStateException.class, () -> dblink.getHead());
		dblink.add(69);
		dblink.add(96);
		dblink.add(420);
		dblink.add(80000);
		assertEquals(80000, dblink.getHead());
		dblink.add(0, 20);
		assertEquals(20, dblink.getHead());
		
		
	}
	
	@Test
	void testGetTail() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		dblink.add(69);
		dblink.add(96);
		dblink.add(420);
		dblink.append(333);
		dblink.append(666);
		dblink.append(777);
		assertEquals(777, dblink.getTail());
		dblink.add(dblink.size(), 555);
		assertEquals(555, dblink.getTail());
	}
	
	@Test
	void testRemove() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		assertThrows(IllegalStateException.class, () -> dblink.remove());
		dblink.add(1);
		assertEquals(1, dblink.remove());
		dblink.add(2);
		dblink.add(3);
		dblink.add(4);
		assertEquals(4, dblink.remove());
		dblink.add(0, 1000);
		dblink.add(3, 500);
		assertEquals(1000, dblink.remove());
		assertEquals(3, dblink.remove());
		assertEquals(2, dblink.remove());
		assertEquals(500, dblink.remove());
		assertThrows(IllegalStateException.class, () -> dblink.remove());
		
		
		
	}
	
	@Test
	void testRemoveLast() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		assertThrows(IllegalStateException.class, () -> dblink.removeLast());
		dblink.add(10);
		assertEquals(10, dblink.removeLast());
		dblink.add(20);
		dblink.add(30);
		dblink.add(40);
		assertEquals(20, dblink.removeLast());
		assertEquals(30, dblink.removeLast());
		assertEquals(40, dblink.removeLast());
		assertThrows(IllegalStateException.class, () -> dblink.removeLast());
		
	}
	
	@Test
	void testRemoveAt() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		assertThrows(IllegalStateException.class, () -> dblink.removeAt(0));
		dblink.add(6969);
		dblink.add(9696);
		dblink.add(9000);
		dblink.removeAt(1);
		dblink.removeAt(0);
		dblink.removeAt(0);
		assertThrows(IllegalStateException.class, () -> dblink.removeAt(0));
		
	}
	
	@Test
	void testRemoveE() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		dblink.add(2);
		dblink.add(3);
		dblink.add(2);
		dblink.add(5);
		assertEquals(true, dblink.remove(2));
		assertEquals(true, dblink.remove(3));
		assertEquals(true, dblink.remove(5));
		assertEquals(true, dblink.remove(2));
		assertThrows(IllegalStateException.class, () -> dblink.remove(0));
	}
	
	@Test
	void testToString() {
		IDLList<Integer> dblink = new IDLList<Integer>();
		assertEquals("[]", dblink.toString());
		dblink.add(2);
		dblink.add(3);
		dblink.add(2);
		dblink.add(5);
		assertEquals("[5,2,3,2]", dblink.toString());
	}
	
}
