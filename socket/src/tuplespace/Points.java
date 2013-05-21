package tuplespace;

import java.util.Date;

import net.jini.core.entry.Entry;

public class Points implements TimeEntry {
	
	public String agent;
	public Date time;
	public Integer clock;
	public Integer value;
	public Integer id;
	
	public Points() {

	}
	
	public Points(String agent, int clock, int value) {

		this.agent = agent;
		this.clock = clock;
		this.time = new Date();
		this.value = value;

	}
	public Points(String agent, int clock) {

		this.agent = agent;
		this.clock = clock;
		this.time = new Date();

	}

	public Points(int clock) {
		this.clock = clock;
	}

	public Points(String agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "Points [agent=" + agent + ", time=" + time + ", clock=" + clock
				+ ", value=" + value + ", id=" + id + "]";
	}
}
