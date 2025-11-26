package com.dicegame;

public class RoundResult {
  private Player player1;
  private int sum1;
  private Player player2;
  private int sum2;
  private Player winner;

  public RoundResult(Player player1, int sum1, Player player2, int sum2, Player winner) {
    this.player1 = player1;
    this.sum1 = sum1;
    this.player2 = player2;
    this.sum2 = sum2;
    this.winner = winner;
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public int getSum1() {
    return sum1;
  }

  public int getSum2() {
    return sum2;
  }

  public Player getWinner() {
    return winner;
  }
}
