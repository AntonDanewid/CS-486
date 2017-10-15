package tspAnnealing;

public class main {

	public static void main(String[] args) {


		
		Parser parser = new Parser();
		Annealing an = new Annealing();
		an.startAnnealing(parser.parser("randTSP\\problem36"));

		
	}	

}
