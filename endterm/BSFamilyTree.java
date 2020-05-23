/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 */
package endterm;
import java.util.ArrayList;


/**
 * BSFamilyTree creates a tree for specific families. 
 */
public class BSFamilyTree {
    //Data Fields
    //TODO
	private FamilyTreeNode root;

    /**
     * Constructor: constructs an empty BSFamilyTree
     */
    public BSFamilyTree() {
        //TODO
    	this.root = null;
    	
    }

    /**
     * takes in the last name and returns true if there
     * is a FamilyTreeNode with the given last name.
     */
    public boolean doesFamilyExistHelper(FamilyTreeNode current, String lastName) {
        //TODO
    	if(current == null) {
    		return false;
    	}
    	else {
    		int compare = lastName.compareTo(current.getLastName());
    		if(compare == 0) {
    			return true;
    		}
    		else {
    			if(compare < 0) {
    				return doesFamilyExistHelper(current.left, lastName);
    			}
    			else {
    				return doesFamilyExistHelper(current.right, lastName);
    			}
    		}
    	}
    }
    
    public boolean doesFamilyExist(String lastName) {
		return doesFamilyExistHelper(root,lastName);
	}

    /**
     * Takes in a last name and creates a new instance of
     * FamilyTreeNode and adds it to the BSFamilyTree.
     */
    public FamilyTreeNode addFamilyTreeNodeHelper(FamilyTreeNode current, String lastName) {
        //TODO
    	if (current==null) {
			return new FamilyTreeNode(lastName);
		} else {
			int compare = current.getLastName().compareTo(lastName);
			if (compare == 0) {
				throw new IllegalStateException("Family with Last Name already in tree");
			}
			if (compare > 0) {
				current.left = addFamilyTreeNodeHelper(current.left, lastName);
				return current;
			}
			current.right = addFamilyTreeNodeHelper(current.right, lastName);
			return current;
		}
	}
    
    public void addFamilyTreeNode(String lastName) {
    	root = addFamilyTreeNodeHelper(root, lastName);
    	return;
    }
    	

    /**
     * Takes a last name and then finds that specific
     * family tree and then returns that FamilyTreeNode
     * If last name is not in tree, throws an exception.
     */
    public FamilyTreeNode getFamilyTreeNodeHelper(FamilyTreeNode current, String lastName) {
        //TODO
    	if(current == null) {
    		throw new IllegalArgumentException("Last name does not exist in the entire family tree.");
    	}
    	else {
    		int compare = lastName.compareTo(current.getLastName());
    		if(compare == 0) {
    			return current;
    		}
    		else {
    			if(compare < 0) {
    				return getFamilyTreeNodeHelper(current.left, lastName);
    			}
    			else {
    				return getFamilyTreeNodeHelper(current.right, lastName);
    			}
    		}
    	}
    }
    
    public FamilyTreeNode getFamilyTreeNode(String lastName){
    	return getFamilyTreeNodeHelper(root, lastName);
    }
    

    /**
     * Returns true if the input phone number exists in the BSFamilyTree
     * false otherwise.
     */
    public boolean doesNumberExist(String phoneNumber) {
        //TODO
        return doesNumberExistHelper(root, phoneNumber);
    }
    
    public boolean doesNumberExistHelper(FamilyTreeNode current, String phoneNumber) {
    	if(current == null) {
    		return false;
    	}
    	if(current.doesNumberExist(phoneNumber) == true){
        	return true;
    	}
    	if(doesNumberExistHelper(current.left, phoneNumber)) {
    		return true;
    	}
    	else {
    		return doesNumberExistHelper(current.right, phoneNumber);
    	}
    	
    }
    	
   
    /**
     * Returns the string representation of the BSFamilyTree
     */
    
    private void preOrderTraverse(FamilyTreeNode current, int depth, StringBuilder sb) {
    	for(int i = 1; i < depth; i++) {
    		sb.append("  ");
    	}
    	
    	if(current == null) {
    		sb.append("null\n");
    	}
    	
    	else {
    		sb.append(current.toString());
    		sb.append("\n");
    		preOrderTraverse(current.left, depth + 1, sb);
    		preOrderTraverse(current.right, depth + 1, sb);
    	}
    }
    
    public String toString() {
        //TODO
    	StringBuilder preOrder = new StringBuilder();
    	preOrderTraverse(root, 1, preOrder);
    	return preOrder.toString();
    	
    }
    
}
