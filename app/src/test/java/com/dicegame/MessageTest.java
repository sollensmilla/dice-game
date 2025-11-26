package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MessageTest {
  @Test
  public void getRoundWinnerMessageShouldContainNameForRoundWinner() {
    Message message = new Message();
    String roundWinnerMessage = "Alice wins this round.";
    String result = message.getRoundWinnerMessage("Alice");
    assertEquals(result, roundWinnerMessage);
  }

  @Test
  public void getRollAndSumMessageShouldPrintMessageForFaceValues() {
    Message message = new Message();
    String faceValueForPlayer = "Alice rolled a sum of 7.";
    String result = message.getRollAndSumMessage("Alice", 7);
    assertEquals(result, faceValueForPlayer);
  }

  @Test
  public void getFinalWinnerMessageShouldContainNameForFinalWinner() {
    Message message = new Message();
    String finalWinnerMessage = "Alice wins the game!";
    String result = message.getFinalWinnerMessage("Alice");
    assertEquals(result, finalWinnerMessage);
  }

  @Test
  public void getScoreMessageShouldPrintCurrentScoreForBothPlayers() {
    Message message = new Message();

    Player player1 = new Player("Alice");
    Player player2 = new Player("Eva");

    player1.addScore();
    player1.addScore();
    player2.addScore();

    String expected = "Points after round:\n" +
        "Alice: 2\n" +
        "Eva: 1";

    String result = message.getScoreMessage(player1, player2);

    assertEquals(expected, result);
  }

}