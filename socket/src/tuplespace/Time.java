package tuplespace;

import java.util.Date;

import oopl.DistributedOOPL;
import net.jini.core.entry.Entry;

public class Time implements TimeEntry {

	public Integer clock;
	public Date time;
	
	public Time() {

	}
	public Time(int clock) {
		this.clock = clock;
		this.time = new Date();
	}
	@Override
	public String toString() {
		return "Time [clock=" + clock + ", time=" + time + "]";
	}
	@Override
	public int[] toArray(DistributedOOPL oopl) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
