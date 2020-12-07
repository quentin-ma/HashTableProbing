package models;

import exceptions.KeyDoesNotExistException;
import utils.Utils;

public class HashDoubleProbing implements HashProbing, HashProbingTest {

	private int s;
	private int n;
	private int m;
	private char[] keys;
	private int[] values;

	public HashDoubleProbing() {
		this(Constant.M);
	}

	public HashDoubleProbing(int capacity) {
		m = capacity;
		n = 0;
		s = Utils.getSmallestPrimeNumber(m);
		keys = new char[m];
		values = new int[m];
	}

	private void resize(int capacity) {
		int tempSize = Utils.getCeilPrimerNumber(capacity);
		HashDoubleProbing temp = new HashDoubleProbing(tempSize);
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
		int hash = hash(key);
		int hash2 = hashDouble(key);
		if (n > (m / 2)) {
			resize(2 * m);
		}
		while ((keys[hash] != 0) && (keys[hash] != Constant.DELETED)) {
			if (keys[hash] == key) {
				values[hash] = value;
				return;
			}
			hash = (hash + (i * hash2)) % m;
			i++;
		}
		keys[hash] = key;
		values[hash] = value;
		n++;
	}

	@Override
	public int get(char key) {
		if (key == 0) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		int valueToReturn = -1;
		int hash = hash(key);
		int hash2 = hashDouble(key);
		int i = 0;
		while ((keys[hash] != key) && (keys[hash] != Constant.DELETED)) {
			hash = (hash + (i * hash2)) % m;
			i++;
		}
		if (keys[hash] == key) {
			return values[hash];
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

	private int hashDouble(char key) {
		return s - (key % s);
	}

}
