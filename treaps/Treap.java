/**
 * Created by: Kevin Ha
 * CS-284 A
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

package treaps;


import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E>{
		/**
		 * Data field
		 * @priority The given/generated priority for an element of the treap
		 * @data Also known as the "key"
		 */
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		public Node(E data, int priority) {
			
			if(data == null) {
				throw new IllegalArgumentException();
			}
			
			this.data = data;
			this.left = null;
			this.right = null;
			this.priority = priority;
		}
		//Performs right rotation based on parent node
		Node<E> rotateRight(){
			Node<E> root = this.left;
			Node<E> leftChild = root.right;
			root.right = this;
			this.left = leftChild;
			return this;
		}
		//Performs left rotation based on parent node
		Node<E> rotateLeft(){
			Node<E> root = this.right;
			Node<E> rightChild = root.left;
			root.left = this;
			this.right = rightChild;
			return this;
		}
		//Extra toString() method to properly format tests
		public String toString(){
			String s = "(key=" + this.data + ", priority=" + this.priority + ")";
			return s;
		}
		
	}
	
	/**
	 * data fields
	 * @param args
	 * @priorityGenerator Instantiates the imported Random java util.
	 */
	private Random priorityGenerator;
	private Node<E> root;
	
	//To check for repeating priorities and storing them
	private ArrayList<Integer> priorityList;
	
	//Constructors for the Treap
	public Treap() {
		this.root = null;
		this.priorityList = new ArrayList<Integer>();
		this.priorityGenerator = new Random();
	}
	
	public Treap(long seed) {
		this.root = null;
		this.priorityList = new ArrayList<Integer>();
		this.priorityGenerator = new Random(seed);
	}
	
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	
	/**
	 * Reheap function which restores the heap invariant after inserting something
	 * @param heapPath The stack which contains the parent nodes that were pushed by the add function
	 * @param item The node to be inserted
	 * .peek() is called in order to reference the parent's parent and is need since rotate does not update references
	 */
	public void reheap(Stack<Node<E>> heapPath, Node<E> item) {
		while(!heapPath.empty()) {
			Node<E> parent = heapPath.pop();
		
			if(parent.priority < item.priority) {
				if(parent.right == item) {
					parent.rotateLeft();
					if(heapPath.isEmpty()) {
							root = item;
							return;
						}
					if(heapPath.peek().right == parent) {
						heapPath.peek().right = item;
						continue;
						}
					
					heapPath.peek().left = item;
				}
				else {
					parent.rotateRight();
					if(heapPath.isEmpty()) {
						root = item;
						return;
					}
					if(heapPath.peek().right == parent) {
						heapPath.peek().right = item;
						continue;
					}
					
					heapPath.peek().left = item;
					
				}
				
			}
			//No need to reheap if parent's priority is bigger than child's.		
			else {
				return;
			}
		}
		
	}
	/**
	 * Add function, adds a node consisting of it's key, priority pair to the treap
	 * @param key: value of the node sorted using BST
	 * @param priority: sorted using heap algorithm
	 * @return
	 */
	boolean add(E key, int priority) {	
		Node<E> element = new Node<>(key, priority);
		Node<E> current = root;
		Stack<Node<E>> heapPath = new Stack<>();
		
		//Used to check if a node in treap already contains the generated priority
		if(priorityList.contains(priority)) {
			System.out.println("The treap already contains this priority");
			return false;
		}
		
		priorityList.add(priority);
		
		if(current == null) {
			root = element;
		}
		
		while(current != null) {
			int compare = current.data.compareTo(key);
			
			if(compare == 0) {
				return false;
			}
			
			if(compare > 0) {
				heapPath.push(current);
				
				if(current.left == null) {
					current.left = element;
					reheap(heapPath, current.left);
					return true;
				}
				else {
					current = current.left;
				}
				
			}
			else {
				heapPath.push(current);
				
				if(current.right == null) {
					current.right = element;
					reheap(heapPath, current.right);
					return true;
				}
				else {
					current = current.right;
				}
			}
			
			
		}
		return true;
	}
	/**
	 * Delete function: Deletes the selected node from the treap, using the helper to properly restore heap invariant upon deletion
	 * @param key: The selected node you want to delete
	 * @return
	 */
	public boolean delete(E key) {
		if(find(key) == true) {
			root = delete_helper(key,root);
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Delete helper: Performs deletion
	 * @param key: The selected value the function needs to find
	 * @param current: Starts at root and is set to the value of the resulting traversal
	 * @return
	 */
	public Node<E> delete_helper(E key, Node<E> current) {
		
		if(current == null) {
			return null;
		}
		else {
			int n = current.data.compareTo(key);
			
			//Checks to see if the key value the function is looking for is smaller than the current node, then shift current to left.
			if(n > 0) {
				current.left = delete_helper(key, current.left);
				return current;
			}
			//Checks to see if the key value the function is looking for is bigger than the current node, then shift current to right.
			if(n < 0) {
				current.right = delete_helper(key, current.right);
				return current;
			}
			
			else {
				//Checks to see if the current node has no children, then it is a leaf and simply remove it by setting = to null
				if(current.left == null && current.right == null) {
					current = null;
					return current;
				}
				//Checks to see if current node has left child but not right, then shift current to left, delete current.left
				if(current.left != null && current.right == null) {
					Node<E> temp = current.left;
					current = temp;
					delete_helper(key, current.left);
					return current;
					
				}
				//Checks to see if current node has right child but not left, then shift current to right, delete current.right
				if(current.right != null && current.left == null) {
					Node<E> temp = current.right;
					current = temp;
					delete_helper(key, current.right);
					return current;
					
				}
				/**Checks to see if current has both left and right node. 
				 * Set current.right/left = to recursive call to shift focus on 
				 * deletion to the new location of the node that was rotated
				 */
				else {
					if(current.left.priority > current.right.priority) {
						current = current.rotateRight();
						current.right = delete_helper(key, current.right);
					}
					else {
						current = current.rotateLeft();
						current.left = delete_helper(key, current.left);
					}
				}
			}
			
		}
		return current;
		
	}
	//Find helper function, finds if the requested key value exists in the treap or not, if yes return true, else false;
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		} else {
			int findcomp = key.compareTo(root.data);
			if (findcomp == 0) {
				return true;
			} else {
				if (findcomp < 0) {
					return find(root.left,key);
				} else {
					return find(root.right,key);
				}
			}
		}
	}
	//Find function, takes a key and returns the value the helper
	public Boolean find(E key) {
		return find(root,key);
	}
	
	//toString() helper method
	private StringBuilder toString_helper(Node<E> current, int level) {
		StringBuilder r = new StringBuilder();
		for(int i = 0; i<level; i++) {
			r.append("  "); 
		}
		if(current == null) {
			r.append("null\n");
		}
		else {
			r.append(current.toString());
			r.append("\n");
			r.append(toString_helper(current.left, level + 1));
			r.append(toString_helper(current.right, level + 1));
		}
		return r;
	}
	
	public String toString() {
		return toString_helper(root, 0).toString();
	}

	public static void main(String[] args) {
		Treap<Integer> testTreap = new Treap<Integer>();
		Treap<Character> testTreap2 = new Treap<Character>();
		testTreap.add(4,19);
		testTreap.add(2,31);
		testTreap.add(6,70);
	    testTreap.add(1,84);
		testTreap.add(3,12);
		testTreap.add(5,83);
		testTreap.add(7,26);
	
		
		testTreap2.add('d',19);
		testTreap2.add('b',31);
		testTreap2.add('f',70);
	    testTreap2.add('a',84);
		testTreap2.add('c',12);
		testTreap2.add('e',83);
		testTreap2.add('g',26);
		
		
		System.out.println(testTreap.toString());
		System.out.println(testTreap2.toString());
		
	}

}
