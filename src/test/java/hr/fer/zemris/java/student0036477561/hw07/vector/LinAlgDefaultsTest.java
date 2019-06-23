package hr.fer.zemris.java.student0036477561.hw07.vector;

import static org.junit.Assert.*;
import hr.fer.zemris.java.student0036477561.hw07.linearna.LinAlgDefaults;

import org.junit.Test;

public class LinAlgDefaultsTest {

	@Test
	public void test() {
		
		assertTrue(LinAlgDefaults.checkIfInside(4, 5));
		assertTrue(LinAlgDefaults.checkIfInside(0, 3));
		assertTrue(LinAlgDefaults.checkIfInside(0, 0));
		
		assertFalse(LinAlgDefaults.checkIfInside(-1, 5));
		assertFalse(LinAlgDefaults.checkIfInside(1, 0));
		assertFalse(LinAlgDefaults.checkIfInside(0, -1));
	}
}
