# TicTac
 TicTacToe Game
 
  
#Features
Player vs. Player Mode: Two players can play against each other.

Winner Detection: Automatically determines the winner or declares a draw.

Recursive Game Flow: Implements a clean and efficient recursive structure for gameplay.

Customizable Logging: Logs the game board and winner using a flexible logger.

#How to Play
Players take turns entering the number corresponding to the grid position where they want to place their marker ("X" or "O").

The game board updates after each move.

The game ends when:

One player aligns three of their markers in a row, column, or diagonal.

All spaces are filled, resulting in a draw.

#How It Works
The game uses a recursive method to handle player turns and check game status. Logging is integrated to display the board and status messages clearly.

# Prerequisites
Apache Maven
Java 17

# Install
mvn package

# Run
java -jar target/TicTacToe.jar

# Run tests
mvn test