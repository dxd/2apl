package tuplespace;

import java.awt.Point;
import java.sql.Timestamp;
import java.util.Date;

import oopl.DistributedOOPL;
import net.jini.core.entry.Entry;

public class Reading implements TimeEntry {

	public Integer id;
	public String agent;
	public Cell cell;
	public Date time;
	public Float value;
	public Integer clock;
	
	public Reading() {

	}
	
	public Reading(Integer id, String agent, Cell cell, int clock, float value) {
		this.id = id;
		this.agent = agent;
		this.cell = cell;
		this.clock = clock;
		this.time = new Date();
		this.value = value;
	}
	
	public Reading(String agent, Cell cell, float value) {
		this.agent = agent;
		this.cell = cell;
		this.value = value;
	}

	public Reading(String agent) {
		this.agent = agent;
	}

	public Reading(Object[] params) {
		this.agent = params[2].toString();
	}
	
	public int[] toArray(DistributedOOPL oopl) {
		int[] r = new int[21];
		JL.addPredicate(r,0,oopl.prolog.strStorage.getInt("reading"),4, oopl); // cargo/2

		
		JL.addPredicate(r, 3, oopl.prolog.strStorage.getInt("cell"), 2, oopl);
		JL.addNumber(r, 6, this.cell.x, oopl);
		JL.addNumber(r, 9, this.cell.y, oopl);
		JL.addNumber(r, 12, this.value.intValue(), oopl);
		JL.addPredicate(r,15, JL.makeStringKnown(this.agent, oopl),0, oopl);
		JL.addNumber(r,18,this.clock, oopl);
		//addPredicate(r,3,makeStringKnown(t.agent==null?"null":t.agent),0); // the name
		//for (int i = 0;  i<r.length; i++){
		//	System.out.println("to array: " + oopl.prolog.strStorage.getString(r[i]));
			
		//}
		
		//addNumber(r, c,t.i);
		return r;
	}
	
	@Override
	public String toString() {
		return "Reading [id=" + id + ", agent=" + agent + ", cell=" + cell
				+ ", time=" + time + ", value=" + value + ", clock=" + clock
				+ "]";
	}
	@Override
	public void setTime() {
		this.time = new Date();
		
	}
		
}
