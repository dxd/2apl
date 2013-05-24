package tuplespace;

import java.util.Date;

import oopl.DistributedOOPL;
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

	public int[] toArray(DistributedOOPL oopl) {
		int[] r = new int[12];
		JL.addPredicate(r,0,oopl.prolog.strStorage.getInt("points"),4, oopl); // points/2
		
		JL.addPredicate(r,3,JL.makeStringKnown(this.agent, oopl),0, oopl); // the name
		JL.addNumber(r,6, this.clock, oopl);
		JL.addNumber(r, 9, this.value, oopl);

		return r;
	}
	
	@Override
	public String toString() {
		return "Points [agent=" + agent + ", time=" + time + ", clock=" + clock
				+ ", value=" + value + ", id=" + id + "]";
	}
}
