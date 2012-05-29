package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Reading implements Entry {
	
	public String agent;
	//public Long longitude;
	//public Long latitude;
	public Cell cell;
	public Date time;
	public byte value;
	public int clock;
	
	public Reading() {

	}
	
	public Reading(String agent, Cell cell, int clock, Date time, byte value) {
		this.agent = agent;
		this.cell = cell;
		this.clock = clock;
		this.time = time;
		this.value = value;
	}
		
}
