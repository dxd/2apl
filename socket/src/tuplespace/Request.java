package tuplespace;

import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Request implements Entry {
	
	public Cell cell;
	public Date time;
	public int clock;
	
	public Request() {

	}
	
	public Request(Cell cell, int clock, Date time) {

		this.cell = cell;
		this.clock = clock;
		this.time = time;

	}
}
