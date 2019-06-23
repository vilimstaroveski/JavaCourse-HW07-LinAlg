package hr.fer.zemris.java.student0036477551.hw07.vector;

import java.security.InvalidParameterException;
import java.util.Arrays;

import hr.fer.zemris.java.student0036477561.hw07.linearna.LinAlgDefaults;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.UnmodifiableObjectException;

/**
 * Extends {@link AbstractVector} and it is a concrete vector.
 * @author Vilim Staroveški
 *
 */
public class Vector extends AbstractVector {

	/**
	 * Vector components.
	 */
	private double[] elements;
	
	/**
	 * Vector dimension.
	 */
	private int dimensions;
	
	/**
	 * Is the vector modifiable.
	 */
	private boolean readOnly;
	
	/**
	 * Creates new Vector from given components.
	 * @param elements given components.
	 */
	public Vector(double[] elements) {
		
		this.elements = Arrays.copyOf(elements, elements.length);
		this.dimensions = elements.length;
		this.readOnly = false;
	}
	
	/**
	 * Creates new {@link Vector} with given elements. If useGiven is true,
	 * vector will use given elements. Otherwise, it will copy them.
	 * @param readOnly is vector read only.
	 * @param useGiven should vector use elements or copy them.
	 * @param elements elements.
	 */
	public Vector(boolean readOnly, boolean useGiven, double[] elements) {
		
		this.elements = useGiven ? elements : Arrays.copyOf(elements, elements.length);
		this.dimensions = elements.length;
		this.readOnly = readOnly;
	}

	@Override
	public double get(int index) {
		
		if(!LinAlgDefaults.checkIfInside(index, this.getDimension())) {
			throw new IncompatibleOperandException("Index out of bounds.");
		}
		
		return this.elements[index];
	}

	@Override
	public IVector set(int index, double value)
			throws UnmodifiableObjectException {

		if(readOnly) {
			throw new UnmodifiableObjectException("Vector is read only!");
		}
		if(!LinAlgDefaults.checkIfInside(index, this.getDimension())) {
			throw new IncompatibleOperandException("Index out of bounds.");
		}
		this.elements[index] = value;
		
		return this;
	}

	@Override
	public int getDimension() {
		
		return this.dimensions;
	}

	@Override
	public IVector copy() {
		
		return new Vector(elements);
	}

	@Override
	public IVector newInstance(int dimension) {

		if(dimension < 1) {
			throw new IncompatibleOperandException("Dimension cant be negative number.");
		}
		return new Vector(new double[dimension]);
	}
	
	/**
	 * Creates new {@link Vector} from given {@link String}.
	 * @param vectorInString string representing vector.
	 * @return new {@link Vector}
	 */
	public static IVector parseSimple(String vectorInString) {
		
		String[] elementsInString = vectorInString.split("\\s+");
		double[] array = new double[elementsInString.length];
		try {
			
			for(int i = 0; i < elementsInString.length; i++) {
				array[i] = Double.parseDouble(elementsInString[i]);
			}
			
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("Given string contains unparsable parts!");
		}
		
		return new Vector(array);
 	}
	
	
	
	
	
	
	
	
}
