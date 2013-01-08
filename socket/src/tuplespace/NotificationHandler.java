package tuplespace;

import game.JSpace;
import game.Synchronization;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import net.jini.core.event.RemoteEvent;
import net.jini.core.event.RemoteEventListener;
import net.jini.space.JavaSpace;

public class NotificationHandler extends UnicastRemoteObject implements RemoteEventListener {

	JavaSpace space;
	Synchronization synchro;
	
	public NotificationHandler() throws RemoteException {
		
    }

	public NotificationHandler(JavaSpace js) throws RemoteException {
		space = js;
	}
	


    public NotificationHandler(Synchronization s) throws RemoteException {
		synchro = s;
	}

	public void notify(RemoteEvent anEvent) {

        try {
        	String type = anEvent.getRegistrationObject().get().toString();
           // System.out.println("Got event: " + anEvent.getSource() + ", " +
             //                  anEvent.getID() + ", " +
               //                anEvent.getSequenceNumber() + ", " + 
                 //              anEvent.getRegistrationObject().get());
            
            if (type.equals("position")) {
            	System.out.println("position notification");
            	
            	synchro.update.Positions(synchro.jspace.readLocations(null));
            	synchro.postLocations();
            }
            if (type.equals("makeReading")) {
            	System.out.println("reading request notification");
            	
            	synchro.update.ActionRequests(synchro.jspace.readReadingRequests(null));
            	synchro.getReadings();
            }
            if (type.equals("coin")) {
            	System.out.println("coin request notification");
            	
            	synchro.update.Coins(synchro.jspace.readRequests(null));
            	synchro.postRequests();
            }
            if (type.equals("cargo")) {
            	System.out.println("cargo notification");
            	
            	synchro.update.Cargos(synchro.jspace.readCargos(null));
            	synchro.postCargos();
            }
            if (type.equals("points")) {
            	System.out.println("points notification");
            	
            	synchro.update.Points(synchro.jspace.readPoints(null));
            	synchro.postPoints();
            }
        } catch (Exception anE) {
            System.out.println("Got event but couldn't display it");
            anE.printStackTrace(System.out);
        }
    }
}
