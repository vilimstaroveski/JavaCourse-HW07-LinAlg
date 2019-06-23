package hr.fer.zemris.java.student0036477561.hw07.matrix;

import static org.junit.Assert.*;
import hr.fer.zemris.java.student0036477551.hw07.matrix.Matrix;
import hr.fer.zemris.linearna.IMatrix;

import org.junit.Test;

public class MatrixTransposeViewTest {

	@Test
	public void testTransposeNotLive() {

		IMatrix matrix = Matrix.parseSimple("2 3 | 3 4 | 4 5");
		IMatrix matrixTrans = matrix.nTranspose(false);
		
		double[][] expected = new double[2][3];
		expected[0][0] = 2;
		expected[0][1] = 3;
		expected[1][0] = 3;
		expected[1][1] = 4;
		expected[0][2] = 4;
		expected[1][2] = 5;
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				assertEquals(expected[i][j], matrixTrans.toArray()[i][j], 0.000001);
			}
		}
		matrix.add(matrix);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				assertEquals(expected[i][j], matrixTrans.toArray()[i][j], 0.000001);
			}
		}
		
		assertEquals(2, matrixTrans.getRowsCount());
		assertEquals(3, matrixTrans.getColsCount());
		
		IMatrix copy = matrixTrans.copy();
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				assertEquals(matrixTrans.get(i, j), copy.get(i, j), 0.000001);
			}
		}
		
		matrixTrans.set(1, 2, 44);
		assertNotEquals(matrixTrans.get(1, 2), matrix.get(2, 1), 0.000001);
		
		matrix.set(0, 1, 222);
		assertNotEquals(matrixTrans.get(0, 1), matrix.get(1, 0), 0.000001);
		
		assertEquals(matrixTrans.get(1, 1), copy.get(1, 1), 0.000001);
		
		matrixTrans.set(1, 1, 100);
		assertNotEquals(100, copy.get(1, 1));
		
		IMatrix matNewInstance = matrixTrans.newInstance(4, 3);
		
		assertEquals(4, matNewInstance.getRowsCount());
		assertEquals(3, matNewInstance.getColsCount());
	}
	
	@Test
	public void testTransposeLive() {

		IMatrix matrix = Matrix.parseSimple("2 3 | 3 4 | 4 5");
		IMatrix matrixTrans = matrix.nTranspose(true);
		
		double[][] expected = new double[2][3];
		expected[0][0] = 2;
		expected[0][1] = 3;
		expected[1][0] = 3;
		expected[1][1] = 4;
		expected[0][2] = 4;
		expected[1][2] = 5;
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				assertEquals(expected[i][j], matrixTrans.toArray()[i][j], 0.000001);
			}
		}
		matrix.add(matrix);
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				assertEquals(matrix.get(i, j), matrixTrans.get(j, i), 0.000001);
			}
		}
		
		assertEquals(2, matrixTrans.getRowsCount());
		assertEquals(3, matrixTrans.getColsCount());
		
		matrixTrans.set(1, 2, 44);
		assertEquals(matrixTrans.get(1, 2), matrix.get(2, 1), 0.000001);
		
		matrix.set(0, 1, 222);
		assertEquals(matrixTrans.get(0, 1), matrix.get(1, 0), 0.000001);
		
		IMatrix copy = matrixTrans.copy();
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				assertEquals(matrixTrans.get(i, j), copy.get(i, j), 0.000001);
			}
		}
		
		assertEquals(matrixTrans.get(1, 1), copy.get(1, 1), 0.000001);
		
		matrixTrans.set(1, 1, 100);
		assertNotEquals(100, copy.get(1, 1));
		
		
		IMatrix matNewInstance = matrixTrans.newInstance(4, 3);
		assertEquals(4, matNewInstance.getRowsCount());
		assertEquals(3, matNewInstance.getColsCount());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
