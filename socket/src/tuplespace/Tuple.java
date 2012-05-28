package tuplespace;

import java.awt.Point;

import net.jini.core.entry.Entry;

public class Tuple implements Entry {
	
	public String type;
	public Point point;
	
	public Tuple() {

	}
	
	public Tuple(String n, Point p) {
		this.type = n;
		this.point = p;
	}
	
	public Tuple(String n) {
		this.type = n;
	}
				
		public String toString(){
			return "Tuple: <"+type+", point("+point.x+","+point.y+")>";
		}
		
		public String toProlog(){
			String pointStr = "null";
			if(point!=null) {
				pointStr = "point("+point.x+","+point.y+")";
			}
			return "tuple("+type+","+pointStr+").";
		}
}
