
import java.util.ArrayList;

/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 * Section: CS 284 A
 */


public class IDLList<E> {
	
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	/**
	 * The Class Node.
	 *
	 * @param <E> generic element type
	 */
	private static class Node<E>{
		
		//data fields
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		/**
		 * Instantiates a new node.
		 *
		 * @param elem data
		 */
		public Node(E elem) {
			this.data = elem;
		}
		
		/**
		 * Instantiates a new node.
		 *
		 * @param elem data
		 * @param prev previous node
		 * @param next next node
		 */
		public Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}
	
		
	}
	
	/**
	 * Instantiates a new IDLList.
	 */
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.indices = new ArrayList<Node<E>>();
		size = 0;
	}
	
	/**
	 * Adds the elem to specified index
	 *
	 * @param index the index
	 * @param elem the elem
	 * @return true, if successful
	 */
	public boolean add(int index, E elem) {
		if(index > size || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		else if(index == 0 || size == 0 ){
			add(elem);
		}
		
		else if(index == size) {
			append(elem);
		}
		else {
			Node<E> tempCurrent = indices.get(index);
			Node<E> newItem = new Node<E>(elem, tempCurrent.prev, tempCurrent);
			tempCurrent.prev.next = newItem;
			tempCurrent.prev = newItem;
			size++;
			indices.add(index, newItem);
		}
		return true;
	}
	
	/**
	 * Adds the elem to the beginning
	 *
	 * @param elem the elem
	 * @return true, if successful
	 */
	public boolean add(E elem) {
		if(head == null) {
			Node<E> newItem = new Node<E>(elem);
			head = newItem;
			tail = head;
			size++;
			indices.add(newItem);
		}
		
		else {
			Node<E> newHead = new Node<E>(elem, null, head);
			head.prev = newHead;
			head = newHead;
			size++;
			indices.add(0, newHead);
		}
		return true;
	}
	
	/**
	 * Append elem to the end of the list.
	 *
	 * @param elem the elem
	 * @return true, if successful
	 */
	public boolean append(E elem) {
		if(tail == null) {
			add(elem);
		}
		else {
			tail.next = new Node<E>(elem, tail, null);
			tail = tail.next;
			size++;
			indices.add(tail);
		}
		return true;
	}
	
	/**
	 * Gets the node at the specified index
	 *
	 * @param index the index
	 * @return the e
	 */
	public E get(int index) {
		if(size == 0) {
			throw new IllegalStateException("Your list is empty.");
		}
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Node<E> item = indices.get(index);
		return item.data;
	}
	
	/**
	 * Gets the head.
	 *
	 * @return the head
	 */
	public E getHead() {
		if(size == 0) {
			throw new IllegalStateException("Your list is empty.");
		}
		return get(0);
	}
	
	/**
	 * Gets the tail.
	 *
	 * @return the tail
	 */
	public E getTail() {
		if(size == 0) {
			throw new IllegalStateException("Your list is empty.");
		}
		return get(size - 1);
	}

	/**
	 * Removes the head node.
	 *
	 * @return the e
	 */
	public E remove() {
		if(size == 0) {
			throw new IllegalStateException("Your list is empty");
		}
		
		else if(size == 1) {
			E temp = head.data;
			indices.remove(head);
			head = null;
			tail = head;
			size--;
			return temp;
		}
		E temp = head.data;
		indices.remove(head);
		head.next.prev = null;
		head = head.next;
		
		size--;
		return temp;
	}
	
	/**
	 * Removes the last node
	 *
	 * @return the e
	 */
	public E removeLast() {
		if(size == 0) {
			throw new IllegalStateException("Your list is empty.");
		}
		else if(size == 1) {
			E temp = tail.data;
			indices.remove(tail);
			tail = null;
			head = tail;
			size--;
			return temp;
		}
		E temp = tail.data;
		indices.remove(tail);
		tail = tail.prev;
		tail.next = null;
		size--;
		return temp;
	}
	
	/**
	 * Removes the node at the specified index
	 *
	 * @param index the index
	 * @return the e
	 */
	public E removeAt(int index) {
		if(index > size || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		else if(index == 0 || size == 0 ){
			return remove();
		}
		else if(index == size-1) {
			return removeLast();
		}
		else {
			Node<E> tempCurrent = indices.get(index);
			indices.remove(index);
			tempCurrent.next.prev = tempCurrent.prev;
			tempCurrent.prev.next = tempCurrent.next;
			size--;
			return tempCurrent.data;
		}	
	}
	
	/**
	 * Removes the specified elem
	 *
	 * @param elem the elem
	 * @return true, if successful
	 */
	public boolean remove(E elem) {
		if(size == 0) {
			throw new IllegalStateException();
		}
		for(int i = 0; i < size; i++) {
			Node<E> current = indices.get(i);
			if(current.data.equals(elem)) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns the size
	 *
	 * @return the int
	 */
	public int size() {
		return size;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		Node<E> current = head;
		s.append("[");
		if(head == null) {
			s.append("]");
			return s.toString();
		}
		while (current.next !=null) {
			s.append(current.data);
			s.append(",");
			current = current.next;
		}
		s.append(current.data);
		s.append("]");
	
		return s.toString();
	
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IDLList<Integer> dblink = new IDLList<Integer>();
		dblink.add(5);
		dblink.add(6);
		dblink.add(10);
		dblink.add(22);
		dblink.add(1,3);
		dblink.add(2,0);
		dblink.add(2,500);
		dblink.append(1000);

		System.out.println(dblink);
		dblink.removeAt(3);
		dblink.removeAt(1);
		dblink.removeAt(0);
		dblink.removeAt(4);
		
		
		
		System.out.println(dblink);
		System.out.println(dblink.getHead());
		System.out.println(dblink.getTail());
		
	}

}
