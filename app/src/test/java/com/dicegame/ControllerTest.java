package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ControllerTest {
  @Test
  public void setUpGameShouldAddPlayersToGameForGivenPlayers() {
    Console consoleMock = Mockito.mock(Console.class);
    Mockito.when(consoleMock.promptForPlayerNames())
        .thenReturn(new String[] { "Alice", "Eva" });

    DiceCup diceCupMock = Mockito.mock(DiceCup.class);
    Game game = new Game(diceCupMock);
    Controller controller = new Controller(game, consoleMock);
    controller.setUpGame();
    Mockito.verify(consoleMock).promptForPlayerNames();

    List<Player> players = game.getPlayers();

    assertEquals("Alice", players.get(0).getName(), "Player 1 name should be Alice");
    assertEquals("Eva", players.get(1).getName(), "Player 2 name should be Eva");
  }

  @Test
  public void setUpGameShouldThrowExceptionForMoreThanTwoPlayers() {
    Console consoleMock = Mockito.mock(Console.class);
    DiceCup diceCupMock = Mockito.mock(DiceCup.class);
    Mockito.when(consoleMock.promptForPlayerNames())
        .thenReturn(new String[] { "Alice", "Eva", "Bob" });

    Game game = new Game(diceCupMock);
    Controller controller = new Controller(game, consoleMock);

    Exception exception = assertThrows(IllegalStateException.class, () -> {
      controller.setUpGame();;
    });

    assertTrue(exception.getMessage().contains("Cannot add more than two players"));
  }

  @Test
  public void startGameShouldStartGameForTrue() {
    Console consoleMock = Mockito.mock(Console.class);
    Mockito.when(consoleMock.promptForGameStart())
        .thenReturn(true);
  
    Game gameMock = Mockito.mock(Game.class);

    Controller controller = new Controller(gameMock, consoleMock);
    controller.startGame();

    Mockito.verify(gameMock).play();
  }

  @Test
  public void displayWinnerShouldPrintNameForFinalWinner() {
    Game gameMock = Mockito.mock(Game.class);
    Console consoleMock = Mockito.mock(Console.class);
    Message message = new Message();

    Controller controller = new Controller(gameMock, consoleMock, message);

    Player winner = new Player("Alice");

    String expected = "Alice wins the game!";

    controller.displayWinner(winner);

    Mockito.verify(consoleMock).printMessage(expected);
  }
}