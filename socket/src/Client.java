import java.util.Date;
import java.util.Timer;

public class Client {
	

	private static Date startTime;
	private static int gamePace = 1000;
	
	private static Timer timer;
	
	private static JSpace jspace;
	private static HttpRequests request;
	
	public static void main(String[] args) {
		
		jspace = new JSpace();
		
		if (jspace.error())
			return;
		
		request = new HttpRequests(jspace);
		initiate();

	}

	public static void initiate() {
		
		startTime = new Date();
		
	    timer = new Timer();
	    timer.schedule(new GameStep(request),0, gamePace);
	    
	  }
}
