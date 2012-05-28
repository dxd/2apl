
public class Client {
	
	private static String ruby = "http://albinoni.cs.nott.ac.uk:49992";
	private static HttpRequests request;
	private static JSpace jspace;
	
	public static void main(String[] args) {
		
		request = new HttpRequests();
		jspace = new JSpace();
		
		if (jspace.error())
			return;
		
		//request.postJoin(ruby);
		//request.postLocation(ruby);
		request.getStatus(ruby);
		
		while (true)
        {
            jspace.read();
            
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	    
	   
		
	}

}
