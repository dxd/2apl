package dataJSon;

public class Player {
	
	private int id;
	private String name;
	private int points_cache;
	private String team;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints_cache() {
		return points_cache;
	}
	public void setPoints_cache(int points_cache) {
		this.points_cache = points_cache;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

}
