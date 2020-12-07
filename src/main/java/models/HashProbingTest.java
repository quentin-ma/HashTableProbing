package models;

public interface HashProbingTest {
	
	public char[] keys();
	
	public int[] values();
	
	public double load();
	
	public int hash(char key);

}
