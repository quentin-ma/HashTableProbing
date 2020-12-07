package models;

import exceptions.KeyDoesNotExistException;

public interface HashProbing {
	
	public void put(char key, int value);
	
	public int get(char key);
	
	public void remove(char key) throws KeyDoesNotExistException;

}
