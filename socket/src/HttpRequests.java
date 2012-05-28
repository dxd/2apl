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

import JSon.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonToken;



public class HttpRequests {

    
	public HttpRequests() {
		
	}
    	//post
	public void postJoin(String s) {
    	try {
    		
    		URL ruby = new URL(s + "/game/3/join");
    		
    		String data = "";
    		//data += 	URLEncoder.encode("layer_id", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
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
	
	public void postLocation(String s) {
    	try {
    		
    		URL ruby = new URL(s + "/game/3/postLocation");
    		
    		String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8");
			data += "&" + URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode("52.9546228", "UTF-8");
			data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode("-1.1867573", "UTF-8");
			
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
    	
	public void getStatus(String s) {
    	//get
    	try {

    		URL ruby = new URL(s + "/game/3/status.json"); 
    		
    		Reader reader = new InputStreamReader(ruby.openStream());
    		//BufferedReader reader = new BufferedReader(new FileReader("/Users/dxd/git/Git/socket/src/status.json"));
    		
    		    Gson gson = new GsonBuilder().create();
    		    Status status = gson.fromJson(reader, Status.class);
    		    System.out.println(status.getRequests().toString());
    		    reader.close();
    		
    	} catch (MalformedURLException e) {
    	} catch (IOException e) {
    	}
 
	}
}

