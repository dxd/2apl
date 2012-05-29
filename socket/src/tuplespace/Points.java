package tuplespace;

import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Points implements Entry {
	
	public Integer id;
	public String agent;
	public Date time;
	public Integer clock;
	
	public Points() {

	}
	
	public Points(String agent, int clock) {

		this.agent = agent;
		this.clock = clock;
		this.time = new Date();

	}
}
