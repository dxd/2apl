
import helperTS.Update;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.javadocmd.simplelatlng.LatLng;

import dataJSon.Reading;
import dataJSon.Status;

import tuplespace.ActionRequest;
import tuplespace.Cargo;
import tuplespace.Cell;
import tuplespace.Points;
import tuplespace.Position;
import tuplespace.Request;
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
		ArrayList<Points> points = readPoints(clock);
		ArrayList<Request> requests = readRequests(clock);
		ArrayList<Cargo> cargos = readCargos(clock);
		ArrayList<ActionRequest> ar = readReadingRequests(clock);
		
		update.Positions(positions);
		update.Points(points);
		update.Cargos(cargos);
		update.Requests(requests);
		update.ActionRequests(ar);
		return update;
	}

	private ArrayList<Cargo> readCargos(int clock) {
		Cargo cargo = new Cargo(clock);
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		getAll(cargo, cargos);
		return cargos;
	}

	private ArrayList<Request> readRequests(int clock) {
		Request request = new Request(clock);
		ArrayList<Request> requests = new ArrayList<Request>();
		getAll(request, requests);
		return requests;
	}

	private ArrayList<Points> readPoints(int clock) {

		Points point = new Points(clock);
		ArrayList<Points> points = new ArrayList<Points>();
		getAll(point, points);
		return points;
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
			//leaseRenewalManager.renewUntil(trans.lease, Lease.FOREVER, null);
			Transaction txn = trans.transaction;
			try {
				while ((entry = (T) space.take((Entry) template, txn, 200)) != null){
					System.out.println(entry.toString());
					result.add(entry);
				}
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
	}

	private ArrayList<Position> readLocations(int clock) {
		Position position = new Position(clock);
		ArrayList<Position> positions = new ArrayList<Position>();
		getAll(position, positions);
		return positions;
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
			tuplespace.Request request = new tuplespace.Request(r.getId(), cell, clock);
			write(request);
		}
		
	}

	private void writeReadings(int clock, Status status) {
		
		for (dataJSon.Reading r : status.getReadings())
		{
			Cell cell = Game.locationToGrid(new LatLng(r.getLatitude(), r.getLongitude()));
			System.out.println(cell.toString());
			tuplespace.Reading reading = new tuplespace.Reading(r.getId(), cell, clock, r.getValue());
			write(reading);
		}
		
	}

	private void write(Entry data)
	{
		System.out.println(data.toString());
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
	
	private void writeTime(int clock) {
		Time time = new Time(clock);
		write(time);

		
	}

}
