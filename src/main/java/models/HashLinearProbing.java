package models;

import exceptions.KeyDoesNotExistException;

public class HashLinearProbing implements HashProbing, HashProbingTest {

	private int n;			// number of key-value pairs in the symbol table
	private final int m;    // size of linear probing table
	private final char[] keys;    // the keys
	private int[] values;   // the values

	public HashLinearProbing() {
		this(Constant.M); // default size
	}
	
	public HashLinearProbing(int capacity) {
		m = capacity;
		n = 0;
		this.keys = new char[capacity];
		this.values = new int[capacity];
	}
	
	@Override
	public void put(char key, int value) {
		if (key == 0) {
			throw new IllegalArgumentException("first argument  to put() is null");
		}
		int index = hash(key);
		while (keys[index] != 0) {
			if (keys[index] == key) {
				values[index] = value;
			}
			index = (index + 1) % m;
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
		while (keys[index] != 0) {
			if (keys[index] == key) {
				return values[index];
			}
			index = (index + 1) % m;
		}
		return valueToReturn;
	}
	
	@Override
	public void remove(char key) throws KeyDoesNotExistException {
		if (get(key) == -1) {
			throw new KeyDoesNotExistException("Provided key is missing in hash table");
		}
		if (key == 0) {
			throw new IllegalArgumentException("argument to remove() is null");
		}
        int index = hash(key);
        while ((keys[index] != key) && (keys[index] != 0)) {
            index = (index + 1) % Constant.M;
        }
        keys[index] = 0;
        values[index] = 0;
        index = (index + 1) % Constant.M;
        char savedKey;
        int savedValue;
        while (keys[index] != 0) {
            savedKey = keys[index];
            savedValue = values[index];
            keys[index] = 0;
            values[index] = 0;
            n--;
            put(savedKey, savedValue);
            index = (index + 1) % Constant.M;
        }
        n--;
	}

	@Override
	public char[] keys() {
		return this.keys;
	}

	@Override
	public int[] values() {
		return this.values;
	}

	@Override
	public double load() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hash(char key) {
		return key % Constant.M;
	}

}
