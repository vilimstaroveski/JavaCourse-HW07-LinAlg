package hr.fer.zemris.java.student0036477561.hw07.vector;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import hr.fer.zemris.java.student0036477551.hw07.vector.AbstractVector;
import hr.fer.zemris.java.student0036477551.hw07.vector.Vector;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;

import org.junit.Test;

public class AbstractVectorTest {

	@Test
	public void testAdd() {
		
		double[] elements = {1.0, 2.5, 4, 22};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {2.0, 5.0, 8, 44};
		IVector vector2 = new Vector(elements2);
		
		IVector result = vector.add(vector);
		
		for(int i = 0; i < 4; i++) {
			assertEquals(vector2.get(i), result.get(i), 0.000001);
		}
		
		assertEquals(result, vector);
		
		IVector addCopy = vector.nAdd(vector);
		
		assertNotEquals(result, addCopy);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testAddException() {
		double[] elements = {1.0, 2.5, 4, 22};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {2.0, 5.0, 8};
		IVector vector2 = new Vector(elements2);
		
		vector.add(vector2);
	}
	
	@Test
	public void testSub() {
		
		double[] elements = {1.0, 2.5, 4, 22};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {4.0, 2.5, 12, 20};
		IVector vector2 = new Vector(elements2);
		
		IVector result = vector2.sub(vector);
		
		double[] elements3 = {3.0, 0.0, 8, -2};
		IVector vectorExpected = new Vector(elements3);
		
		for(int i = 0; i < 4; i++) {
			assertEquals(vectorExpected.get(i), result.get(i), 0.000001);
		}
		
		assertEquals(result, vector2);
		
		IVector subCopy = vector2.nSub(vector);
		
		assertNotEquals(result, subCopy);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testSubException() {
		double[] elements = {1.0, 2.5, 4, 22};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {2.0, 5.0, 8};
		IVector vector2 = new Vector(elements2);
		
		vector.sub(vector2);
	}
	
	@Test
	public void testMul() {
		
		double[] elements = {1.0, 2.5, 4, -12};
		IVector vector = new Vector(elements);
		
		IVector result = vector.scalarMultiply(3);
		
		double[] elements3 = {3.0, 7.5, 12, -36};
		IVector vectorExpected = new Vector(elements3);
		
		for(int i = 0; i < 4; i++) {
			assertEquals(vectorExpected.get(i), result.get(i), 0.000001);
		}
		
		assertEquals(result, vector);
		
		IVector mulCopy = vector.nScalarMultiply(3);
		
		assertNotEquals(result, mulCopy);
	}
	@Test
	public void testMul2() {
		
		double[] elements = {1.0, 2.5, 4, -12};
		IVector vector = new Vector(elements);
		
		vector.nScalarMultiply(3);
		
		double[] elements3 = {3.0, 7.5, 12, -36};
		IVector vectorExpected = new Vector(elements3);
		
		for(int i = 0; i < 4; i++) {
			assertNotEquals(vectorExpected.get(i), vector.get(i), 0.000001);
		}
		
	}
	@Test
	public void testNorm() {
		
		double[] elements = {3, 4};
		IVector vector = new Vector(elements);
		
		assertEquals(5, vector.norm(), 0.0000001);
	}
	
	@Test
	public void testNormalise() {
		
		double[] elements = {3, 4};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {3.0/5, 4.0/5};
		IVector vectorExpected = new Vector(elements2);
		
		IVector result = vector.normalize();
		for(int i = 0; i < 2; i++) {
			assertEquals(vectorExpected.get(i), vector.get(i), 0.000001);
		}
		assertEquals(result, vector);
		
		
	}
	
	@Test
	public void testNNormalise() {
		
		double[] elements = {3, 4};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {3.0/5, 4.0/5};
		IVector vectorExpected = new Vector(elements2);
		
		IVector result = vector.nNormalize();
		for(int i = 0; i < 2; i++) {
			assertEquals(vectorExpected.get(i), result.get(i), 0.000001);
		}
		assertNotEquals(result, vector);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testNormaliseException() {
		
		double[] elements = {0, 0, 0};
		IVector vector = new Vector(elements);

		vector.normalize();
		
	}
	
	@Test
	public void testCosine() {
		
		double[] elements = {3.0, 4.0};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {4.0, 0.0};
		IVector vectorHorisontal = new Vector(elements2);
		
		assertEquals(0.6, vector.cosine(vectorHorisontal), 0.000001);
	}
	
	@Test
	public void testCosine2() {
		
		double[] elements = {7, 3.4, -12};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {-2, 3, 20};
		IVector vector2 = new Vector(elements2);
		
		assertEquals(-0.838780485, vector.cosine(vector2), 0.000001);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testCosineException() {
		
		double[] elements = {3.0, 4.0, 2};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {4.0, 0.0};
		IVector vectorHorisontal = new Vector(elements2);
		
		vector.cosine(vectorHorisontal);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testCosineException2() {
		
		double[] elements = {0, 0};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {4.0, 0.0};
		IVector vectorHorisontal = new Vector(elements2);
		
		vector.cosine(vectorHorisontal);
	}
	
	@Test
	public void testScalarProduct() {
		
		double[] elements = {3.0, -4.0};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {12.0, 2.0};
		IVector vector2 = new Vector(elements2);
		
		assertEquals(28.0, vector.scalarProduct(vector2), 0.000001);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testScalarProductException() {
		
		double[] elements = {3.0};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {12.0, 2.0};
		IVector vector2 = new Vector(elements2);
		
		vector.scalarProduct(vector2);
	}
	
	@Test
	public void testNVectorProduct() {
		
		double[] elements = {3.0, -4.0, 2.2};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {12.0, 2.0, -1.1};
		IVector vector2 = new Vector(elements2);
		
		IVector result = vector.nVectorProduct(vector2);
		
		double[] elements3 = {0, 29.7, 54};
		IVector vectorExpected = new Vector(elements3);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(vectorExpected.get(i), result.get(i), 0.000001);
		}
	}
	
	@Test(expected = IncompatibleOperandException.class)
	public void testNVectorProductException() {
		
		double[] elements = {3.0, -4.0};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {12.0, 2.0, -1.1};
		IVector vector2 = new Vector(elements2);
		
		IVector result = vector.nVectorProduct(vector2);
	}
	
	@Test
	public void testNHomogeneus() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		double[] elements2 = {1.5, -2.0};
		IVector vectorExpected = new Vector(elements2);
		
		IVector result = vector.nFromHomogeneus();
		
		for(int i = 0; i < 2; i++) {
			assertEquals(vectorExpected.get(i), result.get(i), 0.000001);
		}
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testNHomogeneusException() {
		
		double[] elements = {3.0, -4.0, 0.0};
		IVector vector = new Vector(elements);
		
		IVector result = vector.nFromHomogeneus();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testNHomogeneusException2() {
		
		double[] elements = {3.0};
		IVector vector = new Vector(elements);
		
		IVector result = vector.nFromHomogeneus();
	}
	
	@Test
	public void testToArray() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		for(int i = 0; i < 2; i++) {
			assertEquals(elements[i], vector.toArray()[i], 0.000001);
		}
	}
	
	@Test
	public void testToStringDefault() {
		
		double[] elements = {3.0, -4, 2.412424124};
		IVector vector = new Vector(elements);
		
		String string = vector.toString();
		assertEquals("3.000 -4.000 2.412", string);
	}
	
	@Test
	public void testCopyPart() {
		
		double[] elements = {3.0, -4.0, 2.0, 9.32};
		IVector vector = new Vector(elements);
		
		IVector result = vector.copyPart(2);
		
		for(int i = 0; i < 2; i++) {
			assertEquals(elements[i], vector.toArray()[i], 0.000001);
		}
		assertEquals(2, result.getDimension());
	}
	
	@Test
	public void testToRowMatrixLive() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toRowMatrix(true);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(matrix.get(0, i), vector.get(i), 0.000001);
		}
		
		vector.set(1, 500);
		
		for(int i = 0; i < 2; i++) {
			assertEquals(matrix.get(0, i), vector.get(i), 0.000001);
		}
		
	}
	
	@Test
	public void testToRowMatrixNotLive() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toRowMatrix(false);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(matrix.get(0, i), vector.get(i), 0.000001);
		}
		
		vector.scalarMultiply(2);
		
		for(int i = 0; i < 3; i++) {
			assertNotEquals(matrix.get(0, i), vector.get(i), 0.000001);
		}
	}
	
	@Test
	public void testToColMatrixLive() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toColumnMatrix(true);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(matrix.get(i, 0), vector.get(i), 0.000001);
		}
		
