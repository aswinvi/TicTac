package com.tic.tac;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

	private static final String PLAYER_O = "O";
	private static final String PLAYER_X = "X";
	
	

	public static void main(String[] args) {

		List<String> boardPositions = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
		
		Scanner scanner = new Scanner(System.in);

		playGame(boardPositions, "", 9, scanner);
	}

	static void playGame(List<String> boardPositions, String lastPlayed, int pendingMovesForPlayers, Scanner scanner) {
		
		 if (pendingMovesForPlayers <= 0 ) {
		        return;
		    }

		printTicTacBoard(boardPositions);

		if (lastPlayed.equals("") || PLAYER_O.equals(lastPlayed)) {
			getInputFromPlayer1(boardPositions, scanner);
			lastPlayed = PLAYER_X;
		} else {
			getInputFromPlayer2(boardPositions, scanner);
			lastPlayed = PLAYER_O;
		}
		
		playGame(boardPositions, lastPlayed, pendingMovesForPlayers - 1, scanner);

	}

	private static void printTicTacBoard(List<String> boardPositions) {

		System.out.printf("%n %s | %s | %s %n", boardPositions.get(0), boardPositions.get(1), boardPositions.get(2));
		System.out.println(" - + - + - ");
		System.out.printf(" %s | %s | %s %n", boardPositions.get(3), boardPositions.get(4), boardPositions.get(5));
		System.out.println(" - + - + - ");
		System.out.printf(" %s | %s | %s %n%n", boardPositions.get(6), boardPositions.get(7), boardPositions.get(8));

	}

	private static void getInputFromPlayer1(List<String> boardPositions, Scanner scanner) {
		setPositionIfValidInput(boardPositions, scanner.nextInt(), PLAYER_X);
	}

	private static void setPositionIfValidInput(List<String> boardPositions, int nextPosition, String player) {

		if (isValidInput(boardPositions, nextPosition)) {
			System.out.println("Invalid Move!! Please try another Position : ");
		} else {
			boardPositions.set(nextPosition - 1, player);
		}
	}

	private static boolean isValidInput(List<String> boardPositions, int nextPosition) {
		return PLAYER_X.equals(boardPositions.get(nextPosition - 1)) || PLAYER_O.equals(boardPositions.get(nextPosition - 1));
	}

	private static void getInputFromPlayer2(List<String> boardPositions, Scanner scanner) {
		System.out.println("Key in the position to Play : ");
		setPositionIfValidInput(boardPositions,scanner.nextInt(), PLAYER_O);
	}

}
