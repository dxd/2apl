
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dataJSon.Status;
import dataTS.Update;

import tuplespace.ActionRequest;
import tuplespace.Position;
import tuplespace.Time;
import tuplespace.Tuple;

import net.jini.core.discovery.LookupLocator;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.lease.LeaseDeniedException;
import net.jini.core.lease.UnknownLeaseException;
import net.jini.core.lookup.ServiceMatches;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.lookup.ServiceDiscoveryManager;
import net.jini.core.transaction.CannotAbortException;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionException;
import net.jini.core.transaction.TransactionFactory;
import net.jini.core.transaction.UnknownTransactionException;
import net.jini.core.transaction.server.TransactionManager;
import net.jini.lease.LeaseRenewalManager;
import net.jini.lease.LeaseRenewalService;
import net.jini.space.JavaSpace;


public class JSpace {
	
	static JavaSpace space = null;
	ServiceRegistrar sr = null;
	static ServiceDiscoveryManager sdm;
	private static TransactionManager transManager;
	private static LeaseRenewalManager leaseRenewalManager;
	
	ArrayList<Position> positions;
	
	public JSpace(){
		init();
	}
	
	public void init() {
		
    System.setSecurityManager(new RMISecurityManager());
		
		LookupLocator ll = null;
		try {
			ll = new LookupLocator("jini://localhost:4160");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sr = ll.getRegistrar();
		} catch (IOException e) {
			

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}catch (Exception e) {

			e.printStackTrace();
		}

		System.out.println("Service Registrar: "+sr.getServiceID());


		ServiceTemplate template = new ServiceTemplate(null, new Class[] { JavaSpace.class }, null);

		ServiceMatches sms = null;
		try {
			sms = sr.lookup(template, 10);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		if(0 < sms.items.length) {
		    space = (JavaSpace) sms.items[0].service;
		    System.out.println("Java Space found.");
		   
		   
		} else {
		    System.out.println("No Java Space found.");
		}
		ServiceTemplate trans = new ServiceTemplate(null, new Class[] { TransactionManager.class }, null);

		ServiceMatches sms1 = null;
		try {
			sms1 = sr.lookup(trans, 10);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		if(0 < sms1.items.length) {
		    transManager = (TransactionManager) sms1.items[0].service;
		    System.out.println("TransactionManager found.");
		   
		   
		} else {
		    System.out.println("No TransactionManager found.");
		}
		try {
			sdm = new ServiceDiscoveryManager(null,null);
			leaseRenewalManager = sdm.getLeaseRenewalManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public boolean error() {
		
		return space == null;
	}

	public Update readAll(Update update, int clock) {
		
		ArrayList<Position> positions = readLocations(clock);
		//readRequests();
		//readCargos();
		ArrayList<ActionRequest> ar = readReadingRequests(clock);
		update.Positions(positions);
		update.ActionRequests(ar);
		return update;
	}

	private Update createUpdates() {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<ActionRequest> readReadingRequests(int clock) {
		
		ActionRequest template = new ActionRequest(clock);
		ArrayList<ActionRequest> ar = new ArrayList<ActionRequest>();
		getAll(template, ar);
		return ar;
		
	}


	private static <T> void getAll(Object template, ArrayList<T> result) {

		T entry;
		
		try {
			Transaction.Created trans = TransactionFactory.create(transManager, Lease.FOREVER);
			leaseRenewalManager.renewUntil(trans.lease, Lease.FOREVER, null);
			Transaction txn = trans.transaction;
			try {
				while ((entry = (T) space.take((Entry) template, txn, 200)) != null){
					System.out.println(entry.toString());
					result.add(entry);
					txn.abort();
					leaseRenewalManager.cancel(trans.lease);
				}
			} catch (UnusableEntryException e) {
				e.printStackTrace();
			} catch (TransactionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (UnknownLeaseException e) {
				e.printStackTrace();
			}
			
		} catch (LeaseDeniedException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	private ArrayList<Position> readLocations(int clock) {
		Position position = new Position(clock);
		positions = new ArrayList<Position>();
		getAll(position, positions);
		return positions;
	}

	public void writeAll(int clock, Status status) {
		
		writeTime(clock);
		//writeReadings(clock);
		//writeRequests(clock);
		
	}

	private void writeRequests(int clock) {
		// TODO Auto-generated method stub
		
	}

	private void writeReadings(int clock) {
		// TODO Auto-generated method stub
		
	}

	private void writeTime(int clock) {
		Time time = new Time(clock);
		
		try {
			Lease l = space.write(time, null, Lease.FOREVER);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (TransactionException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
