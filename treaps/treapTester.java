/**
 * Created by: Kevin Ha
 * CS-284 A
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package treaps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class treapTester {

	@Test
	void test() {
		Treap<Integer> testTreap = new Treap<Integer>();
		
		//Testing add function
		assertEquals(testTreap.add(4,19), true);
		assertEquals(testTreap.add(2,31), true);
		assertEquals(testTreap.add(6,70), true);
		assertEquals(testTreap.add(1,84), true);
		assertEquals(testTreap.add(3,12), true);
		assertEquals(testTreap.add(5,83), true);
		assertEquals(testTreap.add(7,26), true);
		
		//Test adding elements already in the treap.
		assertEquals(testTreap.add(5,83), false);
		assertEquals(testTreap.add(7,26), false);
		
		//Testing toString() function;
		assertEquals(testTreap.toString(),
				"(key=1, priority=84)\n" +
				"  null\n"+
				"  (key=5, priority=83)\n"+
				"    (key=2, priority=31)\n"+
				"      null\n"+
				"      (key=4, priority=19)\n"+
				"        (key=3, priority=12)\n"+
				"          null\n"+
				"          null\n"+
				"        null\n"+
				"    (key=6, priority=70)\n"+
				"      null\n"+
				"      (key=7, priority=26)\n"+
				"        null\n"+
				"        null\n");
		
		//Testing Find Function
		assertEquals(testTreap.find(5), true);
		assertEquals(testTreap.find(69), false);
		
		//Testing Delete Function
		assertEquals(testTreap.delete(100), false);
		assertEquals(testTreap.delete(7), true);
		assertEquals(testTreap.toString(),
				"(key=1, priority=84)\n" +
				"  null\n"+
				"  (key=5, priority=83)\n"+
				"    (key=2, priority=31)\n"+
				"      null\n"+
				"      (key=4, priority=19)\n"+
				"        (key=3, priority=12)\n"+
				"          null\n"+
				"          null\n"+
				"        null\n"+
				"    (key=6, priority=70)\n"+
				"      null\n"+
				"      null\n");
		
		assertEquals(testTreap.find(6), true);
		assertEquals(testTreap.find(7), false);
		
		
		//Testing delete again, but with a higher node
		assertEquals(testTreap.delete(7), false);
		assertEquals(testTreap.delete(2), true);
		assertEquals(testTreap.toString(),
				"(key=1, priority=84)\n" +
						"  null\n"+
						"  (key=5, priority=83)\n"+
						"    (key=4, priority=19)\n"+
						"      (key=3, priority=12)\n"+
						"        null\n"+
						"        null\n"+
						"      null\n"+
						"    (key=6, priority=70)\n"+
						"      null\n"+
						"      null\n");
		
		assertEquals(testTreap.find(7), false);
		
		
		
	}
	

}
