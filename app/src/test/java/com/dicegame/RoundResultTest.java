package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoundResultTest {
  private RoundResult roundResult;

  @BeforeEach
  public void setUp() {
    String player1 = "Alice";
    int sum1 = 7;
    String player2 = "Eva";
    int sum2 = 10;
    String winner = "Eva";

    roundResult = new RoundResult(player1, sum1, player2, sum2, winner);
  }

  @Test
  public void getPlayer1ShouldReturnNameForPlayer1() {
    assertEquals("Alice", roundResult.getPlayer1());
  }

  @Test
  public void getPlayer2ShouldReturnNameForPlayer2() {
    assertEquals("Eva", roundResult.getPlayer2());
  }

  @Test
  public void getSum1ShouldReturnSumForPlayer1() {
    assertEquals(7, roundResult.getSum1());
  }

  @Test
  public void getSum2ShouldReturnSumForPlayer2() {
    assertEquals(10, roundResult.getSum2());
  }
  
}
