package hr.fer.zemris.java.student0036477551.hw07.matrix;

import java.security.InvalidParameterException;
import java.util.Arrays;

import hr.fer.zemris.linearna.IMatrix;

/**
 * Class extends {@link AbstractMatrix}. It represents a matrix.
 * @author Vilim Staroveški
 *
 */
public class Matrix extends AbstractMatrix {

	/**
	 * Property for storing values of matrix.
	 */
	private double[][] elements;
	
	/**
	 * Number of rows in matrix.
	 */
	private int rows;
	
	/**
	 * Number of columns in matrix.
	 */
	private int cols;
	
	/**
	 * Creates new {@link Matrix} with given number of rows and columns.
	 * @param rows Number of rows in matrix.
	 * @param cols Number of columns in matrix.
	 * @throws InvalidParameterException if rows or columns are not positive numbers.
	 */
	public Matrix(int rows, int cols) {
		
		if(rows < 1 || cols < 1) {
			throw new InvalidParameterException("Matrix sizes must be positive numbers.");
		}
		this.cols = cols;
		this.rows = rows;
		elements = new double[rows][cols];
	}
	
	/**
	 * Creates new {@link Matrix} with given number of rows and columns and 
	 * initialized array of elements.
	 * @param rows Number of rows in matrix.
	 * @param cols Number of columns in matrix.
	 * @param elements given elements.
	 * @param useGiven should the matrix use given array of elements, or copy them.
	 * @throws InvalidParameterException if rows or columns are not positive numbers.
	 */
	public Matrix(int rows, int cols, double[][] elements, boolean useGiven) {

		if(rows < 1 || cols < 1) {
			throw new InvalidParameterException("Matrix sizes must be positive numbers.");
		}
		this.elements = useGiven ? elements : Arrays.copyOf(elements, elements.length);
		if(useGiven) {
			
			this.elements = elements;
		}
		else {
			double[][] newElements = new double[rows][cols];
			for(int i = 0; i < rows; i++) {
				newElements[i] = Arrays.copyOf(elements[i], cols);
			}
			this.elements = newElements;
		}
		this.rows = rows;
		this.cols = cols;
	}

	@Override
	public int getRowsCount() {

		return rows;
	}

	@Override
	public int getColsCount() {
		
		return cols;
	}

	@Override
	public double get(int row, int col) {

		return elements[row][col];
	}

	@Override
	public IMatrix set(int row, int col, double value) {

		elements[row][col] = value;
		return this;
	}

	@Override
	public IMatrix copy() {

		return new Matrix(rows, cols, elements, false);
	}

	@Override
	public IMatrix newInstance(int rows, int cols) {

		return new Matrix(rows, cols);
	}
	
	/**
	 * Creates new {@link Matrix} from given {@link String}. 
	 * @param stringMatrix string to parse to {@link Matrix}
	 * @throws InvalidParameterException if given string is not given properly.
	 * @return new {@link Matrix}
	 */
	public static Matrix parseSimple(String stringMatrix) {
		
		String[] elementsInString = null;
		int numberOfRows = 0;
		int numberOfCols = 0;
		if(stringMatrix.contains("|")) {
			
			elementsInString = stringMatrix.trim().split("\\|");
			numberOfRows = elementsInString.length;
			numberOfCols = elementsInString[0].split("\\s+").length;
		}
		else {
			elementsInString = new String[1];
			elementsInString[0] = stringMatrix;
			numberOfCols = stringMatrix.split("\\s+").length;
			numberOfRows = 1;
		}
		
		double[][] array = new double[numberOfRows][numberOfCols];
		
		for(int i = 0; i < numberOfRows; i++) {
			
			String[] elementsInRow = elementsInString[i].trim().split("\\s+");
			for(int j = 0; j < numberOfCols; j++) {
				try {
					array[i][j] = Double.parseDouble(elementsInRow[j]);
				}catch(NumberFormatException e) {
					throw new InvalidParameterException("Given string contains unparsable parts!");
				}
			}
		}
		
		return new Matrix(numberOfRows, numberOfCols, array, false);
	}

	@Override
	public double[][] toArray() {
		return elements;
	}

//	public static void main(String[] args) {
//		double[][] ar11 = {{1.,2.,3.},{2.,1.,3.},{4.,5.,1.}};
//		double[][] ar12 = {{-1.,2.,-3.},{5.,-2.,7.},{-4.,-1.,3.}};
//		
//		List<Integer> input = new ArrayList<>();
//		
//		Scanner sc = new Scanner(System.in);
//		for(int i = 0; i<12; i++) {
//			input.add(sc.nextInt());
//		}
//		
//		
//		Matrix m1 = new Matrix(3, 3, ar11, true);
//		Matrix m2 = new Matrix(3, 3, ar12, true);
//		
//		System.out.println(m1.nMultiply(m2.nInvert()));
//		
//	}
}
