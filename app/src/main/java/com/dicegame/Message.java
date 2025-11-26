package com.dicegame;

public class Message {

  public String getRoundWinnerMessage(String playerName) {
    return playerName + " wins this round.";
  }

  public String getRollAndSumMessage(String playerName, int sum) {
    return playerName + " rolled a sum of " + sum + ".";
  }

  public String getScoreMessage(Player player1, Player player2) {
    return "Points after round:\n" +
        player1.getName() + ": " + player1.getScore() + "\n" +
        player2.getName() + ": " + player2.getScore();
  }

  public String getFinalWinnerMessage(String playerName) {
    return playerName + " wins the game!";
  }
}
