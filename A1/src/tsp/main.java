package tsp;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		long timeStart = System.currentTimeMillis();
		Parser parser = new Parser();
		A_star algo = new A_star();
		int totNodes = 0;
		ArrayList<Double> list = new ArrayList<Double>();
//		for (int i = 1; i < 11; i++) {
//			for (int a = 1; a < 11; a++) {
//				totNodes += algo.Pathfind(parser.parser("randTSP\\" + i + "\\instance_" + a + ".txt"));
//
//			}
//			double nodeAverage = totNodes / 10;
//			list.add(nodeAverage);
//			totNodes = 0;
//
	//	}
		long timeEnd = System.currentTimeMillis();
		 algo.Pathfind(parser.parser("randTSP\\10\\instance_10.txt"));
		
		System.out.println(list);
		long runningTime = timeEnd - timeStart;
		System.out.println("Running time was ");
		System.out.println(runningTime);
	}
}
