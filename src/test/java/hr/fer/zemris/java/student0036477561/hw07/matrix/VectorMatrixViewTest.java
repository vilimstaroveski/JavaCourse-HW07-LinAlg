package hr.fer.zemris.java.student0036477561.hw07.matrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import hr.fer.zemris.java.student0036477551.hw07.matrix.Matrix;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;

import org.junit.Test;

public class VectorMatrixViewTest {

	@Test
	public void testToVectorLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(true);
		assertEquals(3, vector.getDimension());
		assertEquals(3, vector.get(1), 0.0000001);
		
		vector.set(1, 40);
		assertEquals(40, vector.get(1), 0.000001);
		assertEquals(40, matrix.get(0, 1), 0.000001);
		
		
		IVector copy = vector.copy();
		for(int i = 0; i < 3; i++) {
			assertEquals(vector.get(i), copy.get(i), 0.000001);
		}
		vector.set(0, 400);
		assertNotEquals(400, copy.get(0), 0.000001);
		
		IVector vecNewInstance = vector.newInstance(5);
		assertEquals(5, vecNewInstance.getDimension());
		assertEquals(0, vecNewInstance.get(2), 0.000001);
	}
	
	@Test
	public void testToVectorLiveColumn() {
		
		IMatrix matrix = Matrix.parseSimple("2 | 3 | 5");
		assertEquals(1, matrix.getColsCount());
		assertEquals(3, matrix.getRowsCount());
		IVector vector = matrix.toVector(true);
		assertEquals(3, vector.getDimension());
		assertEquals(3, vector.get(1), 0.0000001);
		
		vector.set(1, 40);
		assertEquals(40, vector.get(1), 0.000001);
		assertEquals(40, matrix.get(1, 0), 0.000001);
		
		IVector copy = vector.copy();
		for(int i = 0; i < 3; i++) {
			assertEquals(vector.get(i), copy.get(i), 0.000001);
		}
		vector.set(0, 400);
		assertNotEquals(400, copy.get(0), 0.000001);
	}
	
	@Test
	public void testToVectorNotLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(false);
		assertEquals(3, vector.getDimension());
		assertEquals(3, vector.get(1), 0.0000001);
		
		vector.set(1, 40);
		assertEquals(40, vector.get(1), 0.000001);
		assertNotEquals(40, matrix.get(0, 1), 0.000001);
		
		IVector copy = vector.copy();
		for(int i = 0; i < 3; i++) {
			assertEquals(vector.get(i), copy.get(i), 0.000001);
		}
		vector.set(0, 400);
		assertNotEquals(400, copy.get(0), 0.000001);
	}
	
	@Test
	public void testToVectorNotLiveColumn() {
		
		IMatrix matrix = Matrix.parseSimple("2 | 3 | 5");
		assertEquals(1, matrix.getColsCount());
		assertEquals(3, matrix.getRowsCount());
		IVector vector = matrix.toVector(false);
		assertEquals(3, vector.getDimension());
		assertEquals(3, vector.get(1), 0.0000001);
		
		vector.set(1, 40);
		assertEquals(40, vector.get(1), 0.000001);
		assertNotEquals(40, matrix.get(1, 0), 0.000001);
		
		IVector copy = vector.copy();
		for(int i = 0; i < 3; i++) {
			assertEquals(vector.get(i), copy.get(i), 0.000001);
		}
		vector.set(0, 400);
		assertNotEquals(400, copy.get(0), 0.000001);
	}
	
	@Test(expected= IncompatibleOperandException.class)
	public void testGetExceptionLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(true);
		vector.get(4);
	}
	
	@Test(expected= IncompatibleOperandException.class)
	public void testGetExceptionNotLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(false);
		vector.get(4);
	}
	
	@Test(expected= IncompatibleOperandException.class)
	public void testSetExceptionLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(true);
		vector.set(4, 2);
	}
	
	@Test(expected= IncompatibleOperandException.class)
	public void testSetExceptionNotLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(false);
		vector.set(4, 2);
	}
	
	@Test(expected= IncompatibleOperandException.class)
	public void testNewExceptionLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(true);
		vector.newInstance(0);
	}
	
	@Test(expected= IncompatibleOperandException.class)
	public void testNewExceptionNotLive() {
		
		IMatrix matrix = Matrix.parseSimple("2 3 5");
		IVector vector = matrix.toVector(false);
		vector.newInstance(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
