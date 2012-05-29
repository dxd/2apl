package tuplespace;

import java.sql.Timestamp;

import net.jini.core.entry.Entry;

public class Points implements Entry {
	
	public String agent;
	public Timestamp time;
	public int clock;
	
	public Points() {

	}
	
	public Points(String agent, int clock, Timestamp time) {

		this.agent = agent;
		this.clock = clock;
		this.time = time;

	}
}
