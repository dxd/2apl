package tuplespace;

import java.util.Date;

import com.javadocmd.simplelatlng.LatLng;

import net.jini.core.entry.Entry;

public class Cargo implements TimeEntry {

	public Integer id;
	public Cell cell;
	public Date time;
	public Integer clock;
	
	public Cargo() {

	}
	public Cargo(Integer clock) {
		this.clock = clock;
	}
	public Cargo(int id, Cell cell, int clock) {
		
		this.id = id;
		this.cell = cell;
		this.clock = clock;
		this.time = new Date();

	}
	public Cargo(Cell cell, Integer clock) {
		
		this.cell = cell;
		this.clock = clock;
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