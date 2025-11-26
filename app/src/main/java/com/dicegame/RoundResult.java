package com.dicegame;

public class RoundResult {
  private String player1;
  private int sum1;
  private String player2;
  private int sum2;
  private String winner;

  public RoundResult(String player1, int sum1, String player2, int sum2, String winner) {
    this.player1 = player1;
    this.sum1 = sum1;
    this.player2 = player2;
    this.sum2 = sum2;
    this.winner = winner;
  }

  public String getPlayer1() {
    return player1;
  }

  public String getPlayer2() {
    return player2;
  }

  public int getSum1() {
    return sum1;
  }
}
