package game;

import helperTS.Update;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.rmi.MarshalledObject;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.javadocmd.simplelatlng.LatLng;

import dataJSon.Reading;
import dataJSon.Status;

import tuplespace.ActionRequest;
import tuplespace.Cargo;
import tuplespace.Cell;
import tuplespace.NotificationHandler;
import tuplespace.Points;
import tuplespace.Position;
import tuplespace.Prohibition;
import tuplespace.Coin;
import tuplespace.Time;
import tuplespace.TimeEntry;


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

	/*public Update readAll(Update update, int clock) {
		
		ArrayList<Position> positions = readLocations(clock);
		ArrayList<Points> points = readPoints(clock);
		ArrayList<Coin> coins = readRequests(clock);
		ArrayList<Cargo> cargos = readCargos(clock);
		ArrayList<ActionRequest> ar = readReadingRequests(clock);
		
		update.Positions(positions);
		update.Points(points);
		update.Cargos(cargos);
		update.Coins(coins);
		update.ActionRequests(ar);
		return update;
	}*/
	

	public TimeEntry readUpdate(TimeEntry entry) {
	
		Position p1 = (Position) getLatest(entry);
		return p1;
	}
	
	
/*	public ArrayList<Position> readLocations(Integer clock) {
		ArrayList<Position> positions = new ArrayList<Position>();
		for (String a : agents)
		{
			Position position = new Position(a);
			ArrayList<TimeEntry> p = new ArrayList<TimeEntry>();
			Position p1 = (Position) getLatest(position);
			positions.add((Position)p1);
		}
		return positions;
	}
	
	public ArrayList<Cargo> readCargos(Integer clock) {
		Cargo cargo = new Cargo(clock);
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		getLatest(cargo, cargos);
		return cargos;
	}

	public ArrayList<Coin> readRequests(Integer clock) {
		
		ArrayList<Coin> coins = new ArrayList<Coin>();
		for (String a : agents)
		{
			Coin coin = new Coin(a);
			ArrayList<Coin> p = new ArrayList<Coin>();
			getLatest(coin, p);
			coins.addAll(p);
		}
		
		return coins;
	}

	public ArrayList<Points> readPoints(Integer clock) {

		ArrayList<Points> points = new ArrayList<Points>();
		for (String a : agents)
		{
			Points point = new Points(a);
			ArrayList<Points> p = new ArrayList<Points>();
			getLatest(point, p);
			points.addAll(p);
		}
		return points;
	}

	public ArrayList<ActionRequest> readReadingRequests(Integer clock) {
		
		
		
		ArrayList<ActionRequest> ar = new ArrayList<ActionRequest>();
		for (String a : agents)
		{
			ActionRequest t = new ActionRequest(a);
			ArrayList<ActionRequest> p = new ArrayList<ActionRequest>();
			getLatest(t, p);
			ar.addAll(p);
		}
		
		return ar;
		
	}*/


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

	


	public void writeAll(int clock, Status status) {
		
		writeTime(clock);
		writeReadings(clock, status);
		writeRequests(clock, status);
		writeCargos(clock, status);
		writeLocations(clock, status);
	}

	private void writeCargos(int clock, Status status) {
		for (dataJSon.Cargo c : status.getCargos())
		{
			Cell cell = Game.locationToGrid(new LatLng(c.getLatitude(), c.getLongitude()));
			tuplespace.Cargo cargo = new tuplespace.Cargo(c.getId(), cell, clock);
			write(cargo);
		}
	}

	private void writeLocations(int clock, Status status) {
		for (dataJSon.Location l : status.getLocations())
		{
			Cell cell = Game.locationToGrid(new LatLng(l.getLatitude(), l.getLongitude()));
			tuplespace.Position position = new tuplespace.Position(l.getPlayer_id(),status.getPlayerName(l.getPlayer_id()), cell, clock);
			write(position);
		}
		
	}

	private void writeRequests(int clock, Status status) {
		for (dataJSon.Request r : status.getRequests())
		{
			Cell cell = Game.locationToGrid(new LatLng(r.getLatitude(), r.getLongitude()));
			tuplespace.Coin coin = new tuplespace.Coin(r.getId(), cell,"a1" , clock); //TODO agent is hardcoded
			write(coin);
		}
		
	}

	private void writeReadings(int clock, Status status) {
		
		for (dataJSon.Reading r : status.getReadings())
		{
			Cell cell = Game.locationToGrid(new LatLng(r.getLatitude(), r.getLongitude()));
			//System.out.println(cell.toString());
			tuplespace.Reading reading = new tuplespace.Reading(r.getId(),status.getPlayerName(r.getPlayer_id()), cell, clock, r.getValue());
			write(reading);
		}
		
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
				if (t1.time != null && t1.time != null)  
					return t1.time.compareTo(t2.time);
				return 1;
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
