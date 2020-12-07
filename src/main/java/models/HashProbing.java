package models;

import exceptions.KeyDoesNotExistException;

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
 * Remarques : Interface which provide methods for the client
 */
public interface HashProbing {
	
	void put(char key, int value);
	
	int get(char key) throws KeyDoesNotExistException;
	
	void remove(char key) throws KeyDoesNotExistException;

}
