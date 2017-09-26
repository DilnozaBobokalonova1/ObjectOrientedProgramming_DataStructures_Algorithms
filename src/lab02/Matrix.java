package lab02;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	// default constructor
	public Matrix(){}
	
	// constructor 1 - Constructor for new zero matrix
	public Matrix(int rowDim, int colDim){
		numRows = rowDim;
		numColumns = colDim;
		//do column nums
		data = new int[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++){
				data[i][j] = 0;
			}
		}
		
		/*
		* TODO: write a constructor that would create a matrix
		* of correct size and initialize it to 0. 
		*/
	}
	
	
	// constructor 2 - Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][]) {
		numRows = d.length;
		numColumns = d[0].length;
		data = new int[numRows][numColumns];
		for (int k = 0; k < numRows; k++){
			for (int j = 0; j < numColumns; j++){
				data[k][j] = d[k][j];
			}
		}
		/* 1) put the numRows to be the number of 1D arrays in the 2D array
		*  2) specify the numColumns and set it
		*  3) be careful of special cases you are supposed to handle them properly
		*  4) create a new matrix to hold the data
		*  5) copy the data over
		*/
	}	
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString() {
		String s = "\n";
		for (int i = 0; i < numRows; i++) {
		   for (int j = 0; j < numColumns; j++) {
			   s += (data[i][j] + " ");
		   }
		   s += "\n";
		}
		return s; // placeholder		
	}
	
	// *** you will implement the rest of the methods for your assignment
	// *** don't touch them before finishing the lab portion
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		if (!(this.numRows == m.numRows && this.numColumns == m.numColumns)) {
			return false;
		} else {
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				if (this.data[i][j] != m.data[i][j]) {
					return false;
				}
			}
		}
		}
		return true;
	}
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */


	public Matrix times(Matrix m) {
		if (this.numColumns != m.numRows) {
			System.out.println("Matrices have failed compatibility test!");
				return null;
		}
			Matrix newMatrix = new Matrix(new int[this.numRows][m.numColumns]);
			for (int i= 0; i < this.numRows; i++) {
				for (int j = 0; j < m.numColumns; j++) {
					for (int k = 0; k < this.numColumns; k++) {
						newMatrix.data[i][j] += (this.data[i][k] * m.data[k][j]);
					}
				}
			}
		return newMatrix;	
	}
		
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		 // placeholder
	//end times
	
	public Matrix plus(Matrix m) {
		if ((this.numRows != m.numRows) || (this.numColumns != m.numColumns)) {
			System.out.println("Matrices have failed compatibility test!");
			return null;
		}
		Matrix newSumMatrix = new Matrix(new int[this.numRows][this.numColumns]);
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				newSumMatrix.data[i][j] = (this.data[i][j] + m.data[i][j]);
			}
		}
		return newSumMatrix;
	}
}
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */ // placeholder

