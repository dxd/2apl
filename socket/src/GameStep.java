import java.util.TimerTask;


public class GameStep extends TimerTask
  {
		
		private Synchronization synchro;
		
		private int clock = 0;
		
		public int getClock() {
			return clock;
		}


		public GameStep (Synchronization request)
		{
			this.synchro = request;
		}
  
	    public void run()
	    {
	    	clock++;

			//synchro.postJoin(ruby);
			//synchro.postLocation(ruby);
	    	synchro.run(clock);
			//synchro.getStatus(ruby);
			//jspace.read();
			System.out.println("Clock: " + clock);
	    }
  }