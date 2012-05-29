package tuplespace;

import java.sql.Timestamp;

import net.jini.core.entry.Entry;

public class Request implements Entry {
	
	public Cell cell;
	public Timestamp time;
	public int clock;
	
	public Request() {

	}
	
	public Request(Cell cell, int clock, Timestamp time) {

		this.cell = cell;
		this.clock = clock;
		this.time = time;

	}
}
