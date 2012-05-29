package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;
import java.util.Date;

import net.jini.core.entry.Entry;

public class Reading implements Entry {
	

	public Integer id;
	//public Long longitude;
	//public Long latitude;
	public Cell cell;
	public Date time;
	public Float value;
	public Integer clock;
	
	public Reading() {

	}
	
	public Reading(Integer id, Cell cell, int clock, float value) {
		this.id = id;
		this.cell = cell;
		this.clock = clock;
		this.time = new Date();
		this.value = value;
	}

	@Override
	public String toString() {
		return "Reading [id=" + id + ", cell=" + cell + ", time=" + time
				+ ", value=" + value + ", clock=" + clock + "]";
	}
		
}
