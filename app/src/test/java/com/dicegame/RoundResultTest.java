package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoundResultTest {
  private RoundResult roundResult;
  private Player player1;
  private Player player2;

  @BeforeEach
  public void setUp() {
    player1 = new Player("Alice");
    int sum1 = 7;
    player2 = new Player("Eva");
    int sum2 = 10;

    roundResult = new RoundResult(player1, sum1, player2, sum2, player2);
  }

  @Test
  public void getPlayer1ShouldReturnPlayerObjectForPlayer1() {
    assertEquals(player1, roundResult.getPlayer1());
  }

  @Test
  public void getPlayer2ShouldReturnPlayerObjectForPlayer2() {
    assertEquals(player2, roundResult.getPlayer2());
  }

  @Test
  public void getSum1ShouldReturnSumForPlayer1() {
    assertEquals(7, roundResult.getSum1());
  }

  @Test
  public void getSum2ShouldReturnSumForPlayer2() {
    assertEquals(10, roundResult.getSum2());
  }
  
  @Test
  public void getWinnerShouldReturnPlayerObjectForWinner() {
    assertEquals(player2, roundResult.getWinner());
  }
}
