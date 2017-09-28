package soduku;

public class main {

	public static void main(String[] args) {
		Solver solver = new Solver();
		Parser parser = new Parser();
		solver.backSolve(parser.parse("problems\\68\\6.sd"));
		
	}
	
}
