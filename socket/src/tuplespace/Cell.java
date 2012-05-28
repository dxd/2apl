package tuplespace;

import net.jini.core.entry.Entry;

public class Cell implements Entry {
	
	
	public int x;
	public int y;
	
	public Cell(int i, int j) {
		x = i;
		y = j;
	}

}
