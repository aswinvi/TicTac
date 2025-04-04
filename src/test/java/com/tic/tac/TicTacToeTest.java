package com.tic.tac;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
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
	TicTacToe ticTac = new TicTacToe();

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
		mockingScannerInput("5\n3\n7\n9\n8\n6\n4\n2\n1\n");
		
		Scanner scanner = new Scanner(System.in);
		
		TicTacToe.playGame(boardPositions, "", 9, scanner);

		Mockito.verify(boardPositions, Mockito.times(20)).get(0);
		Mockito.verify(boardPositions, Mockito.times(20)).get(1);
		Mockito.verify(boardPositions, Mockito.times(11)).get(2);
		Mockito.verify(boardPositions, Mockito.times(20)).get(3);
		Mockito.verify(boardPositions, Mockito.times(20)).get(4);
		Mockito.verify(boardPositions, Mockito.times(11)).get(5);
		Mockito.verify(boardPositions, Mockito.times(20)).get(6);
		Mockito.verify(boardPositions, Mockito.times(20)).get(7);
		Mockito.verify(boardPositions, Mockito.times(11)).get(8);
	}

	private void mockingScannerInput(String mockInput) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(mockInput.getBytes());
		System.setIn(inputStream);
	}

	@Test
	void shouldDisplay_X_WhenPlayerOnePlays() {

		mockingScannerInput("5\n3\n");
		
		Scanner scanner = new Scanner(System.in);

		TicTacToe.playGame(boardPositionsList, "", 1, scanner);

		assertEquals("X", boardPositionsList.get(4));
	}

	@Test
	void shouldDisplay_O_WhenPlayerTwoPlays() {

		mockingScannerInput("5\n3\n");
		Scanner scanner = new Scanner(System.in);

		TicTacToe.playGame(boardPositionsList, "", 1, scanner);

		assertEquals("X", boardPositionsList.get(4));
	}

	@Test
	void shouldNotUpdateXInPositionIfInputNotValid() {

		mockingScannerInput("2\n3\n");
		Scanner scanner = new Scanner(System.in);

		List<String> positionList = Arrays.asList("1", "O", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(positionList, "", 1, scanner);

		assertEquals("O", positionList.get(1));
	}

	@Test
	void shouldNotUpdateOInPositionIfInputNotValid() {

		mockingScannerInput("2\n3\n");

		Scanner scanner = new Scanner(System.in);
		
		List<String> positionList = Arrays.asList("1", "X", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(positionList, "X", 1, scanner);

		assertEquals("X", positionList.get(1));
	}

	@Test
	void shouldallowPlayer2OnlyIfLastPlayedIsPlayer1() {

		mockingScannerInput("2\n3\n");
		Scanner scanner = new Scanner(System.in);

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(positionList, "X", 1, scanner);

		assertEquals("3", positionList.get(2));
	}

	@Test
	void shouldallowPlayer1OnlyIfHePlaysFirstOrLastPlayerIsNotHim() {

		mockingScannerInput("2\n3\n");
		Scanner scanner = new Scanner(System.in);

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(positionList, "O", 1, scanner);

		assertEquals("3", positionList.get(2));
	}
	
	@Test
	void shouldContinueTheGameTillThereIsNoNextMove() {

		mockingScannerInput("5\n3\n7\n9\n8\n6\n4\n2\n1\n");
		Scanner scanner = new Scanner(System.in);

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(positionList, "O", 9, scanner);

		assertEquals("X", positionList.get(0));
		assertEquals("O", positionList.get(1));
		assertEquals("O", positionList.get(2));
		assertEquals("X", positionList.get(3));
		assertEquals("X", positionList.get(4));
		assertEquals("O", positionList.get(5));
		assertEquals("X", positionList.get(6));
		assertEquals("X", positionList.get(7));
		assertEquals("O", positionList.get(8));
		
	}
	
	@Test
	void closeTheGameWhenPlayerStrikesConsicutiveRows() {

		mockingScannerInput("5\n3\n7\n9\n6\n8\n4\n2\n1\n");
		Scanner scanner = new Scanner(System.in);

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(positionList, "O", 9, scanner);

		assertTrue(TicTacToe.gameOver);
		assertEquals("O", positionList.get(2));
		assertEquals("X", positionList.get(3));
		assertEquals("X", positionList.get(4));
		assertEquals("X", positionList.get(5));
		assertEquals("X", positionList.get(6));
		assertEquals("O", positionList.get(7));
		assertEquals("O", positionList.get(8));
		assertEquals("1", positionList.get(0));
		assertEquals("2", positionList.get(1));
	}

	@Test
	void closeTheGameWhenPlayerStrikesConsicutiveColumn() {

		mockingScannerInput("5\n3\n7\n6\n8\n9\n4\n2\n1\n");
		Scanner scanner = new Scanner(System.in);

		List<String> positionList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(positionList, "O", 9, scanner);

		assertTrue(TicTacToe.gameOver);
		assertEquals("O", positionList.get(2));
		assertEquals("4", positionList.get(3));
		assertEquals("X", positionList.get(4));
		assertEquals("X", positionList.get(5));
		assertEquals("O", positionList.get(6));
		assertEquals("X", positionList.get(7));
		assertEquals("X", positionList.get(8));
		assertEquals("1", positionList.get(0));
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
