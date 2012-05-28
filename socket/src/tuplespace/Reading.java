package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;

import net.jini.core.entry.Entry;

public class Reading implements Entry {
	
	public String agent;
	//public Long longitude;
	//public Long latitude;
	public Cell cell;
	public Timestamp time;
	public byte value;
	
	public Reading() {

	}
	
	public Reading(String agent, Cell cell, Timestamp time, byte value) {
		this.agent = agent;
		this.cell = cell;
		this.time = time;
		this.value = value;
	}
		
}
