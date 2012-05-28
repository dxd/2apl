package tuplespace;

import java.sql.Timestamp;

import net.jini.core.entry.Entry;

public class Points implements Entry {
	
	public String agent;
	public Timestamp time;
	
	public Points() {

	}
	
	public Points(String agent, Timestamp time) {

		this.agent = agent;
		this.time = time;

	}
}
