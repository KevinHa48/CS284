/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 */
package anagrams;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	/**
	 * Test1: Tests the supplied check in the PDF
	 * Test2: Uses a modified version of words_alpha.txt without the words that have 15 anagrams.
	 * @listOfAnagrams stores the multiple list of Anagrams
	 */
	void test() {
		Anagrams test1 = new Anagrams();
		Anagrams test2 = new Anagrams();
		
		try {
			test1.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = test1.getMaxEntries();
		assertEquals(236204078, maxEntries.get(0).getKey());
		assertEquals("[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]", maxEntries.get(0).getValue().toString());
		assertEquals(15, maxEntries.get(0).getValue().size());
		
		
		try {
			test2.processFile("words_alpha_no15.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries2 = test2.getMaxEntries();
		ArrayList<ArrayList<String>> listOfAnagrams = new ArrayList<>();
		for(int i = 0; i < maxEntries2.size(); i++) {
			listOfAnagrams.add(maxEntries2.get(i).getValue());
		}
	
		assertEquals(4765442, maxEntries2.get(0).getKey());
		assertEquals(6383894, maxEntries2.get(1).getKey());
		assertEquals(6313671166L, maxEntries2.get(2).getKey());
		assertEquals("[[apers, apres, asper, pares, parse, pears, prase, presa, rapes, reaps, repas, spaer, spare, spear], [arest, aster, astre, rates, reast, resat, serta, stare, strae, tares, tarse, tears, teras, treas], [anestri, antsier, asterin, eranist, nastier, ratines, resiant, restain, retains, retinas, retsina, stainer, starnie, stearin]]", listOfAnagrams.toString());
		assertEquals(14, maxEntries2.get(0).getValue().size());
	}
		
}
	
