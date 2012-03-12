package ts;

import lime.*;
import lights.adapters.*;
import lights.interfaces.*;

/**
 * 
 * This is an extremely simple Lime application which demonstrates how to launch
 * Lime from within an application.  To run this application:
 * 
 * java SimpleLime one_word_message [lime args]
 * 
 * The one word message (a single word) will be put in a tuple and written to
 * the tuple space, the lime args are optional and can be any of the standard
 * Lime arguments (see lime.util.Launcher).  When the tuple is successfully
 * written, a message will be printed to standard out.
 * 
 * Sample output:
 * >java SimpleLime Hi -mcast off
 * Lime:Factory set to lights.adapters.builtin.TupleSpaceFactory
 * Lime:Lime server murphy:1973 activated
 * Lime:Agent SimpleLime loaded and started.
 * I wrote the tuple: <Hi>
 * Lime:Shutting down Lime server...
 * Lime:Done.
 * 
 */

public class SimpleLimeTest extends StationaryAgent {
  static final int NUMLOCALPARAMETERS = 1;
  String msg = null;
	
  // This constructor receives the parameters we passed in server.loadAgent()
  public SimpleLimeTest (String msg) {
    this.msg = msg;
  }
	


  public void run () { 
    LimeTupleSpace lts = null;
    ITuple myTuple = new Tuple().addActual(msg);
    // create the new tuple space (default name)
    try {
      lts = new LimeTupleSpace();
      lts.out(myTuple);
    } catch(LimeException le) {
      System.out.println("Trouble creating tuple space and writing to it");
      le.printStackTrace();
      System.exit(1);
    }
    System.out.println("I wrote the tuple: "+myTuple);
		
    // shut down Lime gracefully
    LimeServer.getServer().shutdown(true);
  }
}
