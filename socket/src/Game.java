import java.sql.Time;

import tuplespace.Cell;

import com.javadocmd.simplelatlng.*;

import static java.lang.Math.*;

 
public class Game {
	
	private LatLng start;
	private double kmX = 0.400;
	private double kmY = 0.400;
	
	private Time startTime;
	private static int gamePace = 1000;
	private int clock;
	
	private static int gridX = 10;
	private static int gridY = 10;
	
	private LatLng[][] grid;
	private double width;
	private double height;
	
	public Game(LatLng start, double metersX, double metersY){
		
		this.start = start;
		this.kmX = metersX;
		this.kmY = metersY;
		
		initiateGrid();
		
	}

	private void initiateGrid() {
		
		width = kmX / gridX;
		height = kmY / gridY;
		grid[0][0] = start;
		
		for (int i = 1; i < gridX; i++) {
			for (int j = 1; j < gridY; j++) {
				double dx = width*i;
				double dy = height*j;
				int r_earth = 6378; //TODO adjust for latitude
				
				double latitude  = start.getLatitude()  + (dy / r_earth) * (180 / PI);
				double longitude = start.getLongitude() + (dx / r_earth) * (180 / PI) / cos(start.getLatitude() * 180/PI);
				
				grid[i][j] = new LatLng(latitude, longitude);
			}
		}
		
		
	}
	
	public Cell locationToGrid(LatLng loc){
		
		for (int i = 1; i < gridX; i++) {
			for (int j = 1; j < gridY; j++) {
				if (loc.getLatitude() <= grid[i][j].getLatitude() && loc.getLongitude() >= grid[i][j].getLongitude())
				{
					if (i+1 < gridX && j+1 < gridY) {
						if (loc.getLatitude() >= grid[i+1][j+1].getLatitude() && loc.getLongitude() <= grid[i+1][j+1].getLongitude()){
							return new Cell(i,j);
						}
					}
					else
						return new Cell(i,j);
						
				}
			}
		}
		return null;
		
	}
	
	public LatLng gridToLocation(Cell cell){
		
		double dx = width/2;
		double dy = height/2;
		int r_earth = 6378; //TODO adjust for latitude
		
		double latitude  = grid[cell.x][cell.y].getLatitude()  + (dy / r_earth) * (180 / PI);
		double longitude = grid[cell.x][cell.y].getLongitude() + (dx / r_earth) * (180 / PI) / cos(grid[cell.x][cell.y].getLatitude() * 180/PI);
		
		return new LatLng(latitude, longitude);
		
	}

}
