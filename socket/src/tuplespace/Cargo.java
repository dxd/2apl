package tuplespace;

import java.util.Date;

import com.javadocmd.simplelatlng.LatLng;

import net.jini.core.entry.Entry;

public class Cargo implements Entry {
	
	public Cell cell;
	public Date time;
	public int clock;
	
	public Cargo() {

	}
	
	public Cargo(Cell cell, int clock, Date time) {

		this.cell = cell;
		this.clock = clock;
		this.time = time;

	}
}