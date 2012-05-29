package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;

import com.javadocmd.simplelatlng.LatLng;

import net.jini.core.entry.Entry;

public class Cargo implements Entry {
	
	public Cell cell;
	public Timestamp time;
	public int clock;
	
	public Cargo() {

	}
	
	public Cargo(Cell cell, int clock, Timestamp time) {

		this.cell = cell;
		this.clock = clock;
		this.time = time;

	}
}