/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 */
package endterm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
	// Data fields
	// TODO
	public Map<Character, BSFamilyTree> directory;

	/**
     	* Creates a new phone book with an empty directory.
     	*/
	public PhoneBook() {
		//TODO
		directory = new HashMap<Character, BSFamilyTree>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for(int i = 0; i < alphabet.length; i++) {
			directory.put(alphabet[i], new BSFamilyTree());
		}
		
	}

	/*
	 * Returns the instance of BSFamilyTree at the indicated letter
	 * Must accept lowercase letters as well as uppercase letters
	 */
	public BSFamilyTree getFamilyTree(char letter) {
		//TODO
		if(Character.isLetter(letter) == true) {
			return directory.get(Character.toLowerCase(letter));
		}
		else {
			throw new IllegalArgumentException("Input entered is not a letter.");
		}
		
	}

	/*
	 * Adds a FamilyTreeNode to the PhoneBook
	 */
	public void addFamily(String lastName) {
		//TODO
		if(getFamilyTree(Character.toLowerCase(lastName.charAt(0))).doesFamilyExist(lastName)){
			throw new IllegalArgumentException("Family with last name is already in the phonebook.");
		}
		else {
			getFamilyTree(Character.toLowerCase(lastName.charAt(0))).addFamilyTreeNode(lastName);
		}
		
	}

	/*
	 * Adds a Person to the PhoneBook
	 * If a FamilyTreeNode with the given last name doesn't currently exist, create the FamilyTreeNode
	 */
	public void addPerson(String lastName, String firstName, String phoneNumber) {
		//TODO
		for(Map.Entry<Character, BSFamilyTree> entry: directory.entrySet()) {
			if(entry.getValue().doesNumberExist(phoneNumber) == true) {
				throw new IllegalArgumentException("This phone number already exists in the phonebook.");
			}
			
		}
		
		BSFamilyTree tree = getFamilyTree(Character.toLowerCase(lastName.charAt(0)));
		
		if(tree.doesFamilyExist(lastName) == true) {
			tree.getFamilyTreeNode(lastName).addFamilyMember(lastName, firstName, phoneNumber);
		}
		else {
			tree.addFamilyTreeNode(lastName);
			tree.getFamilyTreeNode(lastName).addFamilyMember(lastName, firstName, phoneNumber);
		}
		
	}

	/*
	 * Finds the phone number of a person
	 * Returns 'Does not exist.' if not found.
	 */
	public String getPhoneNumber(String lastName, String firstName) {
		//TODO
		BSFamilyTree tree = getFamilyTree(Character.toLowerCase(lastName.charAt(0)));
		
		if(tree.doesFamilyExist(lastName) == true) {
			return tree.getFamilyTreeNode(lastName).getPhoneNumberOfFamilyMember(lastName, firstName);
		}
		else {
			return "Does not exist.";
		}
		
	}

    	/**
     	* String representation of PhoneBook
     	*/
	public String toString() {
		//TODO
		StringBuilder pBook = new StringBuilder();
		
		for(Map.Entry<Character, BSFamilyTree> entry: directory.entrySet()) {
			pBook.append(Character.toUpperCase(entry.getKey()));
			pBook.append("\n");
			pBook.append(entry.getValue().toString());
		}
		
		return pBook.toString();
	}
	
}
