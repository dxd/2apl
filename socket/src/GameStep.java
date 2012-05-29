import java.util.TimerTask;


public class GameStep extends TimerTask
  {
		private static String ruby = "http://albinoni.cs.nott.ac.uk:49992";
		private HttpRequests request;
		
		private int clock = 0;
		
		public GameStep (HttpRequests request)
		{
			this.request = request;
		}
  
	    public void run()
	    {
	    	clock++;
			
			
		    
			//request.postJoin(ruby);
			//request.postLocation(ruby);
			request.getStatus(ruby);
			//jspace.read();
			System.out.println(clock);
	    }
  }