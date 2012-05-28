package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;

import net.jini.core.entry.Entry;

public class Cargo implements Entry {
	
	public Cell cell;
	public Timestamp time;
	
	public Cargo() {

	}
	
	public Cargo(Location location, Timestamp time) {

		this.cell = cell;
		this.time = time;

	}
}