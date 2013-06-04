package tuplespace;

import game.Synchronization;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.sun.tools.javac.util.Pair;

import net.jini.core.event.RemoteEvent;
import net.jini.core.event.RemoteEventListener;

public class NotificationHandler extends UnicastRemoteObject implements RemoteEventListener {

	//JavaSpace space;
	Synchronization synchro;
	HashMap<Pair<String,String>, Date> timestamps;

    public NotificationHandler(Synchronization s) throws RemoteException {
		synchro = s;
		timestamps = new HashMap<Pair<String,String>,Date>();
	}

	public void notify(RemoteEvent anEvent) {

        try {
        	String[] type = (String[]) anEvent.getRegistrationObject().get();
        	Pair pair = new Pair(type[0],type[1]);
        	timestamps.put(pair, new Date());
        	ArrayList<TimeEntry> r = new ArrayList<TimeEntry>();
           // System.out.println("Got event: " + anEvent.getSource() + ", " +
             //                  anEvent.getID() + ", " +
               //                anEvent.getSequenceNumber() + ", " + 
                 //              anEvent.getRegistrationObject().get());
            
        	if (type[0].equals("position")) {
            	System.out.println("position notification " + type[1]);
            	//synchro.update.UpdatePosition((Position) synchro.jspace.readUpdate(new Position(type[1])));
            	//System.out.println("position posting");
            	r = synchro.jspace.readUpdate(new Position(type[1]), timestamps.get(pair));
            	synchro.postLocations(r);
            }
        	else if (type[0].equals("makeReading")) {
            	System.out.println("reading request notification " + type[1]);
            	
            	//synchro.update.ActionRequests(synchro.jspace.readReadingRequests(null));
            	r = synchro.jspace.readUpdate(new ActionRequest(type[1],"reading"), timestamps.get(pair));
            	synchro.getReadings(r);
            }
        	else if (type[0].equals("makeInvestigation")) {
            	System.out.println("reading request notification " + type[1]);
            	
            	//synchro.update.ActionRequests(synchro.jspace.readReadingRequests(null));
            	r = synchro.jspace.readUpdate(new ActionRequest(type[1],"investigation"), timestamps.get(pair));
            	synchro.getReadings(r);
            }
            else if (type[0].equals("coin")) {
            	System.out.println("coin request notification " + type[1]);
            	
            	//synchro.update.Coins(synchro.jspace.readRequests(null));
            	r = synchro.jspace.readUpdate(new Coin(type[1]), timestamps.get(pair));
            	synchro.postRequests(r);
            }
            else if (type[0].equals("cargo")) {
            	System.out.println("cargo notification ");
            	
            	//synchro.update.Cargos(synchro.jspace.readCargos(null));
            	//synchro.postCargos();
            	r = synchro.jspace.readUpdate(new Cargo(), timestamps.get(pair));
            	synchro.postCargos(r);
            }
            else if (type[0].equals("points")) {
            	System.out.println("points notification " + type[1]);
            	
            	//synchro.update.Points(synchro.jspace.readPoints(null));
            	//synchro.postPoints();
            	r = synchro.jspace.readUpdate(new Points(type[1]), timestamps.get(pair));
            	synchro.postPoint(r);
            }
        } catch (Exception anE) {
            System.out.println("Got event but couldn't display it");
            anE.printStackTrace(System.out);
        }
    }
}
