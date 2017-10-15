package soduku;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		Solver solver = new Solver();
		SolverForward fSolve = new SolverForward();
		HeuristicSolve hSolve = new HeuristicSolve();
		Parser parser = new Parser();
//
		ArrayList<Double> back = new ArrayList<Double>();
		ArrayList<Double> forw = new ArrayList<Double>();
		ArrayList<Double> heu = new ArrayList<Double>();

		
		for(int i = 1; i < 71; i++) {
			double nbr = 0;
			double divider = 0;
			for(int j = 1; j < 9; j++) {
				System.out.println("Backwards search results");
				int a = solver.backSolve(parser.parse("problems\\" + i + "\\" + j + ".sd"));
				if(a != 0 ) {
					divider++;
					nbr = nbr+ a;
				}
				System.out.println();
				System.out.println();
			}
			back.add(nbr/divider);
		}
		
		 

		for(int i = 1; i < 71; i++) {
			double nbr = 0;
			double divider = 0;
			for(int j = 1; j < 9; j++) {
				System.out.println("Forward search results");
				
				
				int a = fSolve.backForSolve(parser.parse("problems\\" + i + "\\" + j + ".sd"));
				if(a != 0 ) {
					divider++;
					nbr = nbr+ a;
				}
				System.out.println();
				System.out.println();
			}
			forw.add(nbr/divider);
		}
		
		
		
		for(int i = 1; i < 71; i++) {
			double nbr = 0;
			double divider = 0;
			for(int j = 1; j < 9; j++) {
				System.out.println("Forward search results");
				int a = hSolve.heuristicSolve(parser.parse("problems\\" + i + "\\" + j + ".sd"));
				if(a != 0 ) {
					divider++;
					nbr = nbr+ a;
				}
				System.out.println();
				System.out.println();
			}
			heu.add(nbr/divider);
		}
	
		
		System.out.println(back);
		System.out.println(forw);
		System.out.println(heu);
	}
	
	
}
