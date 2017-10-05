package soduku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Parser {

	public int[][] parse(String filepath) {
	String fullString = null;
	String s = null;
	String t = null;
	int n = 0;
	File newFile = new File(filepath);
	Scanner scanner = null;
	try {
		scanner = new Scanner(newFile);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	int[][] board = new int[9][9];
	int x = 0;
	while (scanner.hasNextLine()) {
		fullString = scanner.nextLine();
		if(fullString.trim().isEmpty()) {
			break;
		}
		String[] dataline = fullString.split(" ");
		for(int i = 0; i <9; i ++) {
			board[x][i] = 	Integer.parseInt(dataline[i]);
		}
		x++;

	}
	
//	for(int i = 0; i < 9; i++) {
//		for(int j = 0; j < 9; j++) {
//			System.out.print(board[i][j] + " ");
//		}
//		System.out.println();
//	}
	return board;
	
	}
	
	
	
	
}
