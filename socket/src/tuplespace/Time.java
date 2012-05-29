package tuplespace;

import java.util.Date;

import net.jini.core.entry.Entry;

public class Time implements Entry {

	public Integer clock;
	public Date time;
	
	public Time() {

	}
	public Time(int clock) {
		this.clock = clock;
		this.time = new Date();
	}
	
}
