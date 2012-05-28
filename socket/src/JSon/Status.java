package JSon;

import java.util.ArrayList;
import java.util.List;

public class Status {
	
	private List<Request> request = new ArrayList<Request>();
	private List<Location> location = new ArrayList<Location>();
	private List<Reading> reading = new ArrayList<Reading>();
	private List<Cargo> cargo = new ArrayList<Cargo>();
	private List<Player> player = new ArrayList<Player>();
	
	public Status(){
		
	}

	public List<Request> getRequests() {
		return request;
	}


	public void setRequests(List<Request> requests) {
		this.request = requests;
	}

	public List<Location> getLocations() {
		return location;
	}

	public void setLocations(List<Location> locations) {
		this.location = locations;
	}

	public List<Reading> getReadings() {
		return reading;
	}

	public void setReadings(List<Reading> readings) {
		this.reading = readings;
	}

	public List<Cargo> getCargos() {
		return cargo;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargo = cargos;
	}

	public List<Player> getPlayers() {
		return player;
	}

	public void setPlayers(List<Player> players) {
		this.player = players;
	}




}
