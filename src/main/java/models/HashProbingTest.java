package models;

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
 * Remarques : Interface which provide technical methods
 */
public interface HashProbingTest {
	
	char[] keys();
	
	int[] values();
	
	double load();
	
	int hash(char key);

}
