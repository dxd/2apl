package helperTS;

import tuplespace.Points;

public class AgentPoints extends Points {
	
	private int id;
	
	public AgentPoints(int id, Integer points) {
		this.id = id;
		this.value = points;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoints() {
		return value;
	}

	public void setPoints(int points) {
		this.value = points;
	}
	

}
