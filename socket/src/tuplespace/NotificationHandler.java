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
        	String[] type = (String[]) anEvent.getRegistrationObject().get();
           // System.out.println("Got event: " + anEvent.getSource() + ", " +
             //                  anEvent.getID() + ", " +
               //                anEvent.getSequenceNumber() + ", " + 
                 //              anEvent.getRegistrationObject().get());
            
            if (type[0].equals("position")) {
            	System.out.println("position notification " + type[1]);
            	//synchro.update.UpdatePosition((Position) synchro.jspace.readUpdate(new Position(type[1])));
            	//System.out.println("position posting");
            	synchro.postLocation((Position) synchro.jspace.readUpdate(new Position(type[1])));
            }
            if (type[0].equals("makeReading")) {
            	System.out.println("reading request notification " + type[1]);
            	
            	//synchro.update.ActionRequests(synchro.jspace.readReadingRequests(null));
            	synchro.getReading((ActionRequest)synchro.jspace.readUpdate(new ActionRequest(type[1],"reading")));
            	//synchro.getReadings();
            }
            if (type[0].equals("makeInvestigation")) {
            	System.out.println("reading request notification " + type[1]);
            	
            	//synchro.update.ActionRequests(synchro.jspace.readReadingRequests(null));
            	synchro.getInvestigation((ActionRequest)synchro.jspace.readUpdate(new ActionRequest(type[1],"investigation")));
            	//synchro.getReadings();
            }
            if (type[0].equals("coin")) {
            	System.out.println("coin request notification " + type[1]);
            	
            	//synchro.update.Coins(synchro.jspace.readRequests(null));
            	synchro.postRequest((Coin)synchro.jspace.readUpdate(new Coin(type[1])));
            }
            if (type[0].equals("cargo")) {
            	System.out.println("cargo notification " + type[1]);
            	
            	//synchro.update.Cargos(synchro.jspace.readCargos(null));
            	//synchro.postCargos();
            	synchro.postCargo((Cargo)synchro.jspace.readUpdate(new Cargo()));
            }
            if (type[0].equals("points")) {
            	System.out.println("points notification " + type[1]);
            	
            	//synchro.update.Points(synchro.jspace.readPoints(null));
            	//synchro.postPoints();
            	synchro.postPoint((Points)synchro.jspace.readUpdate(new Points(type[1])));
            }
        } catch (Exception anE) {
            System.out.println("Got event but couldn't display it");
            anE.printStackTrace(System.out);
        }
    }
}
