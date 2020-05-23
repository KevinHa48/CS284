/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	//Assigns each alphabet letter to its corresponding value in the primes table
	private void buildLetterTable() {
		    // Complete
		letterTable = new HashMap<Character, Integer>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for(int i = 0; i < alphabet.length; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}
	//Constructor
	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	//Adds word to anagramTable or a word in an existing entry
	public void addWord(String s) {
		    // Complete
	    if(s == null) {
	    	throw new IllegalArgumentException("String cannot be null");
	    }
	    else {
	    	long hashKey = myhashcode(s);
	    	
	    	if(anagramTable.containsKey(hashKey) == false) {
	    		ArrayList<String> words = new ArrayList<>();
	    		words.add(s);
	    		anagramTable.put(hashKey, words);
	    	}
	    	//If entry already exists, grab the map list entry and append to the word arraylist
	    	else {
	    		anagramTable.get(hashKey).add(s);
	    	}
	    }
		
	}
	//Generates hashcode for string. 
	public long myhashcode(String s) {
		    // Complete
		long hashCode = 1;
		
	    for(int i = 0; i < s.length(); i++) {
	    	Character letter = s.charAt(i);
	    	int value = letterTable.get(letter);
	    	hashCode *= value;
	    }
	    return hashCode;

	}
	//Used to process word_alpha
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	/**
	 * Gets the list with the most anagrams, may have multiple entries in maxEntryList
	 * @maxEntryList holds the Mapped values
	 * 
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    // Complete
		int maxVal = 0;
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntryList = new ArrayList<>();
		
	    for(Map.Entry<Long, ArrayList<String>> mappings: anagramTable.entrySet()) {
	    	int anagramSize = mappings.getValue().size();
	    	
	    	if(anagramSize == maxVal) {
	    		maxEntryList.add(mappings);
	    	}
	    	
	    	if(anagramSize > maxVal) {
	    		maxVal = anagramSize;
	    		maxEntryList.clear();
	    		maxEntryList.add(mappings);
	    		
	    	}
	    	
	    	else {
	    		continue;
	    	}
	    }
	    return maxEntryList;
	}
	/**
	 * Note: format was changed to match PDF main method
	 * @completeList is created in order to account for cases where multiple anagrams have the same max value
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Elapsed Time: "+ seconds);
		System.out.println("Key of max anagrams: "+ maxEntries.get(0).getKey());
		if(maxEntries.size() == 1) {
			System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue());
		}
		else {
			ArrayList<ArrayList<String>> completeList = new ArrayList<>();
			
			for(int i = 0; i < maxEntries.size(); i++) {
				ArrayList<String> anagramList = maxEntries.get(i).getValue();
				completeList.add(anagramList);
			}
			
			System.out.println("List of max anagrams: "+ completeList);
		}
		System.out.println("Length of list of max anagrams: " + maxEntries.get(0).getValue().size());
		
		
		
	}
}
