package hr.fer.zemris.java.student0036477561.hw07.matrix;

import static org.junit.Assert.*;
import hr.fer.zemris.java.student0036477551.hw07.matrix.Matrix;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IncompatibleOperandException;

import org.junit.Test;

public class AbstractMatrixTest {

	@Test
	public void testAdd() {
		
		double value = 2.34;
		double[][] elements = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double value2 = 1;
		double[][] elements2 = new double[2][2];
		// {1, 1}, {1, 1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements2[i][j] = value2;
			}
		}
		IMatrix matrix2 = new Matrix(2, 2, elements2, false);
		
		double[][] expected = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				expected[i][j] = value + i + j + 1;
			}
		}
		IMatrix result = matrix.add(matrix2);
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
		assertEquals(result, matrix);
	}
	
	@Test
	public void testNAdd() {
		
		double value = 2.34;
		double[][] elements = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double value2 = 1;
		double[][] elements2 = new double[2][2];
		// {1, 1}, {1, 1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements2[i][j] = value2;
			}
		}
		IMatrix matrix2 = new Matrix(2, 2, elements2, false);
		
		double[][] expected = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				expected[i][j] = value + i + j + 1;
			}
		}
		IMatrix result = matrix.nAdd(matrix2);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertNotEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
		assertNotEquals(result, matrix);
		
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testAddException() {
		
		double value = 2.34;
		double[][] elements = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double value2 = 1;
		double[][] elements2 = new double[2][1];
		// {1, 1}, {1, 1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 1; j++) {
				elements2[i][j] = value2;
			}
		}
		IMatrix matrix2 = new Matrix(2, 1, elements2, false);
		
		matrix.add(matrix2);
	}
	
	@Test
	public void testSub() {
		
		double value = 2.34;
		double[][] elements = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double value2 = 1;
		double[][] elements2 = new double[2][2];
		// {1, 1}, {1, 1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements2[i][j] = value2;
			}
		}
		IMatrix matrix2 = new Matrix(2, 2, elements2, false);
		
		double[][] expected = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				expected[i][j] = value + i + j - 1;
			}
		}
		IMatrix result = matrix.sub(matrix2);
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
		assertEquals(result, matrix);
	}
	
	@Test
	public void testNSub() {
		
		double value = 2.34;
		double[][] elements = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double value2 = 1;
		double[][] elements2 = new double[2][2];
		// {1, 1}, {1, 1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements2[i][j] = value2;
			}
		}
		IMatrix matrix2 = new Matrix(2, 2, elements2, false);
		
		double[][] expected = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				expected[i][j] = value + i + j - 1;
			}
		}
		IMatrix result = matrix.nSub(matrix2);
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
		assertNotEquals(result, matrix);
		
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testSubException() {
		
		double value = 2.34;
		double[][] elements = new double[2][2];
		// {2.34, 3.34}, {3.34, 4.34}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double value2 = 1;
		double[][] elements2 = new double[2][1];
		// {1, 1}, {1, 1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 1; j++) {
				elements2[i][j] = value2;
			}
		}
		IMatrix matrix2 = new Matrix(2, 1, elements2, false);
		
		matrix.sub(matrix2);
	}
	
	@Test
	public void testMul() {
		
		double value = 2.0;
		double[][] elements = new double[2][2];
		// {2.0, 3.0}, {3.0, 4.0}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double value2 = 2;
		double[][] elements2 = new double[2][2];
		// {2, 2}, {2, 2}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements2[i][j] = value2;
			}
		}
		IMatrix matrix2 = new Matrix(2, 2, elements2, false);
		
		double[][] expected = new double[2][2];
		expected[0][0] = 10;
		expected[0][1] = 10;
		expected[1][0] = 14;
		expected[1][1] = 14;
		
		IMatrix result = matrix.nMultiply(matrix2);
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
	}
	
	@Test
	public void testMul2() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, false);
		
		double value2 = 2;
		double[][] elements2 = new double[2][2];
		// {2, 1}, {0, -1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements2[i][j] = value2-2*i-j;
			}
		}
		IMatrix matrix2 = new Matrix(2, 2, elements2, false);
		
		double[][] expected = new double[3][2];
		expected[0][0] = 4;
		expected[0][1] = -1;
		expected[1][0] = 6;
		expected[1][1] = -1;
		expected[2][0] = 8;
		expected[2][1] = -1;
		
		IMatrix result = matrix.nMultiply(matrix2);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testMulException() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, false);
		
		double value2 = 2;
		double[][] elements2 = new double[2][2];
		// {2, 1}, {0, -1}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements2[i][j] = value2-2*i-j;
			}
		}
		IMatrix matrix2 = new Matrix(2, 2, elements2, false);
		
		
		IMatrix result = matrix2.nMultiply(matrix);
	}
	
	@Test
	public void testToArray() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, false);
		
		double[][] expected = new double[3][2];
		expected[0][0] = 2;
		expected[0][1] = 3;
		expected[1][0] = 3;
		expected[1][1] = 4;
		expected[2][0] = 4;
		expected[2][1] = 5;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.toArray()[i][j], 0.000001);
			}
		}
	}
	
	@Test
	public void testScalarMultiply() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, false);
		
		double[][] expected = new double[3][2];
		expected[0][0] = 4;
		expected[0][1] = 6;
		expected[1][0] = 6;
		expected[1][1] = 8;
		expected[2][0] = 8;
		expected[2][1] = 10;
		
		IMatrix result = matrix.scalarMultiply(2);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test
	public void testNScalarMultiply() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, false);
		
		double[][] expected = new double[3][2];
		expected[0][0] = 4;
		expected[0][1] = 6;
		expected[1][0] = 6;
		expected[1][1] = 8;
		expected[2][0] = 8;
		expected[2][1] = 10;
		
		IMatrix result = matrix.nScalarMultiply(2);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertNotEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testMakeIdentityException() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, false);
		matrix.makeIdentity();
	}
	
	@Test
	public void testMakeIdentity() {
		
		double value = 2.0;
		double[][] elements = new double[2][2];
		// {2.0, 3.0}, {3.0, 4.0}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(2, 2, elements, false);
		
		double[][] expected = new double[3][2];
		expected[0][0] = 1;
		expected[0][1] = 0;
		expected[1][0] = 0;
		expected[1][1] = 1;
		
		IMatrix result = matrix.makeIdentity();
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], result.get(i, j), 0.000001);
			}
		}
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
