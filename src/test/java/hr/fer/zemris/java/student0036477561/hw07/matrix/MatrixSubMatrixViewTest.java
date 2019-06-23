package hr.fer.zemris.java.student0036477561.hw07.matrix;

import static org.junit.Assert.*;

import org.junit.Test;

import hr.fer.zemris.java.student0036477551.hw07.matrix.Matrix;
import hr.fer.zemris.java.student0036477551.hw07.matrix.MatrixSubMatrixView;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;

public class MatrixSubMatrixViewTest {

	@Test
	public void testDeterminant() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 | 3 4");
		assertEquals(-1, matrix.determinant(), 0.000001);
		
		IMatrix matrix2 = Matrix.parseSimple("2 3 5 | 3 4 2 | 1 0 3");
		assertEquals(-17, matrix2.determinant(), 0.000001);
		
		IMatrix matrix3 = Matrix.parseSimple("2 3 5 3 | 3 4 2 -2 | "
				+ "1 7 0 -3 | 4 2 0 -2 ");
		assertEquals(188, matrix3.determinant(), 0.000001);
				
		IMatrix matrix4 = Matrix.parseSimple("2 3 5 3 9 | 3 4 2 -2 9 | "
						+ "1 7 0 -3 11 | 4 2 0 -2 0 | -1 -5 10 -13 0");
		assertEquals(-8803.999999999996, matrix4.determinant(), 0.000001);
	}
	
	@Test
	public void testSubMatrixNotLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5 | 3 4 2 | 1 0 3");
		IMatrix sub = matrix.subMatrix(0, 1, false);
		assertEquals(3, sub.get(0, 0), 0.0000001);
		assertEquals(2, sub.get(0, 1), 0.0000001);
		assertEquals(1, sub.get(1, 0), 0.0000001);
		assertEquals(3, sub.get(1, 1), 0.0000001);
	}
	
	@Test
	public void testSubMatrixNotLive2() {
		
		IMatrix matrix = Matrix.parseSimple("1 4 5 9 | 2 3 5 6 | 3 4 2 4 | 1 0 3 1");
		System.out.println(matrix.toString());
		IMatrix sub = matrix.subMatrix(0, 3, false);
		System.out.println(sub.toString());
		sub = sub.subMatrix(0, 1, false);
		System.out.println(sub.toString());
		assertEquals(3, sub.get(0, 0), 0.0000001);
		assertEquals(2, sub.get(0, 1), 0.0000001);
		assertEquals(1, sub.get(1, 0), 0.0000001);
		assertEquals(3, sub.get(1, 1), 0.0000001);
		sub = sub.subMatrix(1, 1, false);
		assertEquals(3, sub.get(0, 0), 0.000001);
	}
	
	@Test
	public void testSubMatrixLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5 | 3 4 2 | 1 0 3");
		IMatrix sub = matrix.subMatrix(0, 1, true);
		
		assertEquals(3, sub.get(0, 0), 0.0000001);
		assertEquals(2, sub.get(0, 1), 0.0000001);
		assertEquals(1, sub.get(1, 0), 0.0000001);
		assertEquals(3, sub.get(1, 1), 0.0000001);
	}
	
	
	@Test
	public void testInverted() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5 | 3 4 2 | 1 0 3");
		IMatrix inverted = matrix.nInvert();
		
		assertEquals(-0.7058823, inverted.get(0, 0), 0.0000001);
		assertEquals(0.52941176, inverted.get(0, 1), 0.0000001);
		assertEquals(0.82352941, inverted.get(0, 2), 0.0000001);
		assertEquals(0.41176470, inverted.get(1, 0), 0.0000001);
		assertEquals(-0.0588235, inverted.get(1, 1), 0.0000001);
		assertEquals(-0.6470588, inverted.get(1, 2), 0.0000001);
		assertEquals(0.23529411, inverted.get(2, 0), 0.0000001);
		assertEquals(-0.1764705, inverted.get(2, 1), 0.0000001);
		assertEquals(0.05882352, inverted.get(2, 2), 0.0000001);
	}
	
	@Test
	public void testSubMatrixLive2() {
		
		IMatrix matrix = Matrix.parseSimple("1 4 5 9 | 2 3 5 6 | 3 4 2 4 | 1 0 3 1");
		
		IMatrix sub = matrix.subMatrix(0, 3, true);
		assertEquals(2, sub.get(0, 0), 0.0000001);
		assertEquals(3, sub.get(0, 1), 0.0000001);
		assertEquals(5, sub.get(0, 2), 0.0000001);
		assertEquals(3, sub.get(1, 0), 0.0000001);
		assertEquals(4, sub.get(1, 1), 0.0000001);
		assertEquals(2, sub.get(1, 2), 0.0000001);
		assertEquals(1, sub.get(2, 0), 0.0000001);
		assertEquals(0, sub.get(2, 1), 0.0000001);
		assertEquals(3, sub.get(2, 2), 0.0000001);
		
		sub = sub.subMatrix(0, 1, true);
		assertEquals(3, sub.get(0, 0), 0.0000001);
		assertEquals(2, sub.get(0, 1), 0.0000001);
		assertEquals(1, sub.get(1, 0), 0.0000001);
		assertEquals(3, sub.get(1, 1), 0.0000001);
		
		IMatrix copy = sub.copy();
		assertEquals(3, copy.get(0, 0), 0.0000001);
		assertEquals(2, copy.get(0, 1), 0.0000001);
		assertEquals(1, copy.get(1, 0), 0.0000001);
		assertEquals(3, copy.get(1, 1), 0.0000001);
		
		sub = sub.subMatrix(1, 1, true);
		assertEquals(3, sub.get(0, 0), 0.000001);
	}
	
	@Test
	public void testSubMatrixLive3() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5 3 9 | 3 4 2 -2 9 | "
				+ "1 7 0 -3 11 | 4 2 0 -2 0 | -1 -5 10 -13 0");
		System.out.println(matrix.toString());
		IMatrix sub = matrix.subMatrix(1, 3, true);
		IMatrix copyBegin = matrix;
		
		assertEquals(2, sub.get(0, 0), 0.0000001);
		assertEquals(3, sub.get(0, 1), 0.0000001);
		assertEquals(5, sub.get(0, 2), 0.0000001);
		assertEquals(9, sub.get(0, 3), 0.0000001);
		assertEquals(1, sub.get(1, 0), 0.0000001);
		assertEquals(7, sub.get(1, 1), 0.0000001);
		assertEquals(0, sub.get(1, 2), 0.0000001);
		assertEquals(11, sub.get(1, 3), 0.0000001);
		assertEquals(4, sub.get(2, 0), 0.0000001);
		assertEquals(2, sub.get(2, 1), 0.0000001);
		assertEquals(0, sub.get(2, 2), 0.0000001);
		assertEquals(0, sub.get(2, 3), 0.0000001);
		assertEquals(-1, sub.get(3, 0), 0.0000001);
		assertEquals(-5, sub.get(3, 1), 0.0000001);
		assertEquals(10, sub.get(3, 2), 0.0000001);
		assertEquals(0, sub.get(3, 3), 0.0000001);
		
		IMatrix copyInMiddle = sub = sub.subMatrix(1, 1, true);
		
		assertEquals(2, sub.get(0, 0), 0.0000001);
		assertEquals(5, sub.get(0, 1), 0.0000001);
		assertEquals(9, sub.get(0, 2), 0.0000001);
		assertEquals(4, sub.get(1, 0), 0.0000001);
		assertEquals(0, sub.get(1, 1), 0.0000001);
		assertEquals(0, sub.get(1, 2), 0.0000001);
		assertEquals(-1, sub.get(2, 0), 0.0000001);
		assertEquals(10, sub.get(2, 1), 0.0000001);
		assertEquals(0, sub.get(2, 2), 0.0000001);
		
		sub = sub.subMatrix(2, 0, true);
		
		assertEquals(5, sub.get(0, 0), 0.0000001);
		assertEquals(9, sub.get(0, 1), 0.0000001);
		assertEquals(0, sub.get(1, 0), 0.0000001);
		assertEquals(0, sub.get(1, 1), 0.0000001);
		
		sub.set(0, 0, 100);
		
		assertEquals(100, sub.get(0, 0), 0.000001);
		assertEquals(100, copyBegin.get(0, 2), 0.000001);
		assertEquals(100, copyInMiddle.get(0, 1), 0.000001);
	}
	
	@Test
	public void testSubMatrixNotLive3() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5 3 9 | 3 4 2 -2 9 | "
				+ "1 7 0 -3 11 | 4 2 0 -2 0 | -1 -5 10 -13 0");
		System.out.println(matrix.toString());
		IMatrix sub = matrix.subMatrix(1, 3, false);
		IMatrix copyBegin = matrix;
		
		assertEquals(2, sub.get(0, 0), 0.0000001);
		assertEquals(3, sub.get(0, 1), 0.0000001);
		assertEquals(5, sub.get(0, 2), 0.0000001);
		assertEquals(9, sub.get(0, 3), 0.0000001);
		assertEquals(1, sub.get(1, 0), 0.0000001);
		assertEquals(7, sub.get(1, 1), 0.0000001);
		assertEquals(0, sub.get(1, 2), 0.0000001);
		assertEquals(11, sub.get(1, 3), 0.0000001);
		assertEquals(4, sub.get(2, 0), 0.0000001);
		assertEquals(2, sub.get(2, 1), 0.0000001);
		assertEquals(0, sub.get(2, 2), 0.0000001);
		assertEquals(0, sub.get(2, 3), 0.0000001);
		assertEquals(-1, sub.get(3, 0), 0.0000001);
		assertEquals(-5, sub.get(3, 1), 0.0000001);
		assertEquals(10, sub.get(3, 2), 0.0000001);
		assertEquals(0, sub.get(3, 3), 0.0000001);
		
		IMatrix copyInMiddle = sub = sub.subMatrix(1, 1, false);
		
		assertEquals(2, sub.get(0, 0), 0.0000001);
		assertEquals(5, sub.get(0, 1), 0.0000001);
		assertEquals(9, sub.get(0, 2), 0.0000001);
		assertEquals(4, sub.get(1, 0), 0.0000001);
		assertEquals(0, sub.get(1, 1), 0.0000001);
		assertEquals(0, sub.get(1, 2), 0.0000001);
		assertEquals(-1, sub.get(2, 0), 0.0000001);
		assertEquals(10, sub.get(2, 1), 0.0000001);
		assertEquals(0, sub.get(2, 2), 0.0000001);
		
		sub = sub.subMatrix(2, 0, false);
		
		assertEquals(5, sub.get(0, 0), 0.0000001);
		assertEquals(9, sub.get(0, 1), 0.0000001);
		assertEquals(0, sub.get(1, 0), 0.0000001);
		assertEquals(0, sub.get(1, 1), 0.0000001);
		
		sub.set(0, 0, 100);
		
		assertEquals(100, sub.get(0, 0), 0.000001);
		assertNotEquals(100, copyBegin.get(0, 2), 0.000001);
		assertNotEquals(100, copyInMiddle.get(0, 1), 0.000001);
	}
	
	
}
