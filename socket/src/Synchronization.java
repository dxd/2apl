import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.Buffer;
//import org.json.JSONTokener;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonToken;
import com.javadocmd.simplelatlng.LatLng;

import dataJSon.*;
import dataTS.Update;



public class Synchronization {

	private static String server = "http://albinoni.cs.nott.ac.uk:49992";
	private static int gameId = 3;
	
	private JSpace jspace;

	private int clock;

	private Status status;

	private Update update;
    
	public Synchronization(JSpace jspace) {
		this.jspace = jspace;
	}
    	//post
	public void postJoin() {
    	try {
    		
    		URL ruby = new URL(server + "/game/" + gameId+ "/join");
    		
    		String data = "";
    		//dataJSon += 	URLEncoder.encode("layer_id", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
			data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode("robot1", "UTF-8");
			data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode("R1", "UTF-8");
			data += "&" + URLEncoder.encode("team", "UTF-8") + "=" + URLEncoder.encode("truck", "UTF-8"); 
			
    	    URLConnection conn = ruby.openConnection();
    	    System.out.println(conn.toString());
    	    System.out.println(data);
    	    conn.setDoOutput(true);
    	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    	    wr.write(data);
    	    wr.flush();

    	    // Get the response
    	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	    StringBuilder builder = new StringBuilder();

    	    for (String line = null; (line = reader.readLine()) != null;) {
        	    System.out.println(line);
    	        builder.append(line).append("\n");
    	    }
    	    
    	    Object obj=JSONValue.parse(builder.toString());
    	    JSONArray finalResult=(JSONArray)obj;

    	    	System.out.println(finalResult.toString());

    	    wr.close();
    	    reader.close();
    	} catch (Exception e) {
    	
        }
	}
	
	public void postLocation(int id, LatLng loc) {
    	try {
    		
    		URL ruby = new URL(server + "/game/" + gameId+ "/postLocation");
    		
    		String data = URLEncoder.encode("id", "UTF-8") + "=" + id;
			data += "&" + URLEncoder.encode("latitude", "UTF-8") + "=" + loc.getLatitude();
			data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + loc.getLongitude();
			
    	    URLConnection conn = ruby.openConnection();
    	    System.out.println(conn.toString());
    	    System.out.println(data);
    	    conn.setDoOutput(true);
    	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    	    wr.write(data);
    	    wr.flush();

    	    // Get the response
    	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	    StringBuilder builder = new StringBuilder();
    	    
    	    for (String line = null; (line = reader.readLine()) != null;) {
        	    System.out.println(line);
    	        builder.append(line).append("\n");
    	    }
    	    
    	    Object obj=JSONValue.parse(builder.toString());
    	    JSONArray finalResult=(JSONArray)obj;

    	    System.out.println(finalResult.toString());

    	    wr.close();
    	    reader.close();
    	} catch (Exception e) {
    	
        }
	}
    	
	public void getStatus() {
    	//get
    	try {

    		URL ruby = new URL(server + "/game/" + gameId+ "/status.json"); 
    		
    		Reader reader = new InputStreamReader(ruby.openStream());
    		//BufferedReader reader = new BufferedReader(new FileReader("/Users/dxd/git/Git/socket/src/status.json"));
    		
    		    Gson gson = new GsonBuilder().create();
    		    status = gson.fromJson(reader, Status.class);
    		    System.out.println(status.toString());
    		    reader.close();
    		
    	} catch (MalformedURLException e) {
    	} catch (IOException e) {
    	}
 
	}
	public void run(int clock) {
		
		pull();
		update = new Update(status);
		jspace.readAll(update, clock-1);
		
		push();
		jspace.writeAll(clock, status);
		
	}
	private void push() {
		postLocations();
		getReadings();
		
	}
	private void getReadings() {
		
		
	}
	private void postLocations() {
		for (dataTS.Location loc : update.getLocations())
		{
			LatLng latlng = Game.gridToLocation(loc.getCell());
			postLocation(loc.getId(), latlng);
		}
		
	}
	private void pull() {
		getStatus();
	}
}

