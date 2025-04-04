package com.tic.tac;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TicTacToeTest {

	@InjectMocks
	TicTacToe ticTacToe = new TicTacToe();

	@Mock
	List<String> boardPositions;

	List<String> boardPositionsList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void printGameBoard() {
		mockBoardPositionsListValues();
		Scanner scanner = new Scanner("5\n3\n7\n9\n8\n6\n4\n2\n1\n");

		ticTacToe.playGame(boardPositions, "", 9, scanner);

		scanner.close();

		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(0);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(1);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(2);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(3);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(4);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(5);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(6);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(7);
		Mockito.verify(boardPositions, Mockito.atLeast(9)).get(8);
	}


	@Test
	void shouldDisplay_X_WhenPlayerOnePlays() {

		Scanner scanner = new Scanner("5\n3\n");

		ticTacToe.playGame(boardPositionsList, "", 1, scanner);

		scanner.close();

		assertEquals("X", boardPositionsList.get(4));
	}

	@Test
	void shouldDisplay_O_WhenPlayerTwoPlays() {

		Scanner scanner = new Scanner("5\n3\n");

		ticTacToe.playGame(boardPositionsList, "", 1, scanner);

		scanner.close();

		assertEquals("X", boardPositionsList.get(4));
	}

	@Test
	void shouldNotUpdateXInPositionIfInputNotValid() {

		Scanner scanner = new Scanner("2\n3\n");

		List<String> positionList = Arrays.asList("1", "O", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList, "", 1, scanner);

		scanner.close();

		assertEquals("O", positionList.get(1));
	}

	@Test
	void shouldNotUpdateOInPositionIfInputNotValid() {

		Scanner scanner = new Scanner("2\n3\n");

		List<String> positionList = Arrays.asList("1", "X", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList, "X", 1, scanner);

		scanner.close();

		assertEquals("X", positionList.get(1));
	}

	@Test
	void shouldallowPlayer2OnlyIfLastPlayedIsPlayer1() {

		Scanner scanner = new Scanner("2\n3\n");

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList, "X", 2, scanner);

		scanner.close();

		assertEquals("O", positionList.get(1));
	}

	@Test
	void shouldallowPlayer1OnlyIfHePlaysFirstOrLastPlayerIsNotHim() {

		Scanner scanner = new Scanner("2\n3\n");

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList, "O", 2, scanner);

		scanner.close();

		assertEquals("X", positionList.get(1));
	}

	@Test
	void shouldContinueTheGameTillThereIsNoNextMove() {

		Scanner scanner = new Scanner("5\n3\n1\n9\n6\n4\n7\n8\n2\n");

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList, "O", 9, scanner);

		scanner.close();

		assertEquals("X", positionList.get(0));
		assertEquals("X", positionList.get(1));
		assertEquals("O", positionList.get(2));
		assertEquals("O", positionList.get(3));
		assertEquals("X", positionList.get(4));
		assertEquals("X", positionList.get(5));
		assertEquals("X", positionList.get(6));
		assertEquals("O", positionList.get(7));
		assertEquals("O", positionList.get(8));

	}

	@Test
	void closeTheGameWhenPlayerStrikesConsicutiveRows() {

		Scanner scanner = new Scanner("5\n3\n7\n9\n6\n8\n4\n2\n1\n");

		List<String> positionList2 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList2, "O", 9, scanner);

		scanner.close();

		assertTrue(ticTacToe.gameOver);
		assertEquals("O", positionList2.get(2));
		assertEquals("X", positionList2.get(3));
		assertEquals("X", positionList2.get(4));
		assertEquals("X", positionList2.get(5));
		assertEquals("X", positionList2.get(6));
		assertEquals("O", positionList2.get(7));
		assertEquals("O", positionList2.get(8));
		assertEquals("1", positionList2.get(0));
		assertEquals("2", positionList2.get(1));
	}

	@Test
	void closeTheGameWhenPlayerStrikesConsicutiveColumn() {

		Scanner scanner = new Scanner("5\n3\n7\n6\n8\n9\n4\n2\n1\n");

		List<String> positionList1 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList1, "O", 9, scanner);

		scanner.close();

		assertTrue(ticTacToe.gameOver);
		assertEquals("O", positionList1.get(2));
		assertEquals("4", positionList1.get(3));
		assertEquals("X", positionList1.get(4));
		assertEquals("O", positionList1.get(5));
		assertEquals("X", positionList1.get(6));
		assertEquals("X", positionList1.get(7));
		assertEquals("O", positionList1.get(8));
		assertEquals("1", positionList1.get(0));
		assertEquals("2", positionList1.get(1));
	}

	@Test
	void closeTheGameWhenPlayerStrikesConsicutivePositionsDiagonallyFromRight() {

		Scanner scanner = new Scanner("1\n3\n5\n6\n9\n7\n4\n2\n8\n");

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList, "O", 9, scanner);

		scanner.close();

		assertTrue(ticTacToe.gameOver);
		assertEquals("O", positionList.get(2));
		assertEquals("4", positionList.get(3));
		assertEquals("X", positionList.get(4));
		assertEquals("O", positionList.get(5));
		assertEquals("7", positionList.get(6));
		assertEquals("8", positionList.get(7));
		assertEquals("X", positionList.get(8));
		assertEquals("X", positionList.get(0));
		assertEquals("2", positionList.get(1));
	}
	
	@Test
	void closeTheGameWhenPlayerStrikesConsicutivePositionsDiagonallyFromLeft() {

		Scanner scanner = new Scanner("1\n3\n6\n5\n9\n7\n4\n2\n8\n");

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		ticTacToe.playGame(positionList, "O", 9, scanner);

		scanner.close();

		assertTrue(ticTacToe.gameOver);
		assertEquals("O", positionList.get(2));
		assertEquals("4", positionList.get(3));
		assertEquals("O", positionList.get(4));
		assertEquals("X", positionList.get(5));
		assertEquals("O", positionList.get(6));
		assertEquals("8", positionList.get(7));
		assertEquals("X", positionList.get(8));
		assertEquals("X", positionList.get(0));
		assertEquals("2", positionList.get(1));
	}

	private void mockBoardPositionsListValues() {
		Mockito.when(boardPositions.get(0)).thenReturn("1");
		Mockito.when(boardPositions.get(1)).thenReturn("2");
		Mockito.when(boardPositions.get(2)).thenReturn("3");
		Mockito.when(boardPositions.get(3)).thenReturn("4");
		Mockito.when(boardPositions.get(4)).thenReturn("5");
		Mockito.when(boardPositions.get(5)).thenReturn("6");
		Mockito.when(boardPositions.get(6)).thenReturn("7");
		Mockito.when(boardPositions.get(7)).thenReturn("8");
		Mockito.when(boardPositions.get(8)).thenReturn("9");
	}

}
