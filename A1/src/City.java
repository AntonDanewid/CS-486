import java.util.ArrayList;
import java.util.Objects;

public class City implements Comparable {

	
	private int x; 
	private int y; 
	private String name; 
	private ArrayList<String> parents = new ArrayList<String>(); 
	
	
	private double g; 
	private double h; 
	private double fCost; 
	
	
	
	
	public City(String name, int x, int y) {
		this.x = x; 
		this.y = y; 
		this.name = name;
		//parents.add(name);
		
	}

	
	public ArrayList<String> getParents() {
		return parents;
	}
	
	public String getName() {
		return name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y; 
	}

	@Override
	public int compareTo(Object other) {
		City c = (City) other; 
		
		return Double.compare(fCost, c.getFCost());
	}
	
	public double distanceTo(City other) {
		return Math.hypot(x +other.x , y+other.y);
	}
	
	public void setFCost(City start, City end) {
		fCost = g + distanceTo(end); 
	}
	
	private void updateFCost() {
		fCost = h + g; 
	}
	
	public double getFCost() {
		return fCost;
	}
	
	public void setGCost(double g) {
		this.g = g; 
		updateFCost();
	}
	
	public void setHCost(double h) {
		this.h= h;
		updateFCost();
	}
	
	public double getGCost() {
		return g; 
	}
	
	public double getHCost() {
		return h; 
	}
	
	public void setFCost(double cost) {
		fCost = cost; 
	}
	
//	public void setParent(City parent) {
//		this.parent = parent; 
//	}
//	

	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
            return true;
        }
 
    
        City c = (City) o;
         
        return name.equals(c.name);
    
	}
	
	public void setParents(ArrayList<String> parents) {
		for(String s: parents) {
			this.parents.add(s);
		}
	}
	
	public int hashCode() {
		//System.out.println("hashcode for " + name + " is " + Objects.hash(name));
		return Objects.hash(name);
	}
	
	
}
