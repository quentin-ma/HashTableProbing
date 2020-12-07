package models;

import exceptions.KeyDoesNotExistException;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HashQuadraticProbingTest {

	private HashQuadraticProbing map;
	private final Character[] R = {'B', 'O', 'E', 'P', 'V', 'L', 'X', 'N', 'K', 'M'};

	@BeforeEach
	void setUp() {
		map = new HashQuadraticProbing(Constant.M);
	}

	void init() {
		int i = 0;
		for (char r : R) {
			map.put(r,  i++);
		}
	}

	@Test
	void putTest() {
		int i = 0;
		for (char r : R) {
			map.put(r, i);
			Assert.assertEquals(map.get(r), i);
			i++;
		}
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
	}

	@Test
	void getTest() {
		init();
		for (char r : R) {
			Assert.assertTrue(map.get(r) != -1);
		}
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
	}

	@Test
	void removeTest() throws KeyDoesNotExistException {
		init();
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
		for (char r : R) {
			map.remove(r);
		}
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
		map.get('E');
	}

}
