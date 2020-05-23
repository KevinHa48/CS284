package midterm;

import java.util.ArrayList;
import java.util.List;

public class ListCollection<E> {
  private int nodeCount;
  protected List<SingleLL<E>> collection;

  /**
   * Base constructor, initializes an empty ListCollection.
   */
  public ListCollection() {
	 this.collection = new ArrayList<>();
	 this.nodeCount = 0; 
  }

  /**
   * Initializes a ListCollection with `numLists` lists.
   * 
   * @param numLists
   */
  public ListCollection(int numLists) {
	  this.collection = new ArrayList<>();
	  this.nodeCount = 0;
	  
	  if(numLists < 0) {
		  throw new IllegalArgumentException("The number of lists cannot be negative.");
	  }
	  
	  for(int i = 0; i < numLists; i++) {
		  collection.add(new SingleLL<E>());
	  }
  }

  /**
   * @return The size of the `ListCollection`
   */
  public int size() {
	  return this.collection.size();
  }

  /**
   * Sets the nodeCount
   * 
   * @param nodeCount
   */
  public void setNodeCount(int nodeCount) {
	  this.nodeCount = nodeCount;
  }

  /**
   * @return the nodeCount
   */
  public int getNodeCount() {
	  return nodeCount;
  }

  /**
   * Adds the specified `SingleLL` to the end of the `ListCollection`
   * 
   * @param list
   */
  public void addList(SingleLL<E> list) {
	  this.collection.add(list);
	  nodeCount += list.size();
  }

  /**
   * Adds the specified `List` to the `ListCollection`
   * 
   * @param list
   * @complexity O(N) time complexity, as there is a single for loop which takes N*O(1) for the total time.
   */
  public void addList(List<E> list) {
	  
	  SingleLL<E> convert = new SingleLL<E>();
	  
	  for(E item: list) {
		  convert.append(item);
	  }
	  addList(convert);
  }

  /**
   * Returns the list at the specified index
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @return the list
   */
  public SingleLL<E> getList(int listIndex) {
	  if(listIndex < 0 || listIndex >= size()) {
		  throw new IllegalArgumentException("Index out of bounds.");
	  }
	  else {
		  return collection.get(listIndex);
	  }
  }

  /**
   * Adds an element to the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   * @complexity O(n) complexity. (technically O(2n)) since two for loops are run which both have a complexity of N, but they are not nested so n+n = 2n but we assume n.
   */
  public void addElem(int listIndex, int elemIndex, E elem) {
	  if(listIndex < 0 || listIndex >= size()) {
		  throw new IllegalArgumentException("Index out of bounds.");
	  }
	  if(elemIndex < 0 || elemIndex >= getList(listIndex).size()) {
		  throw new IllegalArgumentException("Index out of bounds.");
	  }
	  getList(listIndex).insert(elemIndex, elem);
	  nodeCount++;

  }

  /**
   * Sets the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   */
  public void setElem(int listIndex, int elemIndex, E elem) {
	  
	  if(listIndex < 0 || listIndex >= size()) {
		  throw new IllegalArgumentException("Index out of bounds.");
	  }
	  if(elemIndex < 0 || elemIndex >= getList(listIndex).size()) {
		  throw new IllegalArgumentException("Index out of bounds.");
	  }
	 
	  getList(listIndex).set(elemIndex, elem);
  }

  /**
   * Returns the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @return
   */
  public E getElem(int listIndex, int elemIndex) {
	  if(listIndex < 0 || listIndex >= size()) {
		  throw new IllegalArgumentException("Index out of bounds.");
	  }
	  if(elemIndex < 0 || elemIndex >= getList(listIndex).size()) {
		  throw new IllegalArgumentException("Index out of bounds.");
	  }
	  return getList(listIndex).get(elemIndex);
  }

  /**
   * Returns the `ListCollection` in string form, following STRICTLY the rule of
   * "[LIST1, LIST2, LIST3, ... ]" Ex: [[0, 1, 2], [3, 4, 5] [6, 7, 8]]
   */
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  
	  if(collection.size() == 0) {
		  sb.append("[]");
		  return sb.toString();
	  }
	  
	  sb.append("[");
	  for(int i = 0; i < size()-1; i++) {
		  sb.append(getList(i).toString());
		  sb.append(", ");
	  }
	  sb.append(getList(size()-1).toString());
	  sb.append("]");
	  
	  return sb.toString();
	
  }
  
  public static void main(String[] args) {
	  ListCollection<Integer> pp = new ListCollection<>(1);
	  pp.addElem(0,0,5);
  }
  
}
