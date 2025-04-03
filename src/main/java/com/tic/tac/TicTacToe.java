package com.tic.tac;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {

		List<String> boardPositions = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		playGame(boardPositions);
	}

	static void playGame(List<String> boardPositions) {

		Scanner scanner = new Scanner(System.in);

		printTicTacBoard(boardPositions);

		getInputFromPlayer1(boardPositions, scanner);

	}

	private static void printTicTacBoard(List<String> boardPositions) {

		System.out.printf("%n %s | %s | %s %n", boardPositions.get(0), boardPositions.get(1), boardPositions.get(2));
		System.out.println(" - + - + - ");
		System.out.printf(" %s | %s | %s %n", boardPositions.get(3), boardPositions.get(4), boardPositions.get(5));
		System.out.println(" - + - + - ");
		System.out.printf(" %s | %s | %s %n%n", boardPositions.get(6), boardPositions.get(7), boardPositions.get(8));

	}

	private static void getInputFromPlayer1(List<String> boardPositions, Scanner scanner) {

		System.out.println("Key in the position to Play : ");

		int nextPosition = scanner.nextInt();
		boardPositions.set(nextPosition - 1, "X");

	}

}
