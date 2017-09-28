package soduku;

public class Solver {

	
	private int[][] board;
	private int nbr = 0;
	
	
	
	public void backSolve(int[][] board) {
		this.board = board;
		nbr = 0;
		boolean res = solveBack(board);
		if(!res) {
			System.out.println("no solution found");
		}
	}



	private boolean solveBack(int[][] board) {
		int[] unAssigned = new int[2];
		
		if(!findUnassigned(unAssigned)) {
			System.out.println("YES");
			for(int i = 0; i < 9; i ++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(board[i][j] + " ");
					
				}
				System.out.println();
			}
			return true; 
		}
		int row = unAssigned[0];
		int col = unAssigned[1];
		
		for(int i = 1; i < 10; i++) {
			if(checkValid(row, col, i)) {
				
				
				board[row][col] = i;
				nbr++;
				if(solveBack(board)) {
					return true;
				}
				board[row][col] = 0;
			}
		}
		return false;
	}
	
	
	
	private boolean checkValid(int row, int col, int num) {
		
		return checkRow(row, col, num)&&checkCol(row,col,num)&&checkSq(row, col, num);
	}
	
	
	private boolean checkRow(int row, int col, int num) {
		for(int i = 0; i < 9; i++) {
			if(board[row][i] == num) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkCol(int row, int col, int num) {
		for(int i = 0; i < 9; i++) {
			if(board[i][col] == num) {
				return false;
			}
		}
		return true;

	}
	
	private boolean checkSq(int row, int col, int num) {
		int xStart = row - row%3;
		int yStart = col - col%3;
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				if(board[x+xStart][y+yStart] == num) {
					return false;
				}
			}
		}
		return true;
		
	}

	public boolean findUnassigned(int[] list) {
		for(int i = 0; i <9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == 0) {
					list[0] = i;
					list[1] = j;
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void backForSolve() {
		
	}
	
	public void backForHeuSolve() {
		
	}
	
}
