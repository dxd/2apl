package tuplespace;

import java.util.Date;
import net.jini.core.entry.Entry;

public class Position implements Entry {
	
	public String agent = null;
	public Integer id = null;
	//public Long longitude;
	//public Long latitude;
	public Cell cell = null;
	public Date time = null;
	public Integer clock;
	
	public Position() {

	}
	
	public Position(Integer id, String agent, Cell cell, int clock) {
		this.id = id;
		this.agent = agent;
		this.cell = cell;
		this.clock = clock;
		this.time = new Date();
	}

	public Position(String agent) {
		this.agent = agent;
	}

	public Position(int clock) {
		this.clock = clock;
	}

	@Override
	public String toString() {
		return "Position [agent=" + agent + ", id=" + id + ", cell=" + cell
				+ ", time=" + time + ", clock=" + clock + "]";
	}
		
}

