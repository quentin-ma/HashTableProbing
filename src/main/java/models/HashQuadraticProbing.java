package models;

import exceptions.KeyDoesNotExistException;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class HashQuadraticProbing implements HashProbing, HashProbingTest {

	private int n;			// number of key-value pairs in the symbol table
	private int m;	// size of linear probing table
	private char[] keys;	// the keys
	private int[] values;	// the values

	public HashQuadraticProbing() {
		this(Constant.M);
	}
	
	public HashQuadraticProbing(int capacity) {
		m = capacity;
		n = 0;
		keys = new char[m];
		values = new int[m];
	}

	private void resize(int capacity) {
		int tempSize = Utils.getCeilPrimerNumber(capacity);
		HashQuadraticProbing temp = new HashQuadraticProbing(tempSize);
		int i = 0;
		while (i < m) {
			if (keys[i] != 0) {
				temp.put(keys[i], values[i]);
			}
			i++;
		}
		keys = temp.keys;
		values = temp.values;
		m = temp.m;
	}

	@Override
	public void put(char key, int value) {
		if (key == 0) {
			throw new IllegalArgumentException("first argument  to put() is null");
		}
		int i = 0;
		int index = hash(key);
		if (n > (m / 2)) {
			resize(2 * m);
		}
		while ((keys[index] != 0) && (keys[index] != Constant.DELETED)) {
			if (keys[index] == key) {
				values[index] = value;
				return;
			}
			i++;
			index = (index + (2 * i) - 1) % m;
		}
		keys[index] = key;
		values[index] = value;
		n++;
	}

	@Override
	public int get(char key) {
		if (key == 0) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		int valueToReturn = -1;
		int index = hash(key);
		int i = 0;
		while ((keys[index] != key) && (keys[index] != Constant.DELETED)) {
			index = (index + (i * i)) % m;
			i++;
		}
		if (keys[index] == key) {
			return values[index];
		}
		return valueToReturn;
	}

	@Override
	public void remove(char key) throws KeyDoesNotExistException {
		if (get(key) == -1) {
			throw new KeyDoesNotExistException("Provided key is missing");
		}
		if (key == 0) {
			throw new IllegalArgumentException("argument to remove() is null");
		}
		int index = hash(key);
		int i = 0;
		while ((keys[index] != key) && (keys[index] != 0)) {
			index = (index + (i * i)) % m;
			i++;
		}
		keys[index] = '*';
		values[index] = 0;
		n--;
	}

	@Override
	public char[] keys() {
		return keys;
	}

	@Override
	public int[] values() {
		return values;
	}

	@Override
	public double load() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hash(char key) {
		return key % m;
	}

}
