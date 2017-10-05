package soduku;

import java.util.ArrayList;

public class HeuristicSolve {

	private boolean[][][] check;

	// private ArrayList<Integer>[][] lol;
	private int[][] board;
	private int nbr = 0;
	private ArrayList<ArrayList<Integer>> variables;

	public void heuristicSolve(int[][] board) {
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
		//System.out.println("arraylist " + variables);
		solve(variables);
		System.out.println(nbr);

	}

	
	//Gör kopia på listorna så att concurrent issues undviks. 
	//Allt bör sedan vara klart.
	private boolean solve(ArrayList<ArrayList<Integer>> list) {
		int[] unAssigned = new int[2];
		
		ArrayList<ArrayList<Integer>> backup = (ArrayList<ArrayList<Integer>>) list.clone();
		
		if (!findUnassigned(unAssigned)) {
			//System.out.println("YES");
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j] + " ");

				}
				System.out.println();
			}
			return true;
		}
		
		
		//This is where the magic happens
		int row = unAssigned[0];
		int col = unAssigned[1];
		int variable = findCell(backup);
		
		ArrayList<Integer> numbers = (ArrayList<Integer>) list.get(variable).clone();
		//System.out.println(row + " " + col + " " + numbers.toString());
		for (int i : numbers) {
			board[row][col] = i;
			remove(backup, row, col, i); //det ballar ur här
			nbr++;

			
			if (solve(backup)) {
				return true;
			}
			//System.out.println("We are at " + row + " " + col + " " + numbers.toString());
			board[row][col] = 0;
			addBack(backup, row, col, i);
		}
		
		return false;
	}
	
	
	public void addBack(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
		for(int i = 0; i < 9; i ++) {
			list.get(row* 9 + i).add(new Integer(num));
			list.get(i * 9 + col).add(new Integer(num));
		}
		
		int xStart = row - row % 3;
		int yStart = col - col % 3;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				list.get((xStart + x) * 9 + yStart + y).add(new Integer(num));
			}
		}
		
	}
	
	
	private void remove(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
		removeFromSquare(list, row, col, num);
		removeFromLineRow(list, row, col, num);
	}

	private void removeFromLineRow(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
		for(int i = 0; i < 9; i ++) {
			list.get(row* 9 + i).remove(new Integer(num));
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

	


	
	
	public int findCell(ArrayList<ArrayList<Integer>> list) {
		int max = Integer.MAX_VALUE;
		int variable = 0;
		for(int i = 0; i < list.size(); i++) {
			ArrayList<Integer> l = list.get(i);
			if(l.size() != 0) {
				if(l.size() < i) {
					max = l.size();
					variable = i;
					 
				}
			}
		}
		
		return variable;
		
	}

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


	private void doFirstCheck() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != 0) {
					remove(variables, i, j, board[i][j]);
				}
			}
		}
	}
	
	
	
	
	
	
	
}
