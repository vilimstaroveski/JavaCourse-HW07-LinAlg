package hr.fer.zemris.java.student0036477551.hw07.matrix;



import hr.fer.zemris.java.student0036477561.hw07.linearna.LinAlgDefaults;
import hr.fer.zemris.linearna.IMatrix;

/**
 * Matrix that is a view to another matrix, but without 1 row and 1 column.
 * @author Vilim Staroveški
 *
 */
public class MatrixSubMatrixView extends AbstractMatrix {

	/**
	 * Original matrix.
	 */
	private IMatrix original;
	
	/**
	 * Which row indexes are included.
	 */
	private int[] rowIndexes;
	
	/**
	 * Which column indexes are included.
	 */
	private int[] colIndexes;
	
	/**
	 * Creates new {@link MatrixSubMatrixView}.
	 * @param original original
	 * @param rowIndex Which row index is not included.
	 * @param colIndex Which column index is not included.
	 */
	public MatrixSubMatrixView(IMatrix original, int rowIndex, int colIndex) {
		
		this(original, filterInts(rowIndex, makeIntArray(original.getRowsCount())), 
				filterInts(colIndex, makeIntArray(original.getColsCount())));
	}
	/**
	 * Private method that makes array of sequence ints till count.
	 * @param count max int, but not included.
	 * @return array of ints
	 */
	private static int[] makeIntArray(int count) {
		
		int[] array = new int[count];
		for(int i = 0; i < count; i++) {
			array[i] = i;
		}
		return array;
	}

	/**
	 * Filters given int array from given intToAvoid.
	 * @param intToAvoid int that will be excluded.
	 * @param array array for filtering
	 * @return filtered array.
	 */
	private static int[] filterInts(int intToAvoid, int[] array) {

		if(!containsIndex(array, intToAvoid)) {
			return array;
		}
//		System.out.println("array prije micanja: "+intToAvoid);
//		for(int i : array) {
//			System.out.println(i);
//		}
//		System.out.println("filter...");
		int[] filtered = new int[array.length-1];
		boolean intPassed = false;
		for(int i = 0; i < array.length; i++) {
			if(i == intToAvoid) {
				intPassed = true;
				continue;
			}
			filtered[intPassed ? i-1 : i] = i;
		}
//		System.out.println("array poslije: ");
//		for(int j : filtered) {
//			System.out.println(j);
//		}
//		System.out.println();
		return filtered;
	}

	/**
	 * Creates new {@link MatrixSubMatrixView}.
	 * @param original original
	 * @param rowIndexes included indexes.
	 * @param colIndexes included indexes.
	 */
	private MatrixSubMatrixView(IMatrix original, int[] rowIndexes, int[] colIndexes) {

		this.original = original;
		this.colIndexes = colIndexes;
		this.rowIndexes = rowIndexes;
	}
	
	@Override
	public int getRowsCount() {

		return rowIndexes.length;
	}

	@Override
	public int getColsCount() {
		
		return colIndexes.length;
	}

	@Override
	public double get(int row, int col) {

		if(!LinAlgDefaults.checkIfInside(row, this.getRowsCount()) || 
				!LinAlgDefaults.checkIfInside(col, this.getColsCount())) {
			
			throw new IndexOutOfBoundsException("Index out of bounds in this view.");
		}
		int rowInOriginal = this.rowIndexes[row];
		int colInOriginal = this.colIndexes[col];
		
		return original.get(rowInOriginal, colInOriginal);
	}

	/**
	 * Returns true if given array does not contain given index.
	 * @param indexes
	 * @param row
	 * @return
	 */
	private static boolean containsIndex(int[] indexes, int index) {
		
		for(int i : indexes) {
			if(i == index) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IMatrix set(int row, int col, double value) {

		if(!containsIndex(this.rowIndexes, row) && !containsIndex(this.colIndexes, col)) {
			throw new IndexOutOfBoundsException("Index out of bounds in this view.");
		}
		
		int rowInOriginal = this.rowIndexes[row];
		int colInOriginal = this.colIndexes[col];
		
		return original.set(rowInOriginal, colInOriginal, value);
	}

	@Override
	public IMatrix copy() {
		
		IMatrix copy = this.newInstance(this.getRowsCount() , this.getColsCount());
		
		for(int i = 0; i < this.getRowsCount(); i++) {
			
			for(int j = 0; j < this.getColsCount(); j++) {
				
				copy.set(i, j, this.get(i, j));
			}
		}
		
		return copy;
	}

	@Override
	public IMatrix newInstance(int rows, int cols) {
		
		return original.newInstance(rows, cols);
	}
	
	@Override
	public IMatrix subMatrix(int row, int col, boolean liveView) {
		
//		if(!containsIndex(this.rowIndexes, row) && !containsIndex(this.colIndexes, col)) {
//			throw new IndexOutOfBoundsException("Index out of bounds in this view.");
//		}
		
		MatrixSubMatrixView view = new MatrixSubMatrixView(this, filterInts(row, makeIntArray(getRowsCount())), filterInts(col, makeIntArray(getColsCount())));
		if(liveView) {
			
			return view;
		}
		else {
			
			IMatrix subMat = this.newInstance(view.rowIndexes.length, view.colIndexes.length);
			
			for(int i = 0; i < view.rowIndexes.length; i++) {
				for(int j = 0; j < view.colIndexes.length; j++) {
					
					subMat.set(i, j, view.get(view.rowIndexes[i], view.colIndexes[j]));
				}
			}
			
			return subMat;
		}
		
	}

	
	
	
	
}
