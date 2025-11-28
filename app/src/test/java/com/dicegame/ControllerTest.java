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
  public void setUpGameShouldDisplayNamesForGivenInput() {
    Mockito.when(consoleMock.promptForPlayerNames())
        .thenReturn(new String[] { "Alice", "Eva" });
        
    Mockito.when(gameMock.getPlayers())
        .thenReturn(
            List.of(new Player("Alice"), new Player("Eva")));

    controller.setUpGame();

    Mockito.verify(consoleMock).printMessage("Welcome to the dice game, Alice and Eva!");
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
  public void startGameShouldDisplayGoodByeMessageForFalse() {
    Mockito.when(consoleMock.promptForGameStart()).thenReturn(false);

    controller.startGame();

    Mockito.verify(consoleMock).printMessage("Good bye!");
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
    Player player1 = Mockito.mock(Player.class);
    Player player2 = Mockito.mock(Player.class);

    Mockito.when(player1.getName()).thenReturn("Alice");
    Mockito.when(player2.getName()).thenReturn("Eva");
    Mockito.when(player1.getScore()).thenReturn(1);
    Mockito.when(player2.getScore()).thenReturn(0);

    RoundResult roundResult = Mockito.mock(RoundResult.class);
    Mockito.when(roundResult.getPlayer1()).thenReturn(player1);
    Mockito.when(roundResult.getPlayer2()).thenReturn(player2);
    Mockito.when(roundResult.getSum1()).thenReturn(7);
    Mockito.when(roundResult.getSum2()).thenReturn(5);
    Mockito.when(roundResult.getWinner()).thenReturn(player1);

    String expected = "Alice rolled a sum of 7.\n" +
        "Eva rolled a sum of 5.\n" +
        "Alice wins this round.\n\n" +
        "Points after round:\n" +
        "Alice: 1\n" +
        "Eva: 0";

    controller.displayRoundResult(roundResult);

    Mockito.verify(consoleMock).printMessage(expected);
  }

  @Test
  public void displayRoundResultShouldDisplayResultForPlayerTie() {
    Player player1 = Mockito.mock(Player.class);
    Player player2 = Mockito.mock(Player.class);

    Mockito.when(player1.getName()).thenReturn("Alice");
    Mockito.when(player2.getName()).thenReturn("Eva");
    Mockito.when(player1.getScore()).thenReturn(0);
    Mockito.when(player2.getScore()).thenReturn(0);

    RoundResult roundResult = Mockito.mock(RoundResult.class);
    Mockito.when(roundResult.getPlayer1()).thenReturn(player1);
    Mockito.when(roundResult.getPlayer2()).thenReturn(player2);
    Mockito.when(roundResult.getSum1()).thenReturn(5);
    Mockito.when(roundResult.getSum2()).thenReturn(5);
    Mockito.when(roundResult.getWinner()).thenReturn(null);

    String expected = "Alice rolled a sum of 5.\n" +
        "Eva rolled a sum of 5.\n" +
        "It's a tie.\n\n" +
        "Points after round:\n" +
        "Alice: 0\n" +
        "Eva: 0";

    controller.displayRoundResult(roundResult);

    Mockito.verify(consoleMock).printMessage(expected);
  }
}