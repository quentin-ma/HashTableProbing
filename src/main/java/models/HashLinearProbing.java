package models;

import exceptions.KeyDoesNotExistException;

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
 * Remarques : This class implements all the methods for Linear Probing Hashing
 */
public class HashLinearProbing implements HashProbing, HashProbingTest {

	private int n;					// number of keys-values pairs in hash table
	private int m;    		// size of hash table
	private char[] keys;      // the keys
	private int[] values;     // the values

	public HashLinearProbing() {
		this(Constant.M); // default size
	}
	
	public HashLinearProbing(int capacity) {
		m = capacity;
		n = 0;
		this.keys = new char[capacity];
		this.values = new int[capacity];
	}

	/**
	 * This method provide key-value pair insertion
	 * in hash table with linear probing hashing function
	 * @param key: the key
	 * @param value: the value
	 */
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

	/**
	 * This method find a specific value from provided key
	 * in hash table with linear probing hashing function
	 * @param key: the key
	 * @return: value of the key
	 */
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

	/**
	 * This method remove a key-value pair from a specific key
	 * in hash table with linear probing hashing function
	 * @param key: the key
	 * @throws KeyDoesNotExistException: if key does not exist, this exception is throw
	 */
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

	/**
	 *
	 * @return: keys in hash table
	 */
	@Override
	public char[] keys() {
		return this.keys;
	}

	/**
	 *
	 * @return: values in hash table
	 */
	@Override
	public int[] values() {
		return this.values;
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
		return key % Constant.M;
	}

	public void removeAll() {
		keys = new char[Constant.M];
		values = new int[Constant.M];
		n = 0;
		m = Constant.M;
	}

	@Override
	public String toString() {
		return "HashLinearProbing{" +
				"keys=" + Arrays.toString(keys) +
				", values=" + Arrays.toString(values) +
				'}';
	}
}
