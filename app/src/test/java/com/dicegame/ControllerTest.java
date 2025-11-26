package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ControllerTest {
  private Message message;
  private Console consoleMock;
  private Game gameMock;
  private Controller controller;

  @BeforeEach
  public void setUp() {
    message = new Message();
    consoleMock = Mockito.mock(Console.class);
    gameMock = Mockito.mock(Game.class);
    controller = new Controller(gameMock, consoleMock, message);
  }

  @Test
  public void setUpGameShouldAddPlayersToGameForGivenPlayers() {
    Mockito.when(consoleMock.promptForPlayerNames())
        .thenReturn(new String[] { "Alice", "Eva" });

    DiceCup diceCupMock = Mockito.mock(DiceCup.class);
    Game game = new Game(diceCupMock);
    Controller localController = new Controller(game, consoleMock, message);
    localController.setUpGame();
    Mockito.verify(consoleMock).promptForPlayerNames();

    List<Player> players = game.getPlayers();

    assertEquals("Alice", players.get(0).getName(), "Player 1 name should be Alice");
    assertEquals("Eva", players.get(1).getName(), "Player 2 name should be Eva");
  }

  @Test
  public void setUpGameShouldThrowExceptionForMoreThanTwoPlayers() {
    DiceCup diceCupMock = Mockito.mock(DiceCup.class);
    Mockito.when(consoleMock.promptForPlayerNames())
        .thenReturn(new String[] { "Alice", "Eva", "Bob" });

    Game game = new Game(diceCupMock);
    Controller localController = new Controller(game, consoleMock, message);

    Exception exception = assertThrows(IllegalStateException.class, () -> {
      localController.setUpGame();
    });

    assertTrue(exception.getMessage().contains("Cannot add more than two players"));
  }

  @Test
  public void startGameShouldStartGameForTrue() {
    Mockito.when(consoleMock.promptForGameStart()).thenReturn(true);
    Mockito.when(gameMock.play(controller)).thenReturn(new Player("Test"));

    controller.startGame();

    Mockito.verify(gameMock).play(controller);
  }

  @Test
  public void displayWinnerShouldPrintNameForFinalWinner() {
    Player winner = new Player("Alice");
    String expected = "Alice wins the game!";

    controller.displayWinner(winner.getName());

    Mockito.verify(consoleMock).printMessage(expected);
  }

  @Test
  public void startGameShouldDisplayWinnerForEndedGame() {
    Mockito.when(consoleMock.promptForGameStart()).thenReturn(true);
    Mockito.when(gameMock.play(controller)).thenReturn(new Player("Alice"));

    controller.startGame();

    Mockito.verify(consoleMock)
        .printMessage("Alice wins the game!");
  }

  @Test
  public void displayRoundResultShouldDisplayResultForRound() {
    Player player1 = new Player("Alice");
    Player player2 = new Player("Eva");
    RoundResult roundResult = new RoundResult(player1, 7, player2, 5, player1);

    String expected = "Alice rolled a sum of 7." +
        "\nEva rolled a sum of 5." +
        "\nAlice wins this round.";

    controller.displayRoundResult(roundResult);

    Mockito.verify(consoleMock).printMessage(expected);
  }

  @Test
  public void displayRoundResultShouldDisplayResultForPlayerTie() {
    Player player1 = new Player("Alice");
    Player player2 = new Player("Eva");
    RoundResult roundResult = new RoundResult(player1, 5, player2, 5, null);

    String expected = "Alice rolled a sum of 5." +
        "\nEva rolled a sum of 5." +
        "\nIt's a tie.";

    controller.displayRoundResult(roundResult);

    Mockito.verify(consoleMock).printMessage(expected);
  }
}