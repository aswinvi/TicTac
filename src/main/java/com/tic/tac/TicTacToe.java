package com.tic.tac;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.tic.tac.log.LoggingService;

public class TicTacToe {

	private static final String IT_S_A_DRAW = "It's a draw!";
	private static final String PLAYER_TWO_IS_THE_WINNER = "Player Two is the Winner";
	private static final String PLAYER_ONE_IS_THE_WINNER = "Player One is the Winner";
	private static final String GAMER_OVER = "Game Over !! and the Winner is : ";
	private static final String KEY_IN_THE_POSITION_TO_PLAY = "Key in the position to Play : ";
	private static final String INVALID_MOVE_PLEASE_TRY_ANOTHER_POSITION = "Invalid Move!! Please try another Position : ";
	private static final String PLAYER_O = "O";
	private static final String PLAYER_X = "X";

	boolean gameOver = false;

	private static final Logger logger = LoggingService.getLogger();

	public static void main(String[] args) {

		List<String> boardPositions = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		Scanner scanner = new Scanner(System.in);

		TicTacToe game = new TicTacToe();

		game.playGame(boardPositions, "", 9, scanner);
	}

	public void playGame(List<String> boardPositions, String lastPlayed, int pendingMovesForPlayers, Scanner scanner) {
		
		if (listHasValue(boardPositions)) {

			if (conditionToStopGame(pendingMovesForPlayers)) {
				scanner.close();
				return;
			}

			printTicTacBoard(boardPositions);

			lastPlayed = getInputFromNextPlayer(boardPositions, lastPlayed, scanner);

			checkGameState(boardPositions);

			playGame(boardPositions, lastPlayed, pendingMovesForPlayers - 1, scanner);

		} else {
			logger.info("Game Configuration Failure !!");
		}

	}

	private boolean conditionToStopGame(int pendingMovesForPlayers) {
		return pendingMovesForPlayers <= 0 || gameOver;
	}

	private boolean listHasValue(List<String> boardPositions) {
		return null!=boardPositions&& !boardPositions.isEmpty() && boardPositions.size()==9;
	}

	public void checkGameState(List<String> boardPositions) {

		checkRowsForStrike(boardPositions);

		checkColumnForStrike(boardPositions);

		checkDiagonallyForStrike(boardPositions);

		checkForDraw(boardPositions);
	}

	private void checkForDraw(List<String> boardPositions) {
		if (matchForAnyNumber(boardPositions)) {
			logger.info(IT_S_A_DRAW);
		}
	}

	private boolean matchForAnyNumber(List<String> boardPositions) {
		return boardPositions.stream().noneMatch(pos -> pos.matches("\\d"));
	}

	private void checkDiagonallyForStrike(List<String> boardPositions) {
		if (isDiagonallyMatchedFromRightCorner(boardPositions)) {
			gameOver = true;
			String winner = populatesMessageToPrint(boardPositions, 0);
			logger.info(String.format("%s %s", GAMER_OVER, winner));
		}

		if (isDiagonallyMatchedFromLeftCorner(boardPositions)) {
			gameOver = true;
			String winner = populatesMessageToPrint(boardPositions, 2);
			logger.info(String.format("%s %s", GAMER_OVER, winner));
		}
	}

	private String populatesMessageToPrint(List<String> boardPositions, int positionToCheck) {
		return PLAYER_X.equals(boardPositions.get(positionToCheck)) ? PLAYER_ONE_IS_THE_WINNER
				: PLAYER_TWO_IS_THE_WINNER;
	}

	private boolean isDiagonallyMatchedFromLeftCorner(List<String> boardPositions) {
		return boardPositions.get(2).equals(boardPositions.get(4))
				&& boardPositions.get(4).equals(boardPositions.get(6));
	}

	private boolean isDiagonallyMatchedFromRightCorner(List<String> boardPositions) {
		return boardPositions.get(0).equals(boardPositions.get(4))
				&& boardPositions.get(4).equals(boardPositions.get(8));
	}

	private void checkColumnForStrike(List<String> boardPositions) {
		for (int index = 0; index < 3; index++) {
			if (isVerticallyMatched(boardPositions, index)) {
				gameOver = true;
				String winner = populatesMessageToPrint(boardPositions, index);
				logger.info(String.format("%s %s", GAMER_OVER, winner));
			}
		}
	}

	private boolean isVerticallyMatched(List<String> boardPositions, int index) {
		return boardPositions.get(index).equals(boardPositions.get(index + 3))
				&& boardPositions.get(index + 3).equals(boardPositions.get(index + 6));
	}

	private void checkRowsForStrike(List<String> boardPositions) {
		for (int index = 0; index < 9; index += 3) {
			if (isHorizontallyMatched(boardPositions, index)) {
				gameOver = true;
				String winner = populatesMessageToPrint(boardPositions, index);
				logger.info(String.format("%s %s", GAMER_OVER, winner));
			}
		}
	}

	private boolean isHorizontallyMatched(List<String> boardPositions, int index) {
		return boardPositions.get(index).equals(boardPositions.get(index + 1))
				&& boardPositions.get(index + 1).equals(boardPositions.get(index + 2));
	}

	private String getInputFromNextPlayer(List<String> boardPositions, String lastPlayed, Scanner scanner) {
		if (isTurnForPlayerX(lastPlayed)) {
			getInputFromPlayer1(boardPositions, scanner);
			lastPlayed = PLAYER_X;
		} else {
			getInputFromPlayer2(boardPositions, scanner);
			lastPlayed = PLAYER_O;
		}
		return lastPlayed;
	}

	private boolean isTurnForPlayerX(String lastPlayed) {
		return lastPlayed.equals("") || PLAYER_O.equals(lastPlayed);
	}

	private void printTicTacBoard(List<String> boardPositions) {

		logger.info(
				String.format("%n %s | %s | %s", boardPositions.get(0), boardPositions.get(1), boardPositions.get(2)));
		logger.info(" - + - + - ");
		logger.info(
				String.format(" %s | %s | %s", boardPositions.get(3), boardPositions.get(4), boardPositions.get(5)));
		logger.info(" - + - + - ");
		logger.info(
				String.format(" %s | %s | %s%n", boardPositions.get(6), boardPositions.get(7), boardPositions.get(8)));

	}

	private void getInputFromPlayer1(List<String> boardPositions, Scanner scanner) {
		logger.info(KEY_IN_THE_POSITION_TO_PLAY);
		setPositionIfValidInput(boardPositions, scanner.nextInt(), PLAYER_X);
	}

	private void setPositionIfValidInput(List<String> boardPositions, int nextPosition, String player) {

		if (isValidInput(boardPositions, nextPosition)) {
			logger.info(INVALID_MOVE_PLEASE_TRY_ANOTHER_POSITION);
		} else {
			boardPositions.set(nextPosition - 1, player);
		}
	}

	private boolean isValidInput(List<String> boardPositions, int nextPosition) {
		return PLAYER_X.equals(boardPositions.get(nextPosition - 1))
				|| PLAYER_O.equals(boardPositions.get(nextPosition - 1));
	}

	private void getInputFromPlayer2(List<String> boardPositions, Scanner scanner) {
		logger.info(KEY_IN_THE_POSITION_TO_PLAY);
		setPositionIfValidInput(boardPositions, scanner.nextInt(), PLAYER_O);
	}

}
