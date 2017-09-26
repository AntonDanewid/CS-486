import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class A_star {

	/** Pathfind here **/

	private City last;

	public void Pathfind(HashMap<City, ArrayList<City>> list) {

		// PriorityQueue<City> neb = new PriorityQueue<City>();
		// neb.addAll(list);

		int nbrOfCities = list.size();
		int count = 0;

		City current;
		City testC= null;
		City start = null;


		for(City c: list.keySet()) {
			if(c.getName().equals("A")) {
				start = c; 
				
			} 
			if(c.getName().equals("C")) {
				testC = c;
				System.out.println("yes");
			}
			
		}
		
		PriorityQueue<City> open = new PriorityQueue<City>();
		open.add(start);
		
		//System.out.println(list.containsKey(start));
		int level = 1;
		
		

		while (!open.isEmpty())

		{
			
			current = open.poll();
			//System.out.println(current.toString());
			
			
//			for(City c: list.keySet()) {
//				System.out.println(c.hashCode());
//				
//			}
			
			if (current.getParents().size() == nbrOfCities - 1) {
				ArrayList<City> successorList = list.get(current);

				for (City successor : successorList) {
					//System.out.println("HÄR" +successor.toString());
					if (successor.equals(start)) {
						double gcost = current.distanceTo(successor) + current.getGCost();

						// Lös gCost samt att parents läggs till på rätt sätt

						last = new City(successor.getName(), successor.getX(), successor.getY());
						last.setGCost(gcost);
						//System.out.println("HÄR" + current.getParents());
						last.setParents(current.getParents());
						last.getParents().add(last.getName());

						break;
					}
				}
			} else {
				ArrayList<City> successorList = list.get(current);
				//System.out.println(list.get(current));
				//System.out.println(current.hashCode());
				for (City successor : successorList) {
					if (!current.getParents().contains(successor.getName())) {
						double gcost = current.getGCost() + current.distanceTo(successor);
						City newSuccessor = new City(successor.getName(), successor.getX(), successor.getY());
						//System.out.println(successor.equals(testC));
						
						newSuccessor.setParents(current.getParents());
						newSuccessor.getParents().add(newSuccessor.getName());
						newSuccessor.setGCost(gcost);
						newSuccessor.setHCost(successor.getHCost());
						open.add(newSuccessor);
					}
				}

			}

		}


		reconstructPath(last);
	}

	public void reconstructPath(City last) {
		
		for(int i = last.getParents().size()- 1; i > -1; i--) {
			System.out.println(last.getParents().get(i));
			
		}
		System.out.println("A");
	}
}
