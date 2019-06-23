package hr.fer.zemris.java.student0036477551.hw07.vector;

import java.util.Arrays;

import hr.fer.zemris.java.student0036477551.hw07.matrix.Matrix;
import hr.fer.zemris.java.student0036477551.hw07.matrix.MatrixVectorView;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.UnmodifiableObjectException;

/**
 * Class represents a vector. Implements {@link IVector}. See {@link IVector} for more details.
 * @author Vilim Staroveški
 *
 */
public abstract class AbstractVector implements IVector{

	@Override
	public abstract double get(int index);

	@Override
	public abstract IVector set(int index, double value) throws UnmodifiableObjectException;

	@Override
	public abstract int getDimension();

	@Override
	public abstract IVector copy();

	@Override
	public IVector copyPart(int n){
		
		IVector copy = newInstance(n);
		double[] arrayOfCopy = Arrays.copyOf(this.toArray(), n);
		for(int i = 0; i < n; i++) {
			copy.set(i, arrayOfCopy[i]);
		}
		
		return copy;
	}

	@Override
	public abstract IVector newInstance(int dimension);

	@Override
	public IVector add(IVector other) throws IncompatibleOperandException {

		if(this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Vector dimensions does not "
					+ "match so add operation is not possible.");
		}
		for(int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i)+other.get(i));
		}
		return this;
	}

	@Override
	public IVector nAdd(IVector other) throws IncompatibleOperandException {

		return this.copy().add(other);
	}

	@Override
	public IVector sub(IVector other) throws IncompatibleOperandException {

		if(this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Vector dimensions does not "
					+ "match so sub operation is not possible.");
		}
		for(int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i)-other.get(i));
		}
		return this;
	}

	@Override
	public IVector nSub(IVector other) throws IncompatibleOperandException {

		return this.copy().sub(other);
	}

	@Override
	public IVector scalarMultiply(double byValue) {

		for(int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i)*byValue);
		}
		return this;
	}

	@Override
	public IVector nScalarMultiply(double byValue) {

		return this.copy().scalarMultiply(byValue);
	}

	@Override
	public double norm() {

		double norm = 0;
		for(int i = this.getDimension()-1; i >= 0; i--) {
			norm += Math.pow(this.get(i), 2);
		}
		return Math.sqrt(norm);
	}

	@Override
	public IVector normalize() {

		double norm = this.norm();
		if(norm == 0) {
			throw new IncompatibleOperandException("Vector is a nul vector and therefore"
					+ " it can not be normalized.");
		}
		for(int i = this.getDimension()-1; i >= 0; i--) {
			this.set(i, this.get(i) / norm);
		}
		return this;
	}

	@Override
	public IVector nNormalize() {

		return this.copy().normalize();
	}

	@Override
	public double cosine(IVector other) throws IncompatibleOperandException {

		if(this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Vector dimensions does not "
					+ "match so cosine does not exists.");
		}
		if(this.norm() == 0 || other.norm() == 0) {
			throw new IncompatibleOperandException("One or more vectors are nul vector and therefore"
					+ " they dont have angle between them.");
		}
		double scalarProduct = this.scalarProduct(other);
		double norm = this.norm() * other.norm();
		
		return scalarProduct / norm;
	}

	@Override
	public double scalarProduct(IVector other)
			throws IncompatibleOperandException {

		if(this.getDimension() != other.getDimension()) {
			throw new IncompatibleOperandException("Vector dimensions does not "
					+ "match so cosine does not exists.");
		}
		double scalarProduct = 0;
		for(int i = this.getDimension()-1; i >= 0; i--) {
			scalarProduct += this.get(i) * other.get(i);
		}
		
		return scalarProduct;
	}

	@Override
	public IVector nVectorProduct(IVector other)
			throws IncompatibleOperandException {

		if(this.getDimension() != 3 || other.getDimension() != 3) {
			throw new IncompatibleOperandException("One or more vectors are not in 3"
					+ " dimensions so the operation vector product cannot be done.");
		}
		IVector vectorProductResult = newInstance(3);
		
		double a1 = this.get(0);
		double b1 = other.get(0);
		double a2 = this.get(1);
		double b2 = other.get(1);
		double a3 = this.get(2);
		double b3 = other.get(2);
		
		vectorProductResult.set(0, (a2*b3 - a3*b2));
		vectorProductResult.set(1, -(a1*b3 - a3*b1));
		vectorProductResult.set(2, (a1*b2 - a2*b1));
		
		return vectorProductResult;
	}

	@Override
	public IVector nFromHomogeneus() {

		int dimensionOfCurrent = this.getDimension();
		if(dimensionOfCurrent < 2) {
			throw new UnsupportedOperationException("Vector is in one dimension "
					+ "so this operation is not supported.");
		}
		double lastComponent = this.get(dimensionOfCurrent-1);
		if(lastComponent == 0) {
			throw new UnsupportedOperationException("Vector's last component is 0 "
					+ "so division is not supported.");
		}
		IVector homogeneusResult = newInstance(dimensionOfCurrent-1);
		for(int i = homogeneusResult.getDimension()-1; i >= 0; i--) {
			homogeneusResult.set(i, this.get(i) / lastComponent);
		}
		
		return homogeneusResult;
	}

	@Override
	public IMatrix toRowMatrix(boolean liveView) {
		
		double[][] elements = new double[1][this.getDimension()];
		for(int i = 0; i < this.getDimension(); i++) {
			elements[0][i] = this.get(i);
		}
		
		return liveView ? new MatrixVectorView(this, true) : new Matrix(1, this.getDimension(), elements, false);
	}

	@Override
	public IMatrix toColumnMatrix(boolean liveView) {
		
		double[][] elements = new double[this.getDimension()][1];
		for(int i = 0; i < this.getDimension(); i++) {
			elements[i][0] = this.get(i);
		}
		
		return liveView ? new MatrixVectorView(this, false) : new Matrix(this.getDimension(), 1, elements, false);
	}

	@Override
	public double[] toArray() {

		double[] elements = new double[this.getDimension()];
		for(int i = this.getDimension() - 1; i >= 0; i--) {
			elements[i] = this.get(i);
		}
		return elements;
	}
	
	public String toString(int precision) {
		
		String formated = "";
		for(int i = 0; i < this.getDimension(); i++) {
			formated += String.format("%."+precision+"f ", this.get(i));
		}
		
		return formated.trim();
	}
	
	@Override
	public String toString() {
		
		return toString(3);
	}

}
