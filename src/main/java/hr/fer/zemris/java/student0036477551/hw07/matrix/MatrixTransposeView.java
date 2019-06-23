package hr.fer.zemris.java.student0036477551.hw07.matrix;

import hr.fer.zemris.linearna.IMatrix;

/**
 * Class that represents a Matrix that is transponted original matrix.
 * @author Vilim Staroveški
 *
 */
public class MatrixTransposeView extends AbstractMatrix {

	/**
	 * Original matrix
	 */
	private IMatrix original;
	
	/**
	 * Creates new {@link MatrixTransposeView}.
	 * @param original original matrix.
	 */
	public MatrixTransposeView(IMatrix original) {
		
		this.original = original;
	}
	
	@Override
	public int getRowsCount() {

		return original.getColsCount();
	}

	@Override
	public int getColsCount() {

		return original.getRowsCount();
	}

	@Override
	public double get(int row, int col) {

		return original.get(col, row);
	}

	@Override
	public IMatrix set(int row, int col, double value) {

		return original.set(col, row, value);
	}

	@Override
	public IMatrix copy() {
		
		return original.copy().nTranspose(true);
	}

	@Override
	public IMatrix newInstance(int rows, int cols) {

		return original.newInstance(rows, cols);
	}

}
