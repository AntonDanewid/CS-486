package tspAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Annealing {

	private ArrayList<City> list;

	private ArrayList<String> currentPath;

	private double currentCost;
	private Random rand; 
	private double p; 
	
	private double temperature; 
	private double cooling; 
	double end;
	
	
	
	void startAnnealing(ArrayList<City> list) {
		int number = 0;
		cooling = 0.99;
		temperature = 100000000000000.0f;
		end = 0.00001;
		rand = new Random();
		this.list = list;
		randomPath();
		currentCost = calcGCost(list);
		System.out.println("Initial cost is " + currentCost);
		ArrayList<City> nextPath = null;
		while(temperature > end) {
			nextPath = changePath();
			double nextCost = calcGCost(nextPath);
			if(nextCost < currentCost) {
				list = nextPath;
				currentCost = nextCost;
			}
			else if(calcProbability(nextCost) > Math.random()){
				list = nextPath;
				//System.out.println("HERE");
				currentCost = nextCost;
			}			
			temperature = temperature * cooling;
			number++;
		}
		
		
		
		
		System.out.println();
		
		System.out.println("Cost is now " + currentCost);
		System.out.println(list);
		System.out.println(number);

	}

	// Randomize first path
	private void randomPath() {

		
		City start = list.remove(0);
		Collections.shuffle(list);
		list.add(0, start);
		list.add(start);
		System.out.println(list);		
	}
	
	
	
	
	
	private double calcProbability(double cost) {
		double  exponent = -(cost - currentCost)/temperature;
		return Math.pow(Math.E, exponent);
	}
	

	private double calcGCost(ArrayList<City> path) {
		double temp = 0;
		
		for (int i = 0; i < path.size(); i++) {

			if (i < path.size() - 1) {
				temp += path.get(i).distanceTo(path.get(i + 1));
			}
		}
		return temp;
	}
	
	private ArrayList<City> changePath() {
		int first = 1 + rand.nextInt(list.size());
		int second = 1 + rand.nextInt(list.size());
		
		
		ArrayList<City> temp = new ArrayList<City>();
		while(first == second) {
			second = 1 + rand.nextInt(list.size());
		}
		
		
		if(first < second) {
//			
			temp.addAll(list.subList(first, second));
			Collections.reverse(temp);
			for(int i = 0; i < first; i++) {
				temp.add(i, list.get(i));
			}
			for(int i = second; i < list.size(); i++) {
				temp.add(i, list.get(i));
			}
			
		} else {
//			
			
			temp.addAll(list.subList(second, first));
			Collections.reverse(temp);
			for(int i = 0; i < second; i++) {
				temp.add(i, list.get(i));
			}
			for(int i = first; i < list.size(); i++) {
				temp.add(i, list.get(i));
			}
			
		}
		
		
		
	
		return temp;
	}
	

}




//
//
//
//while(first == second) {
//	second = 1 + rand.nextInt(list.size());
//}
//ArrayList<City> temp = new ArrayList<City>();
//if(first < second) {
//	for(int i = 0; i < first; i ++) {
//		temp.add(list.get(i));
//	}
//	
//	temp.addAll(list.subList(first + 1, second - 1));
//	for(int i = second; i < list.size(); i++) {
//		temp.add(i, list.get(i));
//	}
//} else {
//	for(int i = 0; i < second; i ++) {
//		temp.add(list.get(i));
//	}
//	Collections.reverse(temp);
//
//	temp.addAll(list.subList(second + 1, first - 1));
//	for(int i = first; i < list.size(); i++) {
//		temp.add(i, list.get(i));
//	}
//}
//
//
//
