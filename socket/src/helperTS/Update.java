package helperTS;

import java.util.ArrayList;

import dataJSon.Status;

import tuplespace.ActionRequest;
import tuplespace.Cargo;
import tuplespace.Points;
import tuplespace.Position;
import tuplespace.Request;

public class Update {
	
	private ArrayList<Position> locations;
	private ArrayList<ActionRequest> actionRead;
	private ArrayList<ActionRequest> actionInv;
	private ArrayList<AgentPoints> agentPoints;
	private ArrayList<Cargo> cargos;
	private ArrayList<Request> requests;
	
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
		locations = new ArrayList<Position>();
		
		for (Position pos : positions)
		{
			int id = status.getPlayerId(pos.agent);
			locations.add(new Position(id,pos.cell));
		}
		
	}

	public ArrayList<Position> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Position> locations) {
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

	public void Points(ArrayList<Points> points) {
		agentPoints = new ArrayList<AgentPoints>();
		
		for (Points p : points)
		{
			int id = status.getPlayerId(p.agent);
			agentPoints.add(new AgentPoints(id,p.value));
		}
	}

	public ArrayList<AgentPoints> getAgents() {
		return agentPoints;
	}

	public void setAgents(ArrayList<AgentPoints> agentPoints) {
		this.agentPoints = agentPoints;
	}

	public void Cargos(ArrayList<Cargo> cargosts) {
		cargos = new ArrayList<Cargo>();
		
		for (Cargo c : cargosts)
		{
			if (c.getId() == null)
				cargos.add(c);
		}
		
	}

	public void Requests(ArrayList<Request> requeststs) {
		requests = new ArrayList<Request>();
		
		for (Request r : requeststs)
		{
			if (r.getId() == null)
				requests.add(r);
		}
		
	}

	public ArrayList<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(ArrayList<Cargo> cargos) {
		this.cargos = cargos;
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}

}
