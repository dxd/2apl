package dataTS;

import java.util.ArrayList;

import dataJSon.Status;

import tuplespace.ActionRequest;
import tuplespace.Position;

public class Update {
	
	private ArrayList<Location> locations;
	private ArrayList<ActionRequest> actionRead;
	private ArrayList<ActionRequest> actionInv;
	private Status status;


	public Update(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void Positions(ArrayList<Position> positions) {
		locations = new ArrayList<Location>();
		
		for (Position pos : positions)
		{
			int id = status.getPlayerId(pos.agent);
			locations.add(new Location(id,pos.cell));
		}
		
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public void ActionRequests(ArrayList<ActionRequest> ar) {
		actionRead = new ArrayList<ActionRequest>();
		actionInv = new ArrayList<ActionRequest>();
		
		for (ActionRequest a : ar)
		{
			int id = status.getPlayerId(a.agent);
			a.setId(id);
			
			if (a.type == "read")
				actionRead.add(a);
			if (a.type == "investigate")
				actionInv.add(a);
		}
		
		
	}

	public ArrayList<ActionRequest> getActionRead() {
		return actionRead;
	}

	public void setActionRead(ArrayList<ActionRequest> actionRead) {
		this.actionRead = actionRead;
	}

	public ArrayList<ActionRequest> getActionInv() {
		return actionInv;
	}

	public void setActionInv(ArrayList<ActionRequest> actionInv) {
		this.actionInv = actionInv;
	}

}
