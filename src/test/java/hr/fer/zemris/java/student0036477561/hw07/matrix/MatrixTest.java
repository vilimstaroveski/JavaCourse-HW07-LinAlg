package hr.fer.zemris.java.student0036477561.hw07.matrix;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import hr.fer.zemris.java.student0036477551.hw07.matrix.Matrix;
import hr.fer.zemris.linearna.IMatrix;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testConstructor() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, true);
		
		elements[0][0] = 4;
		elements[0][1] = 6;
		elements[1][0] = 6;
		elements[1][1] = 8;
		elements[2][0] = 8;
		elements[2][1] = 10;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(elements[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testConstructorException() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(-3, 2, elements, true);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(elements[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test
	public void testDefaultConstructor() {
		
		IMatrix matrix = new Matrix(3, 2);
		
		double[][] expected = new double[3][2];
		expected[0][0] = 0;
		expected[0][1] = 0;
		expected[1][0] = 0;
		expected[1][1] = 0;
		expected[2][0] = 0;
		expected[2][1] = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testDefaultConstructorException() {
		
		@SuppressWarnings("unused")
		IMatrix matrix = new Matrix(-3, 2);
		
	}
	
	@Test
	public void testGetters() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, true);
		
		assertEquals(3, matrix.getRowsCount());
		assertEquals(2, matrix.getColsCount());
		
	}
	
	@Test
	public void testSetters() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, true);
		matrix.set(2, 1, 100);
		matrix.set(0, 0, -45);
		double[][] expected = new double[3][2];
		
		expected[0][0] = -45;
		expected[0][1] = 3;
		expected[1][0] = 3;
		expected[1][1] = 4;
		expected[2][0] = 4;
		expected[2][1] = 100;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test
	public void testNewInstance() {
		
		double value = 2.0;
		double[][] elements = new double[3][2];
		// {2.0, 3.0}, {3.0, 4.0}, {4.0, 5.0}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				elements[i][j] = value + i + j;
			}
		}
		IMatrix matrix = new Matrix(3, 2, elements, true);

		IMatrix matrixNewInstance = matrix.newInstance(3, 2);
		
		double[][] expected = new double[3][2];
		
		expected[0][0] = 0;
		expected[0][1] = 0;
		expected[1][0] = 0;
		expected[1][1] = 0;
		expected[2][0] = 0;
		expected[2][1] = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrixNewInstance.get(i, j), 0.000001);
			}
		}
	}
	
	@Test
	public void testParseSimple() {
		
		IMatrix matrix = Matrix.parseSimple("2.0 3.0 | 3.0 4.0 | 4.0 5.0");

		
		double[][] expected = new double[3][2];
		
		expected[0][0] = 2;
		expected[0][1] = 3;
		expected[1][0] = 3;
		expected[1][1] = 4;
		expected[2][0] = 4;
		expected[2][1] = 5;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testParseSimpleException() {
		
		IMatrix matrix = Matrix.parseSimple("2.0 a | 3.0 4.0 | 4.0 5.0");

		
		double[][] expected = new double[3][2];
		
		expected[0][0] = 2;
		expected[0][1] = 3;
		expected[1][0] = 3;
		expected[1][1] = 4;
		expected[2][0] = 4;
		expected[2][1] = 5;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	@Test
	public void testParseSimple2() {
		
		IMatrix matrix = Matrix.parseSimple(" 2.0   3.0 |    3.0    4.0 | 4.0     5.0  ");

		
		double[][] expected = new double[3][2];
		
		expected[0][0] = 2;
		expected[0][1] = 3;
		expected[1][0] = 3;
		expected[1][1] = 4;
		expected[2][0] = 4;
		expected[2][1] = 5;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(expected[i][j], matrix.get(i, j), 0.000001);
			}
		}
	}
	
	@Test
	public void testToArray() {
		
		IMatrix matrix = Matrix.parseSimple("2.0 3.0 | 3.0 4.0 | 4.0 5.0");

		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
