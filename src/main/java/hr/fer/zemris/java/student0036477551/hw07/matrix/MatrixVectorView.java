package hr.fer.zemris.java.student0036477551.hw07.matrix;

import hr.fer.zemris.java.student0036477561.hw07.linearna.LinAlgDefaults;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;

/**
 * Class represents a matrix that is a view on vector original.
 * @author Vilim Staroveški
 *
 */
public class MatrixVectorView extends AbstractMatrix {
	
	/**
	 * Tells if the matrix is row matrix. If false, then is column matrix.
	 */
	private boolean asRowMatrix;
	
	/**
	 * Orignial.
	 */
	private IVector original;
	
	/**
	 * Creates new {@link MatrixVectorView}.
	 * @param original original vector
	 * @param asRowMatrix Tells if the matrix is row matrix. If false, then is column matrix.
	 */
	public MatrixVectorView(IVector original, boolean asRowMatrix) {
		
		this.original = original;
		this.asRowMatrix = asRowMatrix;
	}

	@Override
	public int getRowsCount() {

		return asRowMatrix ? 1 : original.getDimension();
	}

	@Override
	public int getColsCount() {
		
		return asRowMatrix ? original.getDimension() : 1;
	}

	@Override
	public double get(int row, int col) {
		
		if(asRowMatrix) {
			if(!LinAlgDefaults.checkIfInside(col, original.getDimension()-1) || !LinAlgDefaults.checkIfInside(row, 0)) {
				throw new IncompatibleOperandException("Index out of bounds.");
			}
			return original.get(col);
		}
		else {
			if(!LinAlgDefaults.checkIfInside(row, original.getDimension()-1) || !LinAlgDefaults.checkIfInside(col, 0)) {
				throw new IncompatibleOperandException("Index out of bounds.");
			}
			return original.get(row);			
		}
	}

	@Override
	public IMatrix set(int row, int col, double value) {

		if(asRowMatrix) {
			if(!LinAlgDefaults.checkIfInside(col, original.getDimension()-1) || !LinAlgDefaults.checkIfInside(row, 0)) {
				throw new IncompatibleOperandException("Index out of bounds.");
			}
			original.set(col, value);
			return this;
		}
		else {
			if(!LinAlgDefaults.checkIfInside(row, original.getDimension()-1) || !LinAlgDefaults.checkIfInside(col, 0)) {
				throw new IncompatibleOperandException("Index out of bounds.");
			}
			original.set(row, value);			
			return this;
		}
	}

	@Override
	public IMatrix copy() {
		
		double[][] elements = new double[this.getRowsCount()][this.getColsCount()];
		
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				elements[i][j] = this.get(i, j);
			}
		}
		return new Matrix(this.getRowsCount(), this.getColsCount(), elements, false);
	}

	@Override
	public IMatrix newInstance(int rows, int cols) {
		
		if(rows < 1 || cols < 1) {
			throw new IncompatibleOperandException("Indexes cant be negative numbers.");
		}
		return LinAlgDefaults.defaultMatrix(rows, cols);
	}

}
