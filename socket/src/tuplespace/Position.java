package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Position implements Entry {
	
	public String agent = null;
	//public Long longitude;
	//public Long latitude;
	public Cell cell = null;
	public Date time = null;
	public int clock;
	
	public Position() {

	}
	
	public Position(String agent, Cell cell, int clock, Date time) {
		this.agent = agent;
		this.cell = cell;
		this.clock = clock;
		this.time = time;
	}

	public Position(String agent2) {
		this.agent = agent;
	}

	public Position(int clock) {
		this.clock = clock;
	}
		
}

