
public class main {

	
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		A_star algo = new A_star();
		algo.Pathfind(parser.parser("randTSP\\11\\instance_10.txt"));

	}
}
