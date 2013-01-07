package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Investigate implements TimeEntry {
	
	public Integer id;
	public String agent;
	public Cell cell;
	public Date time;
	public Float value;
	public Integer clock;
	
	public Investigate() {

	}
	
	public Investigate(Integer id, String agent, Cell cell, int clock, float value) {
		this.id = id;
		this.agent = agent;
		this.cell = cell;
		this.clock = clock;
		this.time = new Date();
		this.value = value;
	}

	@Override
	public String toString() {
		return "Reading [id=" + id + ", agent=" + agent + ", cell=" + cell
				+ ", time=" + time + ", value=" + value + ", clock=" + clock
				+ "]";
	}
		
}