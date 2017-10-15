package soduku;

import java.util.ArrayList;

public class HeuristicSolve {

	private boolean[][][] check;

	private int[][] board;
	private int nbr = 0;
	private ArrayList<ArrayList<Integer>> variables;

	
	/*Setup*/
	public int heuristicSolve(int[][] board) {
		this.board = board;
		check = new boolean[9][9][9];
		nbr = 0;
		variables = new ArrayList<ArrayList<Integer>>(81);
		ArrayList<Integer> numbers = new ArrayList<Integer>(9);
		for (int i = 1; i < 10; i++) {
			numbers.add(i);
		}

		

		for (int i = 0; i < 81; i++) {
			variables.add(new ArrayList<Integer>(numbers));
		}

	
		doFirstCheck();

		boolean test = solve(variables);
		System.out.println(test);
		System.out.println(nbr);
		if(!test) {
			return 0;
		}
		return nbr;

	}


	
	//The recursive solving method
	private boolean solve(ArrayList<ArrayList<Integer>> list) {
		if (nbr > 10000) {
			return false;
		}
		int[] unAssigned = new int[2];
		int[] test = new int[2];

		ArrayList<ArrayList<Integer>> backup = Copy(list);

		if (!findUnassigned(unAssigned)) {

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
//					

				}
				System.out.println();
			}
			return true;
		}

		int variable = findCell(backup);
		if (variable == -1) {
		}

		int col = variable % 9;
		int row = (variable / 9) % 9;
		
		ArrayList<Integer> numbers = (ArrayList<Integer>) backup.get(row * 9 + col).clone();
		for (int i : numbers) {
			board[row][col] = i;
			remove(backup, row, col, i);
			nbr++;

			if (solve(backup)) {
				return true;
			}
			
			board[row][col] = 0;

			backup = Copy(list);

		}

		return false;
	}

	public void addBack(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (!list.get(row * 9 + i).contains(num)) {
				list.get(row * 9 + i).add(new Integer(num));
			}
			if (!list.get(i * 9 + col).contains(num)) {
				list.get(i * 9 + col).add(new Integer(num));
			}
		}

		int xStart = row - row % 3;
		int yStart = col - col % 3;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (!list.get((xStart + x) * 9 + yStart + y).contains(num)) {
					list.get((xStart + x) * 9 + yStart + y).add(new Integer(num));
				}
			}
		}

	}

	private void remove(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
		removeFromSquare(list, row, col, num);
		removeFromLineRow(list, row, col, num);
	}

	private void removeFromLineRow(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			list.get(row * 9 + i).remove(new Integer(num));
			list.get(i * 9 + col).remove(new Integer(num));
		}
	}

	private void removeFromSquare(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
		int xStart = row - row % 3;
		int yStart = col - col % 3;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				list.get((xStart + x) * 9 + yStart + y).remove(new Integer(num));
			}
		}
	}

	
	//Finds the most constrained variable
	public int findCell(ArrayList<ArrayList<Integer>> list) {
		int max = Integer.MAX_VALUE;
		int variable = 0;
		int x;
		int y;
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			ArrayList<Integer> l = list.get(i);

			if (l.size() > 0) {
				y = i % 9;
				x = (i / 9) % 9;
				// System.out.println("Index is:" + i + " x and y is " + x + " "
				// + y);
				if (l.size() < max) {
					if (board[x][y] == 0) {
						if (l.size() != variable) {
							max = l.size();
							variable = i;

							// Two competeing choices
						} else {
							//checkValue(list, variable);
						}
					}
				}
			}
		}
//		if (variable == -1) {
//			System.out.println("YES " + list.toString());
//		}

		return variable;

	}

	
	
	//The start of what would have become  least constrained value etc
//	public void checkValue(ArrayList<ArrayList<Integer>> list, int variable) {
//		int y = variable % 9;
//		ArrayList<Integer> temp = list.get(variable);
//		int x = (variable / 9) % 9;
//		for (Integer a : temp) {
//			for (int i = 0; i < 9; i++) {
//				list.get(x * 9 + i).remove(new Integer(num));
//				list.get(i * 9 + y).remove(new Integer(num));
//			}
//		}
//
//	}

	//Finds unassigned cell
	public boolean findUnassigned(int[] list) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
					list[0] = i;
					list[1] = j;
					return true;
				}
			}
		}
		return false;
	}

	
	//Initial check, removes conflicting variables from the list. 
	private void doFirstCheck() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != 0) {
					remove(variables, i, j, board[i][j]);
					// variables.get(i+j).clear();
				}
			}
		}
	}

	
	//Copys an arrayList and its contents
	private ArrayList<ArrayList<Integer>> Copy(ArrayList<ArrayList<Integer>> D) {
		ArrayList<ArrayList<Integer>> Copy = new ArrayList<ArrayList<Integer>>(D.size());

		for (ArrayList<Integer> domain : D) {
			Copy.add(new ArrayList<Integer>(domain));
		}

		return Copy;
	}

	

	private boolean checkRow(int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num) {
				return false;
			}
		}
		return true;
	}

	private boolean checkCol(int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == num) {
				return false;
			}
		}
		return true;

	}

	
	private boolean checkSq(int row, int col, int num) {
		int xStart = row - row % 3;
		int yStart = col - col % 3;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (board[x + xStart][y + yStart] == num) {
					return false;
				}
			}
		}
		return true;

	}

}
