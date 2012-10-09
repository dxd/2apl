package tuplespace;

import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Coin implements TimeEntry {
	
	public Coin(Cell cell, String agent, Integer clock) {
		
		this.cell = cell;
		this.agent = agent;
		this.clock = clock;
	}

	public Integer id;
	public Cell cell;
	public String agent;
	public Date time;
	public Integer clock;
	
	public Coin() {

	}
	
	public Coin(Integer id, Cell cell, String agent, int clock) {

		this.id = id;
		this.cell = cell;
		this.agent = agent;
		this.clock = clock;
		this.time = new Date();

	}

	public Coin(Integer clock) {
		this.clock = clock;
	}

	public Coin(String agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "Coin [id=" + id + ", cell=" + cell + ", agent=" + agent
				+ ", time=" + time + ", clock=" + clock + "]";
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
