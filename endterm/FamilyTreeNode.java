/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package endterm;
import java.util.ArrayList;
import java.util.List;

public class FamilyTreeNode {
	// Data fields
	private String lastName;
	private List<Person> members;
	public FamilyTreeNode left;
	public FamilyTreeNode right;
	

	/**
     	* Constructor: instantializes a new FamilyTreeNode
     	* given a lastName
     	*/
	public FamilyTreeNode(String lastName) {
		this.lastName = lastName;
		this.members = new ArrayList<Person>();
		this.left = null;
		this.right = null;
	}

	/**
     	* Returns the last name of the FamilyTreeNode
     	*/
	public String getLastName() {
		return lastName;
	}

	/**
     	* Returns the arraylist of members in the FamilyTreeNode
     	*/
	public List<Person> getMembers() {
		//TODO
		return members;
	}

	/*
	 * Returns true if there is an instance of Person in the FamilyTreeNode that has
	 * the same first and last name provided Return false otherwise
	 */
	public boolean doesFamilyMemberExist(String lastName, String firstName) {
        //TODO
		for(Person member: members) {
			if(member.getFirstName() == firstName && member.getLastName() == lastName) {
				return true;
			}
		}
        return false;
	}

	/**
	 * Returns true if there is an instance of Person in the FamilyTreeNode whose
	 * phone number matches the one provided Returns false otherwise
	 */
	public boolean doesNumberExist(String phoneNumber) {
		for(Person member: members) {
			if(member.getPhoneNumber() == phoneNumber) {
				return true;
			}
		}
        return false;
	}

	/*
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(String lastName, String firstName, String phoneNumber) {
		//TODO
		if(this.getLastName() == lastName) {
			if(this.doesNumberExist(phoneNumber) == true) {
				throw new IllegalArgumentException("The phone number is already in this tree.");
			}
			if(this.doesFamilyMemberExist(lastName, firstName)){
				throw new IllegalArgumentException("This person is already in the family.");
			}
			else {
				Person newPerson = new Person(lastName, firstName, phoneNumber);
				this.members.add(newPerson);
			}
			
		}
		else {
			throw new IllegalArgumentException("The last name entered is not the same as the family's or you're trying to add a person before declaring the family tree itself.");
		}
	}

	/**
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(Person person) {
		if(this.getLastName() == person.getLastName()) {
			if(this.doesNumberExist(person.getPhoneNumber()) == true) {
				throw new IllegalArgumentException("The phone number is already in this tree.");
			}
			if(this.doesFamilyMemberExist(person.getLastName(), person.getFirstName())){
				throw new IllegalArgumentException("This person is already in the family.");
			}
			else {
				this.members.add(person);
			}
		}
		else {
			throw new IllegalArgumentException("This person's last name is not the same as the family's or you're trying to add a person before declaring the family tree itself.");
		}
	}

	/*
	 * Returns the phone number of the person in the family with the given phone
	 * number Returns "Does not exist." if not found
	 */
	public String getPhoneNumberOfFamilyMember(String lastName, String firstName) {
		//TODO
		for(Person member: members) {
			if(member.getFirstName() == firstName && member.getLastName() == lastName) {
				return member.getPhoneNumber();
			}
		}
		return "Does not exist.";
		
		
	}

	/*
	 * toString method Ex: [] [John Smith (5551234567), May Smith (5551234568),
	 * April Smith (5551234569), August Smith (5551234570)]
	 */
	public String toString() {
		//TODO
		StringBuilder membersList = new StringBuilder();
		
		if(members.size() == 0) {
			membersList.append("[]");
			return membersList.toString();
		}
		
		membersList.append("[");
		for(int i = 0; i < members.size() - 1; i++) {
			membersList.append(members.get(i).toString());
			membersList.append(", ");
		}
		membersList.append(members.get(members.size() - 1));
		membersList.append("]");
		
		return membersList.toString();
		
	}
	
}
