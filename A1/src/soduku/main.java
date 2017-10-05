package soduku;

public class main {

	public static void main(String[] args) {
		Solver solver = new Solver();
		SolverForward fSolve = new SolverForward();
		HeuristicSolve hSolve = new HeuristicSolve();
		Parser parser = new Parser();

		System.out.println("Backwards search results");
		solver.backSolve(parser.parse("problems\\3\\6.sd"));
		System.out.println();
		System.out.println();

		 
		
		System.out.println("Forward search results");
		fSolve.backForSolve(parser.parse("problems\\3\\6.sd"));
		System.out.println();
		System.out.println();

		
		System.out.println("Heuristic search result");
		hSolve.heuristicSolve(parser.parse("problems\\3\\6.sd"));
		
	}
	
}
