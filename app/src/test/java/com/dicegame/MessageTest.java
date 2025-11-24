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
}
