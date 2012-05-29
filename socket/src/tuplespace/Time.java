package tuplespace;

import java.util.Date;

import net.jini.core.entry.Entry;

public class Time implements Entry {

	public Time(int clock) {
		this.clock = clock;
		this.time = new Date();
	}
	public int clock;
	public Date time;
}
