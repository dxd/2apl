package tuplespace;

import java.sql.Timestamp;

import net.jini.core.entry.Entry;

public class Request implements Entry {
	
	public Cell cell;
	public Timestamp time;
	
	public Request() {

	}
	
	public Request(Cell cell, Timestamp time) {

		this.cell = cell;
		this.time = time;

	}
}
