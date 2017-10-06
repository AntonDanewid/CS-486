package soduku;

import java.util.ArrayList;

import org.omg.Messaging.SyncScopeHelper;

public class SolverForward {

	private boolean[][][] check;

	private int[][] board;
	private int nbr = 0;
	private ArrayList<ArrayList<Integer>> variables;
	int level = 0;

	
	public void backForSolve(int[][] board) {
		this.board = board;
		check = new boolean[9][9][9];
		nbr = 0;
		variables = new ArrayList<ArrayList<Integer>>(81);
		ArrayList<Integer> numbers = new ArrayList<Integer>(9);
		for (int i = 1; i < 10; i++) {
			numbers.add(i);
		}

		for (int i = 0; i < 81; i++) {
			ArrayList<Integer> numberCopy = (ArrayList<Integer>) numbers.clone();
			variables.add(numberCopy);
		
		}
		doFirstCheck();
	

		boolean test = solve(variables);
//		if(!test) {
//			for (int i = 0; i < 9; i++) {
//				for (int j = 0; j < 9; j++) {
//					System.out.print(board[i][j] + " ");
//
//				}
//				System.out.println();
//		}
//		}
		System.out.println(test);
		System.out.println(nbr);

	}

	
	//Gör kopia på listorna så att concurrent issues undviks. 
	//Allt bör sedan vara klart.
	private boolean solve(ArrayList<ArrayList<Integer>> list) {
		
		
		if(nbr > 10000) {
			return false;
		}
		int[] unAssigned = new int[2];
		
		
		
		ArrayList<ArrayList<Integer>> backup = Copy(list);
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
		
		
		
		int row = unAssigned[0];
		int col = unAssigned[1];
		ArrayList<Integer> numbers = (ArrayList<Integer>) backup.get(row * 9 + col).clone();
		//System.out.println(row + " " + col + " " + numbers.toString());

		for (int i : numbers) {
			board[row][col] = i;
			remove(backup, row, col, i); //det ballar ur här
			nbr++;
			if(nbr == 23) {
				System.out.println();
			}
			
			level++;
			if (solve(backup)) {
				return true;
			}
			level--;
			if(col == 0 && row == 0) {
				System.out.println();
			}
			//System.out.println("We are at " + row + " " + col + " " + numbers.toString());
			board[row][col] = 0;
			backup =  Copy(list);
		
		}
		
		return false;
	}
	
	
//	public void addBack(ArrayList<ArrayList<Integer>> list, int row, int col, int num) {
//		for(int i = 0; i < 9; i ++) {
//			if(!list.get(row* 9 + i).contains((new Integer(num)))) {
//			list.get(row* 9 + i).add(new Integer(num));
//			}
//			if(!list.get(i * 9 + col).contains((new Integer(num)))) {
//			list.get(i * 9 + col).add(new Integer(num));
//			}
//		}
//		
//		int xStart = row - row % 3;
//		int yStart = col - col % 3;
//		for (int x = 0; x < 3; x++) {
//			for (int y = 0; y < 3; y++) {
//				if(!list.get((xStart + x) * 9 + yStart + y).contains((new Integer(num)))) {
//				list.get((xStart + x) * 9 + yStart + y).add(new Integer(num));
//				}
//			}
//		}
//		
//	}
	
	
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
					//variables.get(i+j).clear();
				}
			}
		}
	}
	
	private ArrayList<ArrayList<Integer>> Copy(ArrayList<ArrayList<Integer>> D) {
		ArrayList<ArrayList<Integer>> Copy = new ArrayList<ArrayList<Integer>>(
				D.size());

		for (ArrayList<Integer> domain : D) {
			Copy.add(new ArrayList<Integer>(domain));
		}

		return Copy;
	}

}
