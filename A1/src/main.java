import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		A_star algo = new A_star();
		int totNodes = 0;
		ArrayList<Double> list = new ArrayList<Double>();
		for (int i = 1; i < 11; i++) {
			for (int a = 1; a < 11; a++) {
				totNodes += algo.Pathfind(parser.parser("randTSP\\" + i + "\\instance_" + a + ".txt"));

			}
			double nodeAverage = totNodes / 10;
			list.add(nodeAverage);
			totNodes = 0;

		}
		// algo.Pathfind(parser.parser("randTSP\\1\\instance_10.txt"));

		System.out.println(list);

	}
}
