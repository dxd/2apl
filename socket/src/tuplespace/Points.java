package tuplespace;

import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Points implements Entry {
	
	public String agent;
	public Date time;
	public int clock;
	
	public Points() {

	}
	
	public Points(String agent, int clock, Date time) {

		this.agent = agent;
		this.clock = clock;
		this.time = time;

	}
}
