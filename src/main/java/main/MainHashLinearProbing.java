package main;

import exceptions.KeyDoesNotExistException;
import models.Constant;
import models.HashLinearProbing;

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
 * Remarques : Main class for HashLinearProbing
 */
public class MainHashLinearProbing {
	
	public static void main(String[] args) throws KeyDoesNotExistException {
		HashLinearProbing map = new HashLinearProbing(Constant.M);
		map.put('B', 0);
		map.put('N', 7);
		map.put('X', 6);
		System.out.println("Hash table with 3 items: " + map);
		map.remove('N');
		System.out.println("Hash table without N key: " + map);
		System.out.println("V(X) = " + map.get('X'));
		map.removeAll();
		int i = 0;
		for (char r : Constant.R_HASH) {
			map.put(r, i++);
		}
		System.out.println("Hash table filled with pairs: " + map);
		for (char r : Constant.R_HASH) {
			map.remove(r);
		}
		System.out.println("Empty Hash table: " + map);
	}

}
