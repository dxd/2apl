package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import com.javadocmd.simplelatlng.LatLng;

import dataJSon.Status;
import tuplespace.Cell;
import tuplespace.Position;
import tuplespace.Time;
import tuplespace.TimeEntry;
import net.jini.core.discovery.LookupLocator;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.lease.LeaseDeniedException;
import net.jini.core.lookup.ServiceMatches;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.lookup.ServiceDiscoveryManager;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionException;
import net.jini.core.transaction.TransactionFactory;
import net.jini.core.transaction.server.TransactionManager;
import net.jini.space.JavaSpace;


public class JSpace {
	
	public static JavaSpace space = null;
	ServiceRegistrar sr = null;
	static ServiceDiscoveryManager sdm;
	private static TransactionManager transManager;
	//private static LeaseRenewalManager leaseRenewalManager;
	public static String[] agents = {"a1", "a2", "a3", "t1", "c1"};

	public JSpace(){
		init();
	}
	
	public void init() {
		
        System.setSecurityManager(new RMISecurityManager());
      
        try {
            File file = new File("./log/space"+ System.currentTimeMillis() +".log");

            // Create file if it does not exist
            boolean success = file.createNewFile();
            if (success) {
                // File did not exist and was created
            } else {
                // File already exists
            }
            
            PrintStream printStream;
    		try {
    			printStream = new PrintStream(new FileOutputStream(file));
    			System.setOut(printStream);
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        } catch (IOException e) {
        	
        }
		
		LookupLocator ll = null;
		try {
			//ll = new LookupLocator("jini://kafka.cs.nott.ac.uk:4160");
			ll = new LookupLocator("jini://10.154.219.251");
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
/*		try {
			sdm = new ServiceDiscoveryManager(null,null);
			leaseRenewalManager = sdm.getLeaseRenewalManager();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}

	public boolean error() {
		
		return space == null;
	}

	public ArrayList<TimeEntry> readUpdate(TimeEntry entry, Date date) {
	
		ArrayList<TimeEntry> t = (ArrayList<TimeEntry>) getAllFromDate(entry, date);
		return t;
	}
	

	private ArrayList<TimeEntry> getAllFromDate(TimeEntry te, Date date) {
		TimeEntry entry;
		try {
			Transaction.Created trans = TransactionFactory.create(transManager, Lease.FOREVER);
			//leaseRenewalManager.renewUntil(trans.lease, Lease.FOREVER, null);
			Transaction txn = trans.transaction;
			try {
				ArrayList<TimeEntry> result = new ArrayList<TimeEntry>();
				while ((entry = (TimeEntry) space.take(te, txn, 200)) != null){
					//System.out.println(entry.toString());
					result.add(entry);
				}
				ArrayList<TimeEntry> e = getFromDate(result,date);
				//System.out.println(result.toString());
				txn.abort();
				//leaseRenewalManager.cancel(trans.lease);
				return e;
			} catch (UnusableEntryException e) {
				e.printStackTrace();
			} catch (TransactionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
		} catch (LeaseDeniedException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private ArrayList<TimeEntry> getFromDate(ArrayList<TimeEntry> result,
			Date date) {
		if (result.size() > 0) {
			for (TimeEntry te : result) {
				if (te.getTime().after(date))
					result.remove(te);
			}
			return result;
		}

		return null;
	}
	private TimeEntry getLatest(Entry template) {

		TimeEntry entry;
		TimeEntry t = null;
		
		ArrayList<TimeEntry> result = new ArrayList<TimeEntry>();
		try {
			Transaction.Created trans = TransactionFactory.create(transManager, Lease.FOREVER);
			//leaseRenewalManager.renewUntil(trans.lease, Lease.FOREVER, null);
			Transaction txn = trans.transaction;
			try {
				while ((entry = (TimeEntry) space.take(template, txn, 200)) != null){
					//System.out.println(entry.toString());
					result.add(entry);
				}
				t = getLatest(result);
				//System.out.println(result.toString());
				txn.abort();
				//leaseRenewalManager.cancel(trans.lease);
			} catch (UnusableEntryException e) {
				e.printStackTrace();
			} catch (TransactionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
		} catch (LeaseDeniedException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		return t;
	}


	private void write(Entry data)
	{
		//System.out.println(data.toString());
		try {
			Lease l = space.write(data, null, Lease.FOREVER);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (TransactionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void writeTime(int clock) {
		Time time = new Time(clock);
		write(time);	
	}

	private TimeEntry getLatest(ArrayList<TimeEntry> result) {
		
		if (result.size() > 1) {
			//System.out.println("to be compared: "+result.toString());	
		Collections.sort(result, new Comparator<TimeEntry>(){
			  public int compare(TimeEntry t1, TimeEntry t2) {
				  //TimeEntry t3 = (TimeEntry) t1;
				 //TimeEntry t4 = (TimeEntry) t2;
				//if (t1.time != null && t1.time != null)  
					return t1.getTime().compareTo(t2.getTime());
				//return 1;
			  }
			  
			});
		return result.get(result.size()-1);

		}
		else if (result.size() == 1) {
			return result.get(0);
		}

		return null;
		
	}
/*	private <T> T getLatest(ArrayList<T> result) {
		
		if (result.size() > 0) {
			System.out.println("to be compared: "+result.toString());	
		Collections.sort(result, new Comparator<T>(){
			  public int compare(T t1, T t2) {
				  TimeEntry t3 = (TimeEntry) t1;
				  TimeEntry t4 = (TimeEntry) t2;
			    return (int) (t3.time.getTime() - t4.time.getTime());
			  }
			  
			});
		return result.get(result.size()-1);

		}

		return null;
		
	}*/

	public void writeReading(tuplespace.Reading reading, int clock, Status status) {
		//Cell cell = Game.locationToGrid(new LatLng(reading2.getLatitude(), reading2.getLongitude()));
		//System.out.println(cell.toString());
		tuplespace.Reading reading2 = new tuplespace.Reading(reading.id,reading.agent, reading.cell, clock, reading.value);
		write(reading2);
		
	}




/*			theManager.renewFor(myReg.getLease(), Lease.FOREVER,
                30000, new DebugListener());*/
	
}
