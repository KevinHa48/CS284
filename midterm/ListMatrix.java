package midterm;

public class ListMatrix extends ListCollection<Integer> {
  private int rows;
  private int columns;

  /**
   * Initializes a `ListMatrix` with the specified number of rows and columns. By
   * default, ALL elements are set to 0.
   * 
   * @param rows
   * @param columns
   */
  public ListMatrix(int rows, int columns) {
	  super(rows);
	  
	  if(rows <= 0 || columns <= 0) {
		  throw new IllegalArgumentException();
	  }
	  
	  this.rows = rows;
	  this.columns = columns;
	  

	  for(int i = 0; i < rows; i++) {
		  for(int j = 0; j < columns; j++) {
			  super.getList(i).append(0);
			  
		  }
	  }
	  super.setNodeCount(rows * columns);
	
  }

  /**
   * @return the number of rows
   */
  public int numRows() {
    return this.rows;
  }

  /**
   * 
   * @return the number of columns
   */
  public int numColumns() {
    return this.columns;
  }

  /**
   * Adds the `ListMatrix` to `ListMatrix other`, storing the result in the caller
   * (this)
   * 
   * @throws IllegalArgumentException if dimensions do not peoperly coincide
   * @param other
   * @complexity O(n^2). Two for loops that have a run time of N are shown. Nesting them together N^N = N^2. Thus O(n^2).
   */
  public void add(ListMatrix other) {
	  if(this.numColumns() != other.numColumns() || this.numRows() != other.numRows()) {
		  throw new IllegalArgumentException("Both matrices must have equal rows and columns");
	  }
	  for(int i = 0; i < this.rows; i++) {
		  for(int j = 0; j < this.columns; j++) {
			  this.setElem(i, j, this.getElem(i,j) + other.getElem(i, j));
		  }
	  }
	  
  }

  /**
   * Returns the transpose of the matrix
   * 
   * @param matrix
   * @return matrix tranpose
   */
  public static ListMatrix transpose(ListMatrix matrix) {
	  ListMatrix tPose = new ListMatrix(matrix.columns, matrix.rows);
	  for(int i = 0; i < tPose.rows; i++) {
		  for(int j = 0; j < tPose.columns; j++) {
			  tPose.setElem(i, j, matrix.getElem(j, i));
		  }
	  }
	  return tPose;
  }

  /**
   * Multiplies the `ListMatrix` with `ListMatrix other`, returning the result as
   * a new `ListMatrix.
   * 
   * @throws IllegalArgumentException if dimensions do not peoperly coincide
   * @param other
   * @return
   */
  public ListMatrix multiply(ListMatrix other) {
	  if(this.columns != other.rows) {
		  throw new IllegalArgumentException("Recepient matrix columns must equal other's rows");
	  }
	  ListMatrix mMulti = new ListMatrix(this.rows, other.columns);
	  int sum = 0;
	  
	  for(int i = 0; i < this.rows; i++) {
		  for(int j = 0; j < other.columns; j++) {
			for(int k = 0; k < this.columns; k++) {
				sum += this.getElem(i, k)*other.getElem(k, j);
			}
			mMulti.setElem(i, j, sum);
			sum = 0;
		  }
		  
	  }
	  return mMulti;
  }
  
}
