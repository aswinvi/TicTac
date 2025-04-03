package com.tic.tac;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

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

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void printGameBoard() {
		mockBoardPositionsListValues();

		mockingScannerInput("5");

		TicTacToe.playGame(boardPositions);

		Mockito.verify(boardPositions, Mockito.times(1)).get(0);
		Mockito.verify(boardPositions, Mockito.times(1)).get(1);
		Mockito.verify(boardPositions, Mockito.times(1)).get(2);
		Mockito.verify(boardPositions, Mockito.times(1)).get(3);
		Mockito.verify(boardPositions, Mockito.times(1)).get(4);
		Mockito.verify(boardPositions, Mockito.times(1)).get(5);
		Mockito.verify(boardPositions, Mockito.times(1)).get(6);
		Mockito.verify(boardPositions, Mockito.times(1)).get(7);
		Mockito.verify(boardPositions, Mockito.times(1)).get(8);
	}

	private void mockingScannerInput(String mockInput) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(mockInput.getBytes());
		System.setIn(inputStream);
	}

	@Test
	void shouldDisplay_X_WhenPlayerOnePlays() {

		mockingScannerInput("6");

		List<String> boardPositions1 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

		TicTacToe.playGame(boardPositions1);

		assertEquals("X", boardPositions1.get(5));
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
