package tuplespace;

import java.util.Date;

import oopl.DistributedOOPL;
import net.jini.core.entry.Entry;

public class Obligation implements TimeEntry {
	
	public String agent;
	public String obligation;
	public String sanction;
	public Integer deadline;
	public Date time;
	public Integer clock;
	
	
	public Obligation() {
		
	}


	public Obligation(String agent, String obligation, String sanction,
			Integer deadline, Integer clock) {
		
		this.agent = agent;
		this.obligation = obligation;
		this.sanction = sanction;
		this.deadline = deadline;
		this.clock = clock;
		this.time = new Date();
	}


	public Obligation(String agent) {
		this.agent = agent;
	}


	@Override
	public String toString() {
		return "Obligation [agent=" + agent + ", obligation=" + obligation
				+ ", sanction=" + sanction + ", deadline=" + deadline
				+ ", time=" + time + ", clock=" + clock + "]";
	}


	@Override
	public int[] toArray(DistributedOOPL oopl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTime() {
		this.time = new Date();
		
	}
	@Override
	public Date getTime() {
		return this.time;
	}


}
