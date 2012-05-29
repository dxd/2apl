package dataTS;

import tuplespace.Cell;

public class AgentPoints {
	
	private int id;
	private Cell cell;
	private int points;
	
	public AgentPoints(int id, Integer points) {
		this.id = id;
		this.points = points;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	

}
