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
    String finalWinnerMessage = "Alice wins the game with a result of 5-2!";
    String result = message.getFinalWinnerMessage("Alice", 5, 2);
    assertEquals(result, finalWinnerMessage);
  }
}