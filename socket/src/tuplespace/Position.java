package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;

import net.jini.core.entry.Entry;

public class Position implements Entry {
	
	public String agent = null;
	//public Long longitude;
	//public Long latitude;
	public Cell cell = null;
	public Timestamp time = null;
	
	public Position() {

	}
	
	public Position(String agent, Cell cell, Timestamp time) {
		this.agent = agent;
		this.cell = cell;
		this.time = time;
	}

	public Position(String agent2) {
		this.agent = agent;
	}
		
}

