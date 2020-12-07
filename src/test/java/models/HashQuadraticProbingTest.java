package models;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.KeyDoesNotExistException;
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
	void test() {
		int i = 0;
		for (char r : R) {
			map.put(r, i++);
		}
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
	}

	@Test
	void getTest() throws KeyDoesNotExistException {
		init();
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
		map.remove('N');
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
		int x = map.get('O');
		System.out.println(x);
	}

	@Test
	void removeTest() throws KeyDoesNotExistException {
		init();
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
		map.remove('L');
		map.remove('K');
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));
		map.put('L', 7);
		System.out.println(Arrays.toString(map.keys()));
		System.out.println(Arrays.toString(map.values()));

	}

}