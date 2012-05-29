import java.util.TimerTask;


public class GameStep extends TimerTask
  {
		private static String ruby = "http://albinoni.cs.nott.ac.uk:49992";
		private Synchronization synchro;
		
		private int clock = 0;
		
		public GameStep (Synchronization request)
		{
			this.synchro = request;
		}
  
	    public void run()
	    {
	    	clock++;

			//synchro.postJoin(ruby);
			//synchro.postLocation(ruby);
			synchro.getStatus(ruby);
			//jspace.read();
			System.out.println(clock);
	    }
  }