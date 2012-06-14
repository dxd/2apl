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
            System.out.println("Got event: " + anEvent.getSource() + ", " +
                               anEvent.getID() + ", " +
                               anEvent.getSequenceNumber() + ", " + 
                               anEvent.getRegistrationObject().get());
            
            if (type.equals("position")) {
            	System.out.println("position notification");
            	
            	synchro.update.Positions(synchro.jspace.readLocations(null));
            	synchro.postLocations(synchro.update.getLocations());
            }
        } catch (Exception anE) {
            System.out.println("Got event but couldn't display it");
            anE.printStackTrace(System.out);
        }
    }
}
