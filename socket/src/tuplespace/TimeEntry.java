package tuplespace;


import java.util.Date;

import oopl.DistributedOOPL;
import net.jini.core.entry.Entry;

public interface TimeEntry extends Entry{
	
	public Date time = null;
	public JiniLib JL = new JiniLib();
	int[] toArray(DistributedOOPL oopl);
	void setTime();

}
