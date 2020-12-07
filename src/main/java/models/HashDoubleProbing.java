package models;

import exceptions.KeyDoesNotExistException;
import utils.Utils;

import java.util.Arrays;

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
 * This class implements all the methods for Double Probing Hashing
 */
public class HashDoubleProbing implements HashProbing, HashProbingTest {

	private final int s;	// prime number for second hashing function
	private int n;			// number of keys-values pairs in hash table
	private int m;			// size of hash table (starting to 11)
	private char[] keys;	// the keys
	private int[] values;	// the values

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

	/**
	 * This method provide a full resize capacity of the
	 * Hash Table when load factor > 0.5
	 * @param capacity: size of the "old" hash table
	 */
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

	/**
	 * This method provide key-value pair insertion
	 * in hash table with double probing hashing function
	 * @param key: the key
	 * @param value: the value
	 */
	@Override
	public void put(char key, int value) {
		if (key == 0) {
			throw new IllegalArgumentException("first argument  to put() is null");
		}
		int hash = hash(key);
		int hash2 = hashDouble(key);
		if (load() > (double) (m / 2) * 0.1) {
			resize(2 * m);
		}
		int step = 0;
		while ((keys[hash] != Slot.EMPTY) && (keys[hash] != Slot.DELETED)) {
			if (keys[hash] == key) {
				values[hash] = value;
				return;
			}
			step++;
			hash = (hash + (step * hash2) - 1) % m;
		}
		keys[hash] = key;
		values[hash] = value;
		n++;
	}

	/**
	 * This method find a specific value from provided key
	 * in hash table with double probing hashing function
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
		int hash2 = hashDouble(key);
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
				hash = (hash + (step * hash2)) % m;
			}
		}
	}

	/**
	 * This method remove a key-value pair from a specific key
	 * in hash table with double probing hashing function
	 * @param key: the key
	 * @throws KeyDoesNotExistException: if key does not exist, this exception is throw
	 */
	@Override
	public void remove(char key) throws KeyDoesNotExistException {
		if (get(key) == -1) {
			throw new KeyDoesNotExistException("Provided key is missing");
		}
		if (key == 0) {
			throw new IllegalArgumentException("argument to remove() is null");
		}
		int hash = hash(key);
		int hash2 = hashDouble(key);
		int step = 0;
		while (keys[hash] != key) {
			step++;
			hash = (hash + (step * hash2)) % m;
		}
		keys[hash] = '*';
		values[hash] = 0;
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

	/**
	 * Second hash function to generate different
	 * probe sequences for different keys with
	 * h2(k) = s - (k mod s)
	 * @param key: the key
	 * @return: index of hashed key
	 */
	private int hashDouble(char key) {
		return s - (key % s);
	}

	public void removeAll() {
		keys = new char[Constant.M];
		values = new int[Constant.M];
		n = 0;
		m = Constant.M;
	}

	@Override
	public String toString() {
		return "HashDoubleProbing{" +
				"keys=" + Arrays.toString(keys) +
				", values=" + Arrays.toString(values) +
				'}';
	}
}