		vector.set(1, 500);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(matrix.get(i, 0), vector.get(i), 0.000001);
		}
		
	}
	
	@Test
	public void testToColMatrixNotLive() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toColumnMatrix(false);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(matrix.get(i, 0), vector.get(i), 0.000001);
		}
		
		vector.scalarMultiply(2);
		
		for(int i = 0; i < 3; i++) {
			assertNotEquals(matrix.get(i, 0), vector.get(i), 0.000001);
		}
	}
	
	@Test
	public void testLiveViewVectorColumnMatrix() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toColumnMatrix(true);
		
		assertEquals(1, matrix.getColsCount());
		assertEquals(3, matrix.getRowsCount());
		
		IMatrix copy = matrix.copy();
		
		for(int i = 0; i < 3; i++) {
			assertEquals(matrix.get(i, 0), copy.get(i, 0), 0.000001);
		}
		matrix.add(copy);
		
		for(int i = 0; i < 3; i++) {
			assertNotEquals(matrix.get(i, 0), copy.get(i, 0), 0.000001);
		}
		
		IMatrix matrixNewInstance = matrix.newInstance(3, 3);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(0, matrixNewInstance.get(i, 0), 0.000001);
		}
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testLiveViewVectorColumnMatrixException() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toColumnMatrix(true);
		
		matrix.get(2, 2);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testLiveViewVectorColumnMatrixException2() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toColumnMatrix(true);
		
		matrix.set(2, 2, 2);
	}
	
	@Test
	public void testLiveViewVectorRowMatrix() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toRowMatrix(true);
		
		assertEquals(1, matrix.getRowsCount());
		assertEquals(3, matrix.getColsCount());
		
		IMatrix copy = matrix.copy();
		
		for(int i = 0; i < 3; i++) {
			assertEquals(matrix.get(0, i), copy.get(0, i), 0.000001);
		}
		matrix.add(copy);
		
		for(int i = 0; i < 3; i++) {
			assertNotEquals(matrix.get(0, i), copy.get(0, i), 0.000001);
		}
		
		IMatrix matrixNewInstance = matrix.newInstance(3, 3);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(0, matrixNewInstance.get(0, i), 0.000001);
		}
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testLiveViewVectorRowMatrixException() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toRowMatrix(true);
		
		matrix.get(2, 2);
	}
	
	@Test(expected=IncompatibleOperandException.class)
	public void testLiveViewVectorRowMatrixException2() {
		
		double[] elements = {3.0, -4.0, 2.0};
		IVector vector = new Vector(elements);
		
		IMatrix matrix = vector.toRowMatrix(true);
		
		matrix.set(2, 2, 2);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
