import java.util.TimerTask;

import com.javadocmd.simplelatlng.LatLng;


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
			synchro.postLocation(10, new LatLng(50,-1));
			synchro.getReading(6, new LatLng(50.005,-1.005));
	    	synchro.run(clock);
			//synchro.getStatus(ruby);
			//jspace.read();
			System.out.println("Clock: " + clock);
	    }
  }