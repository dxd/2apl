package tuplespace;

import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Request implements Entry {
	
	public Integer id;
	public Cell cell;
	public Date time;
	public Integer clock;
	
	public Request() {

	}
	
	public Request(Integer id, Cell cell, int clock) {

		this.id = id;
		this.cell = cell;
		this.clock = clock;
		this.time = new Date();

	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", cell=" + cell + ", time=" + time
				+ ", clock=" + clock + "]";
	}
}
