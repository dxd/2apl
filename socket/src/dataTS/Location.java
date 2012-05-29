package dataTS;

import tuplespace.Cell;

public class Location {
	
	private int id;
	private Cell cell;
	
	public Location(int id, Cell cell) {
		this.id = id;
		this.cell = cell;
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
	

}
