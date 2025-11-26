package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoundResultTest {

  @Test
  public void getPlayer1ShouldReturnNameForPlayer1() {
    String player1 = "Alice";
    int sum1 = 7;
    String player2 = "Eva";
    int sum2 = 10;
    String winner = "Eva";
    
    RoundResult roundResult = new RoundResult(player1, sum1, player2, sum2, winner);

    assertEquals("Alice", roundResult.getPlayer1());
  }
  
}
