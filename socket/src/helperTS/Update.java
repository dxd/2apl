/*package helperTS;

import java.util.ArrayList;

import dataJSon.Status;

import tuplespace.ActionRequest;
import tuplespace.Cargo;
import tuplespace.Points;
import tuplespace.Position;
import tuplespace.Coin;

public class Update {
	
	private ArrayList<Position> positions;
	private ArrayList<ActionRequest> actionRead;
	private ArrayList<ActionRequest> actionInv;
	private ArrayList<Points> points;
	private ArrayList<Cargo> cargos;
	private ArrayList<Coin> coins;
	
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

	public void Positions(ArrayList<Position> p) {
		positions = new ArrayList<Position>();
		for (Position pos : p)
		{
			pos.id = status.getPlayerId(pos.agent);
			positions.add(pos);
		}
		
	}
	
	public void UpdatePosition(Position p) {

			p.id = status.getPlayerId(p.agent);
			positions.add(p);

		
	}

	public ArrayList<Position> getPositions() {
		return positions;
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

	public void Points(ArrayList<Points> pts) {
		points = new ArrayList<Points>();
		
		for (Points p : pts)
		{
			p.id = status.getPlayerId(p.agent);
			points.add(p);
		}
	}

	public ArrayList<Points> Points() {
		return points;
	}

	public void setPoints(ArrayList<Points> points) {
		this.points = points;
	}

	public void Cargos(ArrayList<Cargo> cargosts) {
		cargos = new ArrayList<Cargo>();
		
		for (Cargo c : cargosts)
		{
			if (c.getId() == null)
				cargos.add(c);
		}
		
	}

	public void Coins(ArrayList<Coin> requeststs) {
		coins = new ArrayList<Coin>();
		
		for (Coin r : requeststs)
		{
			if (r.getId() == null)
				coins.add(r);
		}
		
	}

	public ArrayList<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(ArrayList<Cargo> cargos) {
		this.cargos = cargos;
	}

	public ArrayList<Coin> getRequests() {
		return coins;
	}

	public void setRequests(ArrayList<Coin> coins) {
		this.coins = coins;
	}

}
*/