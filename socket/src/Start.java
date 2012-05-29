import java.util.Date;
import java.util.Timer;

public class Start {
	

	private static Date startTime;
	private static int gamePace = 1000;
	
	private static Timer timer;
	
	private static JSpace jspace;
	private static Synchronization synchro;
	
	public static void main(String[] args) {
		
		jspace = new JSpace();
		
		if (jspace.error())
			return;
		
		synchro = new Synchronization(jspace);
		initiate();

	}

	public static void initiate() {
		
		startTime = new Date();
		
	    timer = new Timer();
	    timer.schedule(new GameStep(synchro),0, gamePace);
	    
	    Game.initiateGrid();
	    
	  }
}
