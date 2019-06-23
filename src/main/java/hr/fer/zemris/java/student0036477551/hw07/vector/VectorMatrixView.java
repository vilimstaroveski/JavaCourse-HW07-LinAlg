package hr.fer.zemris.java.student0036477551.hw07.vector;

import hr.fer.zemris.java.student0036477561.hw07.linearna.LinAlgDefaults;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.UnmodifiableObjectException;

/**
 * Class represents a vector view on a one rowed or one columned matrix.
 * @author Vilim Staroveški
 *
 */
public class VectorMatrixView extends AbstractVector {

	/**
	 * Dimension of vector.
	 */
	private int dimension;
	
	/**
	 * Tells is original matrix one rowed.
	 */
	private boolean rowMatrix;
	
	/**
	 * Original matrix
	 */
	private IMatrix original;
	
	/**
	 * Creates new {@link VectorMatrixView}.
	 * @param original original matrix
	 */
	public VectorMatrixView(IMatrix original) {

		this.original = original;
		this.dimension = Math.max(original.getRowsCount(), original.getColsCount());
		this.rowMatrix = original.getRowsCount() < original.getColsCount();
	}
	
	@Override
	public double get(int index) {
		
		if(!LinAlgDefaults.checkIfInside(index, dimension)) {
			throw new IncompatibleOperandException("Index out of bounds.");
		}
		return rowMatrix ? original.get(0, index) : original.get(index, 0);
	}

	@Override
	public IVector set(int index, double value)
			throws UnmodifiableObjectException {
		
		if(!LinAlgDefaults.checkIfInside(index, dimension)) {
			throw new IncompatibleOperandException("Index out of bounds.");
		}
		original.set(rowMatrix ? 0 : index, 
					 rowMatrix ? index : 0, 
					 value);
		
		return this;
	}

	@Override
	public int getDimension() {

		return dimension;
	}

	@Override
	public IVector copy() {

		double[] elements = new double[this.dimension];
		for(int i = 0; i < this.dimension; i++) {
			elements[i] = this.get(i);
		}
		return new Vector(elements);
	}

	@Override
	public IVector newInstance(int dimension) {
		
		if(dimension < 1) {
			throw new IncompatibleOperandException("Dimension cant be negative number.");
		}
		return LinAlgDefaults.defaultVector(dimension);
	}

}
