package models;

import exceptions.KeyDoesNotExistException;
import utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TP n°: TP6 V1
 *
 * Titre du TP : Hash Probing II
 *
 * Date : 07/12/2020
 *
 * Nom  : MA
 * Prenom : Quentin
 *
 * email : quentin.ma@etu-u.paris.fr
 *
 * Remarques :
 * This class implements all the methods for Quadratic Probing Hashing
 */
public class HashQuadraticProbing implements HashProbing, HashProbingTest {

	private int n;			// number of keys-values pairs in hash table
	private int m;			// size of hash table
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

	/**
	 * This method provide a full resize capacity of the
	 * Hash Table when load factor > 0.5
	 * @param capacity: size of the "old" hash table
	 */
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

	/**
	 * This method provide key-value pair insertion
	 * in hash table with quadratic probing hashing function
	 * @param key: the key
	 * @param value: the value
	 */
	@Override
	public void put(char key, int value) {
		if (key == Slot.EMPTY) {
			throw new IllegalArgumentException("first argument  to put() is null");
		}
		int step = 0;
		int index = hash(key);
		if (n > (m / 2)) {
			resize(2 * m);
		}
		while ((keys[index] != Slot.EMPTY) && (keys[index] != Slot.DELETED)) {
			if (keys[index] == key) {
				values[index] = value;
				return;
			}
			step++;
			index = (index + (2 * step) - 1) % m;
		}
		keys[index] = key;
		values[index] = value;
		n++;
	}

	/**
	 * This method find a specific value from provided key
	 * in hash table with quadratic probing hashing function
	 * @param key: the key
	 * @return: value of the key
	 */
	@Override
	public int get(char key) {
		if (key == Slot.EMPTY) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		int valueToReturn = -1;
		int hash = hash(key);
		int step = 0, cpt = 0;
		while (true) {
			if (keys[hash] == key) {
				return values[hash];
			} else if (keys[hash] - 1 == Slot.EMPTY) {
				return valueToReturn;
			} else if (cpt >= keys.length) {
				return valueToReturn;
			} else {
				cpt++;
				step++;
				hash = (hash + (step * step)) % m;
			}
		}
	}
//	@Override
//	public int get(char key) {
//		if (key == Slot.EMPTY) {
//			throw new IllegalArgumentException("argument to get() is null");
//		}
//		int valueToReturn = -1;
//		int index = hash(key);
//		int step = 0;
//		while ((keys[index] != key)) {
//			step++;
//			index = (index + (step * step)) % m;
//		}
//		if (keys[index] == key) {
//			return values[index];
//		}
//		return valueToReturn;
//	}

	/**
	 * This method remove a key-value pair from a specific key
	 * in hash table with quadratic probing hashing function
	 * @param key: the key
	 * @throws KeyDoesNotExistException: if key does not exist, this exception is throw
	 */
	@Override
	public void remove(char key) throws KeyDoesNotExistException {
		if (get(key) == -1) {
			throw new KeyDoesNotExistException("Provided key is missing");
		}
		if (key == Slot.EMPTY) {
			throw new IllegalArgumentException("argument to remove() is null");
		}
		int index = hash(key);
		int step = 0;
		while ((keys[index] != key)) {
			step++;
			index = (index + (step * step)) % m;
		}
		keys[index] = Slot.DELETED;
		values[index] = 0;
		n--;
	}

	/**
	 *
	 * @return: keys in hash table
	 */
	@Override
	public char[] keys() {
		return keys;
	}

	/**
	 *
	 * @return: values in hash table
	 */
	@Override
	public int[] values() {
		return values;
	}

	/**
	 * This function implement load factor α = n / k
	 * where n is the number of pairs and k a prime number
	 * @return: load factor
	 */
	@Override
	public double load() {
		return (double) n / m;
	}

	/**
	 * Hash function h(k) = k mod m
	 * @param key: the key
	 * @return: index of hashed key
	 */
	@Override
	public int hash(char key) {
		return key % m;
	}

	public void removeAll() {
		keys = new char[Constant.M];
		values = new int[Constant.M];
		n = 0;
		m = Constant.M;
	}

	@Override
	public String toString() {
		return "HashQuadraticProbing{" +
				"keys=" + Arrays.toString(keys) +
				", values=" + Arrays.toString(values) +
				'}';
	}
}
