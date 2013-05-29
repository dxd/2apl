package tuplespace;


import java.util.Date;

import oopl.DistributedOOPL;
import net.jini.core.entry.Entry;
//import apapl.data.Term;

public interface TimeEntry extends Entry{
	
	public Date time = null;
	public JiniLib JL = new JiniLib();
	public int[] toArray(DistributedOOPL oopl);
	public void setTime();
	public Date getTime();
	//public Term to2APLterm();

}
