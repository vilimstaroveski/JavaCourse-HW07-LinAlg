package hr.fer.zemris.java.student0036477551.hw07.matrix;

import hr.fer.zemris.java.student0036477551.hw07.vector.Vector;
import hr.fer.zemris.java.student0036477551.hw07.vector.VectorMatrixView;
import hr.fer.zemris.java.student0036477561.hw07.linearna.LinAlgDefaults;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;

/**
 * Abstract class for interface {@link IMatrix}. It contains basic methods for operations 
 * with matrixes.
 * 
 * @author Vilim Staroveški
 *
 */
public abstract class AbstractMatrix implements IMatrix {

	@Override
	public abstract int getRowsCount();

	@Override
	public abstract int getColsCount();

	@Override
	public abstract double get(int row, int col);

	@Override
	public abstract IMatrix set(int row, int col, double value);

	@Override
	public abstract IMatrix copy();

	@Override
	public abstract IMatrix newInstance(int rows, int cols);

	@Override
	public IMatrix nTranspose(boolean liveView) {

		if(liveView) {
			return new MatrixTransposeView(this);
		}
		else {
			
			IMatrix transpose = newInstance(this.getColsCount(), this.getRowsCount());
			for(int i = 0; i < transpose.getRowsCount(); i++) {
				for(int j = 0; j < transpose.getColsCount(); j++) {
					transpose.set(i, j, this.get(j, i));
				}
			}
			return transpose;
		}
	}

	@Override
	public IMatrix add(IMatrix other) {

		if(this.getRowsCount() != other.getRowsCount() 
				|| this.getColsCount() != other.getColsCount()) {
			
			throw new IncompatibleOperandException("Matrix does not match in sizes.");
		}
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				
				double otherValue = other.get(i, j);
				double value = this.get(i, j);
				this.set(i, j, value + otherValue);
			}
		}
		return this;
	}

	@Override
	public IMatrix nAdd(IMatrix other) {

		return this.copy().add(other);
	}

	@Override
	public IMatrix sub(IMatrix other) {

		if(this.getRowsCount() != other.getRowsCount() 
				|| this.getColsCount() != other.getColsCount()) {
			
			throw new IncompatibleOperandException("Matrix does not match in sizes.");
		}
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				
				double otherValue = other.get(i, j);
				double value = this.get(i, j);
				this.set(i, j, value - otherValue);
			}
		}
		return this;
	}

	@Override
	public IMatrix nSub(IMatrix other) {

		return this.copy().sub(other);
	}

	@Override
	public IMatrix nMultiply(IMatrix other) {

		if(this.getColsCount() != other.getRowsCount()) {
			
			throw new IncompatibleOperandException("Matrix can not be multiplied.");
		}
		IMatrix matrixResult = newInstance(this.getRowsCount(), other.getColsCount());
		
		for(int i = 0; i < matrixResult.getRowsCount(); i++) {
			for(int j = 0; j < matrixResult.getColsCount(); j++) {
				
				double result = 0;
				for(int n = 0; n < this.getColsCount(); n++) {
					
					result += this.get(i, n)*other.get(n, j);
				}
				matrixResult.set(i, j, result);
			}
		}
		return matrixResult;
	}

	@Override
	public double determinant() throws IncompatibleOperandException {

		if(this.getColsCount() != this.getRowsCount()) {
			
			throw new IncompatibleOperandException("Matrix must have the same number of cols and rows to"
					+ "calculate determinant.");
		}
		
		
		if(this.getRowsCount() == 1) {
			return this.get(0, 0);
		}
		if(this.getRowsCount() == 2) {
			return (this.get(0, 0)*this.get(1, 1)) 
					- (this.get(0, 1)*this.get(1, 0));
		}
		double sum = 0.0;
		for(int i = 0; i < this.getColsCount(); i++) {

			sum += Math.pow(-1, i) * this.get(0, i) * this.subMatrix(0, i, true).determinant();
		}
		
		return sum;
	}

	@Override
	public IMatrix subMatrix(int row, int col, boolean liveView) {

		if(!LinAlgDefaults.checkIfInside(row, this.getRowsCount())) {
			throw new IncompatibleOperandException("Matrix does not contains row "+ row);
		}
		if(!LinAlgDefaults.checkIfInside(col, this.getColsCount())) {
			throw new IncompatibleOperandException("Matrix does not contains col "+ col);
		}
		
		if(liveView) {
			
			return new MatrixSubMatrixView(this, row, col);
		}
		else {
			
			boolean rowPassed = false;
			boolean colPassed = false;
			IMatrix subMat = newInstance(this.getRowsCount()-1, this.getColsCount()-1);
			
			for(int i = 0; i < this.getRowsCount(); i++) {
				if( i == row) {
					rowPassed = true;
					continue;
				}
				for(int j = 0; j < this.getColsCount(); j++) {
					if(j == col) {
						colPassed = true;
						continue;
					}
					subMat.set((rowPassed ? i-1 : i), 
							(colPassed ? j-1 : j), 
							this.get(i, j));
				}
				colPassed = false;
			}
			return subMat;
		}
	}

	@Override
	public IMatrix nInvert() {
		
		if(this.getColsCount() != this.getRowsCount()) {
			
			throw new IncompatibleOperandException("Matrix had to have the same number of cols and rows.");
		}
		
		IMatrix inverted = newInstance(this.getRowsCount(), this.getColsCount());
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				inverted.set(i, j, this.subMatrix(i, j, true).determinant() * Math.pow(-1, i+j));
			}
		}
		
		return inverted.nTranspose(false).scalarMultiply(1 / this.determinant());
	}

	@Override
	public double[][] toArray() {

		double[][] array = new double[this.getRowsCount()][this.getColsCount()];
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				array[i][j] = this.get(i, j);
			}
		}
		return array;
	}

	@Override
	public IVector toVector(boolean liveView) {

		if(this.getRowsCount() > 1 && this.getColsCount() > 1) {
			throw new IncompatibleOperandException("Matrix has to be one-columned or one-rowed.");
		}
		
		double[] elements = new double[Math.max(this.getRowsCount(), this.getColsCount())];
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				elements[Math.max(i, j)] = this.get(i, j);
			}
		}
		
		return liveView ? new VectorMatrixView(this) : new Vector(false, false, elements);
		
	}

	@Override
	public IMatrix nScalarMultiply(double value) {

		return this.copy().scalarMultiply(value);
	}

	@Override
	public IMatrix scalarMultiply(double value) {
		
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				this.set(i, j, value*this.get(i, j));
			}
		}
		return this;
	}

	@Override
	public IMatrix makeIdentity() {
		
		if(this.getColsCount() != this.getRowsCount()) {
			
			throw new IncompatibleOperandException("Matrix had to have the same number of cols and rows.");
		}
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				
				this.set(i, j, (i == j) ? 1 : 0);
			}
		}
		return this;
	}
	
	public String toString(int precision) {
		
		String formated = "";
		for(int i = 0; i < this.getRowsCount(); i++) {
			for(int j = 0; j < this.getColsCount(); j++) {
				formated += String.format("%9."+precision+"f ", this.get(i, j));
			}
			formated += "\n";
		}
		return formated;
	}
	
	@Override
	public String toString() {
		
		return toString(3);
	}
}
