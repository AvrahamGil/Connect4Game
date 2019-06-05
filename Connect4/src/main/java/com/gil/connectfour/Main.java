package com.gil.connectfour;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	private static int[][] board = new int[6][7];
	private static Scanner scanner = new Scanner(System.in);
	private static String winner;

	public static void main(String[] args) {
		System.out.println(gameFunction(board));
		System.out.println(Arrays.deepToString(board).replace("], ", "]\t\n"));
	}

	protected static String gameFunction(int[][] board) {
		System.out.println("Welcome to Connect4");
		int rows = board.length;
		int columns = board[0].length;
		int rounds = 0;
		System.out.println("Player 1, Your color is - > Red");
		int playerOneColor = 1;
		System.out.println("Player 2, Your color is - > Yellow");
		int playerTwoColor = 2;
		System.out.println("Players should choose a location between 0-6, the winner is the one that match 4 colors");
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				// Player 1 turn :
				System.out.println("Player 1 Choose location : (0-6)");
				int[] playerOneLocation = checkingInput(rows, playerOneColor);
				int playerOneLocationX = playerOneLocation[0];
				int playerOneLocationY = playerOneLocation[1];

				if (calculateMatches(playerOneLocationX, playerOneLocationY, playerOneColor)) {
					return winner = "The winner is: " + playerOneColor;
				}
				rounds++;
				if(rounds == 42) 
					return "############################## Draw ! ##############################";
				
				//Board
				System.out.println(Arrays.deepToString(board).replace("], ", "]\t\n"));
				
				// Player2 turn :
				System.out.println("Player 2 Choose location : (0-5)");
				int[] playerTwoLocation = checkingInput(rows, playerTwoColor);
				int playerTwoLocationX = playerTwoLocation[0];
				int playerTwoLocationY = playerTwoLocation[1];
				if (calculateMatches(playerTwoLocationX, playerTwoLocationY, playerTwoColor)) {
					return winner = "The winner is: " + playerTwoColor;
				}
				rounds++;
				//Board
				System.out.println(Arrays.deepToString(board).replace("], ", "]\t\n"));
				
				if(rounds == 42) 
					return "############################## Draw ! ##############################";
				
			}
		}
		return "Draw !";
	}

	private static int[] checkingInput(int rows, int playerColor) {
		int emptySlot = 0;
		int inputPlayer = 7;
		while (inputPlayer < 0 || inputPlayer > 6) {
			inputPlayer = scanner.nextInt();
			if (inputPlayer < 0 || inputPlayer > 6)
				System.out.println("Invalid input, Choose location between 0-6");
		}
		int[] array = new int[2];
		if (board[0][inputPlayer] == emptySlot) {
			for (int j = rows - 1; j >= 0; j--) {
				if (board[j][inputPlayer] == emptySlot) {
					board[j][inputPlayer] = playerColor;
					array[0] = inputPlayer;
					array[1] = j;
					return array;
				}
			}
		}
		System.out.println("Column is full,Please choose again.");
		return checkingInput(rows, playerColor);
	}

	private static boolean calculateMatches(int locationX, int locationY, int playerColor) {
		int matches = 0;
		int row = locationX;
		int column = locationY;
		int rows = board.length;
		int columns = board[0].length - 1;

		// Down1
		while (column < columns && column >= 0 && board[column][row] == playerColor && matches != 4) {
			matches++;
			column++;
		}
		if (matches == 4) {
			return true;
		} else {
			matches = 0;
			row = locationX;
			column = locationY;
		}

		// Left :
		while (row >= 0 && board[column][row] == playerColor && matches != 4) {
			matches++;
			row--;
		}
		if (matches == 4) {
			return true;
		} else {
			row = locationX + 1;
			column = locationY;
		}

		// Right :
		while (row <= rows && board[column][row] == playerColor && matches != 4) {
			matches++;
			row++;
		}
		if (matches == 4) {
			return true;
		} else {
			matches = 0;
			row = locationX;
			column = locationY;
		}

		// Right up
		while (row < rows && column < columns && board[column][row] == playerColor && matches != 4) {
			matches++;
			row++;
			column++;
		}
		if (matches == 4) {
			return true;
		} else {
			row = locationX - 1;
			column = locationY - 1;
		}

		// Left down
		while (row >= 0 && column >= 0 && board[column][row] == playerColor && matches != 4) {
			matches++;
			row--;
			column--;
		}
		if (matches == 4) {
			return true;
		} else {
			matches = 0;
			row = locationX;
			column = locationY;
		}

		// Left up:
		while (row >= 0 && column < columns && board[column][row] == playerColor && matches != 4) {
			matches++;
			row--;
			column++;
		}
		if (matches == 4) {
			return true;
		} else {
			row = locationX + 1;
			column = locationY - 1;
		}

		// Right down
		while (row < rows && column >= 0 && board[column][row] == playerColor && matches != 4) {
			matches++;
			row++;
			column--;
		}
		if (matches == 4)
			return true;
		
		return false;
	}
}
