package models;

import java.util.Arrays;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.KeyDoesNotExistException;

class HashLinearProbingTest {
	
	private HashLinearProbing map;
	
    private final Character[] R = {'B', 'O', 'E', 'P', 'V', 'L', 'X', 'N', 'K', 'M'};

    @BeforeEach
    void setUp() {
    	map = new HashLinearProbing(Constant.M);
    }
    
    void init() {
    	int i = 0;
    	for (char r : R) {
    		map.put(r,  i++);
    	}
    }
    
    @Test
    void hashTest() {
    	for (char r : R) {
    		System.out.println("idx = " +map.hash(r));
    	}
    }
    
	@Test
	void putTest() {
		HashLinearProbing map = new HashLinearProbing(Constant.M);
		int i = 0;
		for (char r : R) {
			map.put(r, i++);
		}
		System.out.println(map.keys());
	}
	
	@Test
	void getTest() {
		init();
		for (char r : R) {
			Assert.assertTrue(map.get(r) != -1);
		}
	}

	@Test
	void removeTest() throws KeyDoesNotExistException {
		init();
		System.out.println(map.keys());
		System.out.println(Arrays.toString(map.values()));
		for (char r : R) {
			map.remove(r);
			Assert.assertTrue(map.get(r) == -1);
		}
		System.out.println(map.keys());
		System.out.println(Arrays.toString(map.values()));
	}
}
