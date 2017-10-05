package tsp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import org.omg.Messaging.SyncScopeHelper;

public class A_star {

	/** Pathfind here **/

	private City last;

	public int Pathfind(HashMap<City, ArrayList<City>> list) {

		// PriorityQueue<City> neb = new PriorityQueue<City>();
		// neb.addAll(list);

		int nbrOfCities = list.size();
		int count = 0;
		
		
		//If there is only one city
		if(list.size() == 1) {
			System.out.println("A");
			return 1;
		}
		City current;
		City testC= null;
		City start = null;
		
		//Find the starting City A
		for(City c: list.keySet()) {
			if(c.getName().equals("A")) {
				start = c; 
				break;
				
			} 
			
			
		}
		
		//Put A in a priority queue, sorted by lowest FCost
		PriorityQueue<City> open = new PriorityQueue<City>();
		open.add(start);
		
		LinkedList<City> closed = new LinkedList<City>();


		
		int nodes = 0;
		
	
	
		while (!open.isEmpty())

		{
			current = open.poll();
			
			
			//If the current city has as many parents as there are cities, we only have to go back to the starting node.
			if (current.getParents().size() == nbrOfCities) {

				ArrayList<City> successorList = list.get(current);
				
				//Find the start node.
				for (City successor : successorList) {

					if (successor.equals(start)) {
						nodes++;

						double gcost = current.distanceTo(successor) + current.getGCost();


						
						last = new City(successor.getName(), successor.getX(), successor.getY());
						last.setGCost(gcost);

						last.setParents(current.getParents());
						last.getParents().add(last.getName());
						
						break;
					}
				}
			} else {
				//Get the current city's successors.
				ArrayList<City> successorList = list.get(current);
				
				//Go through each successor
				for (City successor : successorList) {

					//If they are not already a parent to the current city
					if (!current.getParents().contains(successor.getName())) {
						nodes++;
						
						//Create a new city object with the correct g, h, and f values. Put it in the prio queue. 
						double gcost = current.getGCost() + current.distanceTo(successor);
						City newSuccessor = new City(successor.getName(), successor.getX(), successor.getY());
						newSuccessor.setParents(current.getParents());
						newSuccessor.getParents().add(current.getName());
						newSuccessor.setGCost(gcost);
					
						
						
						
						
						
						newSuccessor.setHCost(successor.getHCost());
////						
//						

						
						
						//Special case 
						open.add(newSuccessor);
						if(newSuccessor.getParents().size() == nbrOfCities -1) {
							newSuccessor.getParents().add(newSuccessor.getName());
						
						}
					}
				}
				closed.add(current);

			}

		}

	
		System.out.println(last.getParents());

		System.out.println("number of nodes is " + nodes);
		reconstructPath(last);
		return nodes;
	}

	
	//Prints the  last city's parents and g costs, reconstructing the path taken.
	public void reconstructPath(City last) {
		

		
		for(String s: last.getParents()) {
			System.out.println(s);
		}
		System.out.println("Total cost is " + last.getGCost());
	}
}
