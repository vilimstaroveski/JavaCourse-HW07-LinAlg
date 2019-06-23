package hr.fer.zemris.java.student0036477561.hw07.vector;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import hr.fer.zemris.java.student0036477551.hw07.vector.Vector;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.UnmodifiableObjectException;

import org.junit.Test;

public class VectorTest {

	@Test
	public void testConstructor() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = new Vector(false, false, elements);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(elements[i], vector.get(i), 0.000001);
		}
	}
	
	@Test
	public void testConstructorUseGiven() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = new Vector(false, true, elements);
		elements[0] = 1.5;
		elements[1] = 222;
		
		for(int i = 0; i < 3; i++) {
			assertEquals(elements[i], vector.get(i), 0.000001);
		}
	}
	
	@Test(expected=UnmodifiableObjectException.class)
	public void testConstructorReadOnly() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = new Vector(true, false, elements);
		
		for(int i = 0; i < 3; i++) {
			assertEquals(elements[i], vector.get(i), 0.000001);
		}
		
		vector.set(2, 2.2);
	}
	
	@Test
	public void testGetDimension() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = new Vector(false, false, elements);
		
		assertEquals(3, vector.getDimension());
	}
	
	@Test
	public void testCopy() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = new Vector(false, false, elements);
		
		IVector copy = vector.copy();
		for(int i = 0; i < 3; i++) {
			assertEquals(elements[i], copy.get(i), 0.000001);
		}
	}
	
	@Test
	public void testNewInstance() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = new Vector(false, false, elements);
		
		IVector vectorNewInstance = vector.newInstance(3);
		
		for(int i = 0; i < 3; i++) {
			assertNotEquals(elements[i], vectorNewInstance.get(i), 0.000001);
			assertEquals(0, vectorNewInstance.get(i), 0.0000001);
		}
	}
	
	@Test
	public void testParseSimple() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = Vector.parseSimple("1.2 4.43     -12.2");
		
		for(int i = 0; i < 3; i++) {
			assertEquals(elements[i], vector.get(i), 0.000001);
		}
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testParseSimpleException() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = Vector.parseSimple("1.2 abce     -12.2");
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testParseSimpleException2() {
		
		double[] elements = {1.2, 4.43, -12.2};
		IVector vector = Vector.parseSimple("1.0.2      -12.2");
	}
	
	
	
	
	
	
	
	
	
	
	
}
