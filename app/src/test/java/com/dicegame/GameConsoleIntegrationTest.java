package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameConsoleIntegrationTest {
  @Test
  public void setUpGameShouldAddPlayersToGame() {
    Console consoleMock = Mockito.mock(Console.class);
    Mockito.when(consoleMock.promptForPlayerNames())
        .thenReturn(new String[] { "Alice", "Eva" });

    DiceCup diceCupMock = Mockito.mock(DiceCup.class);
    Game game = new Game(diceCupMock, consoleMock);
    game.setUpGame();

    List<Player> players = game.getPlayers();

    assertEquals("Alice", players.get(0).getName(), "Player 1 name should be Alice");
    assertEquals("Eva", players.get(1).getName(), "Player 2 name should be Eva");
  }

  @Test
  public void setUpGameShouldNotAllowMoreThanTwoPlayers() {
    Console consoleMock = Mockito.mock(Console.class);
    DiceCup diceCupMock = Mockito.mock(DiceCup.class);
    Mockito.when(consoleMock.promptForPlayerNames())
        .thenReturn(new String[] { "Alice", "Eva", "Bob" });

    Game game = new Game(diceCupMock, consoleMock);

    Exception exception = assertThrows(IllegalStateException.class, () -> {
      game.setUpGame();
    });

    assertTrue(exception.getMessage().contains("Cannot add more than two players"));

  }
}
