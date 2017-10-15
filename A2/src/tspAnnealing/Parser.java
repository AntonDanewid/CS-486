package tspAnnealing;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Parser {

	
	/*Parse the city file and put it into a HashMap*/
//	public HashMap<City, ArrayList<City>> parser(String filepath) {

	public ArrayList<City> parser(String filepath) {

		String fullString = null;
		String s = null;
		String t = null;
		int n = 0;
		File newFile = new File(filepath);
		Scanner scanner = null;
		try {
			scanner = new Scanner(newFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.nextLine();
		ArrayList<City> result = new ArrayList<City>();
		while (scanner.hasNextLine()) {
			fullString = scanner.nextLine();

			String[] dataline = fullString.split(" ");

			int xCord = Integer.parseInt(dataline[1]);
			int yCord = Integer.parseInt(dataline[2]);

			//System.out.println(dataline[0] + " " + xCord + " " + yCord);

			result.add(new City(dataline[0], xCord, yCord));

			n = fullString.indexOf(" ");
			s = fullString.substring(0, n);
			t = fullString.substring(n + 1, fullString.length());
		}
		
		
//		}
//		HashMap<City, ArrayList<City>> graph = new HashMap<City, ArrayList<City>>();
//		City start = result.get(0);
//		for (City c : result) {
//			ArrayList<City> neighbours = new ArrayList<City>();
//			for (City q : result) {
//				if (!c.equals(q)) {
//					City toAdd = new City(q.getName(), q.getX(), q.getY());
//					toAdd.setHCost(c.getHCost());
//					//toAdd.setHCost(0);
//					neighbours.add(q);
//
//				}
//			
//			}
//			graph.put(c, neighbours);
//		}
//		//System.out.println(		graph.toString());
//		
		
		return result;
	}
}
