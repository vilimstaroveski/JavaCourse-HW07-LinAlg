package hr.fer.zemris.java.student0036477561.hw07.linearna;


import hr.fer.zemris.java.student0036477551.hw07.matrix.Matrix;
import hr.fer.zemris.java.student0036477551.hw07.vector.Vector;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;

/**
 * Utility class for linear algebra.
 * @author Vilim Staroveški
 *
 */
public class LinAlgDefaults {

	/**
	 * Factory method for making new {@link IVector}s.
	 * @param dimension wanted dimension 
	 * @return new {@link IVector}s.
	 */
	public static IVector defaultVector(int dimension) {
		return new Vector(new double[dimension]);
	}
	
	/**
	 * Factory method for making new {@link IMatrix}.
	 * @param rows wanted number of rows
	 * @param cols wanted number of columns.
	 * @return
	 */
	public static IMatrix defaultMatrix(int rows, int cols) {
		return new Matrix(rows, cols);
	}
	
	/**
	 * Returns true if given index is in boundries 0 to maxIndex.
	 * @param index index
	 * @param maxIndex maximum index
	 * @return true if it is.
	 */
	public static boolean checkIfInside(int index, int maxIndex) {
		
		if(index > maxIndex || index < 0) {
			return false;
		}
		return true;
	}
}
