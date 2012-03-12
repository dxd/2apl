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

public class SimpleTuple {
  static final int NUMLOCALPARAMETERS = 1;

	
  // This constructor receives the parameters we passed in server.loadAgent()
  public SimpleTuple () {

  }
	
  public static void main (String[] args) {
		
	    // must be at least one argument 
	    if (args.length == 0) {
	      System.out.println("Usage: java SimpleLime one_word_message [lime args]\n");
	      System.exit(1);
	    }

	    // Pass Lime arguments (if any) through the Launcher and launch the
	    // LimeServer. In this case, NUMLOCALPARAMETERS is the index of the 
	    // first Lime parameter (as opposed to the index of the application
	    // parameter)
	    new lime.util.Launcher().launch(args,NUMLOCALPARAMETERS);

			
	    // load a SimpleLime, passing the first command line argument as the
	    // only paramter
	    try{
	      int i = LimeServer.getServer().getLoadPort();
	      System.out.println(i);
	      LimeServer.getServer().loadAgent("ts.InteractiveMonitorAgent",
	                                       new String[]{});
	    } catch (LimeException le) { 
	      System.out.println("Trouble Loading the agent");
	      le.printStackTrace(); 
	    }
	  }

}
