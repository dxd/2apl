
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;

import tuplespace.Position;
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
	
	JavaSpace space = null;
	ServiceRegistrar sr = null;
	static ServiceDiscoveryManager sdm;
	private TransactionManager transManager;
	private LeaseRenewalService leaseRenewalService;
	private LeaseRenewalManager leaseRenewalManager;
	
	public JSpace(){
		Init();
	}
	
	public void Init() {
		
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

	public void read() {
		
		 // do something with the space
	    Tuple t1 = new Tuple("robot1");
	    Tuple t0 = new Tuple("robot0");
	    long i = 100;
	    try {
			//Lease l = space.write(t, null, i);
	    	//space.write(t1,null, Lease.FOREVER);
			Tuple read = (Tuple) space.readIfExists(t1, null, i);
			if (read != null)
				System.out.println(read.toString());
			else 
				System.out.println("not found");
			
			Tuple read0 = (Tuple) space.readIfExists(t0, null, i);
			if (read0 != null)
				System.out.println(read0.toString());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (TransactionException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void readLocation(String agent) {

	    Position position;
	    ArrayList<Position> positions = new ArrayList<Position>();
	    
	    try {
			Transaction.Created trans = TransactionFactory.create(transManager, Lease.FOREVER);
			leaseRenewalManager.renewUntil(trans.lease, Lease.FOREVER, null);
			Transaction txn = trans.transaction;
			Entry template = space.snapshot(new Position(agent));
			try {
				while ((position = (Position) space.take(template, txn, 200)) != null){
					System.out.println("Position found " + position);
					positions.add(position);
				}
			} catch (UnusableEntryException e) {
				e.printStackTrace();
			} catch (TransactionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				txn.abort();
			} catch (UnknownTransactionException e) {
				e.printStackTrace();
			} catch (CannotAbortException e) {
				e.printStackTrace();
			}
			try {
				leaseRenewalManager.cancel(trans.lease);
			} catch (UnknownLeaseException e) {
				e.printStackTrace();
			}
			
		} catch (LeaseDeniedException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	    
	
	}

	public boolean error() {
		
		return space == null;
	}

}
