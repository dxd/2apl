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

	public Request(Integer clock) {
		this.clock = clock;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", cell=" + cell + ", time=" + time
				+ ", clock=" + clock + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getClock() {
		return clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}
}
