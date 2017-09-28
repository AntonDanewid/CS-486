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
		
		if(list.size() == 1) {
			System.out.println("A");
			return 1;
		}
		City current;
		City testC= null;
		City start = null;
		

		for(City c: list.keySet()) {
			if(c.getName().equals("A")) {
				start = c; 
				break;
				
			} 
			
			
		}
		
		PriorityQueue<City> open = new PriorityQueue<City>();
		open.add(start);
		
		LinkedList<City> closed = new LinkedList<City>();
		//System.out.println(list.containsKey(start));
		int nodes = 0;
		
		

		while (!open.isEmpty())

		{
			current = open.poll();
			//System.out.println(current.toString());
			
			
//			for(City c: list.keySet()) {
//				System.out.println(c.hashCode());
//				
//			}
			
			if (current.getParents().size() == nbrOfCities) {
				//System.out.println("CURRENT PATH " + current.getParents().toString());
				ArrayList<City> successorList = list.get(current);

				for (City successor : successorList) {
					//System.out.println("HÄR" +successor.toString());
					if (successor.equals(start)) {
						nodes++;

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
						nodes++;
						double gcost = current.getGCost() + current.distanceTo(successor);
						City newSuccessor = new City(successor.getName(), successor.getX(), successor.getY());
						//System.out.println(successor.getHCost());

						newSuccessor.setParents(current.getParents());
						newSuccessor.getParents().add(current.getName());
						//newSuccessor.getParents().add(newSuccessor.getName());
						newSuccessor.setGCost(gcost);
						if(newSuccessor.getName().equals("A")) {
							System.out.println("ERROR " + current.getParents());
						}
//						if(successor.getHCost() == 0) {
//							System.out.println("ERROR IN H COST");
//						}
						newSuccessor.setHCost(successor.getHCost());
//						if(newSuccessor.getName().equals("E")) {
//							System.out.println("ITS FUCKING IN");
//						}
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

	public void reconstructPath(City last) {
		
//		for(int i = last.getParents().size()- 1; i > -1; i--) {
//			System.out.println(last.getParents().get(i));
//			
//		}
		
		//System.out.println("A");
		for(String s: last.getParents()) {
			System.out.println(s);
		}
		System.out.println("Total cost is " + last.getGCost());
	}
}
