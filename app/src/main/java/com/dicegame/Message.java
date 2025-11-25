package com.dicegame;

public class Message {

  public String getRoundWinnerMessage(String playerName) {
    return playerName + " wins this round.";
  }

  public String getRollAndSumMessage(String playerName, int sum) {
    return playerName + " rolled a sum of " + sum + ".";
  }

  public String getFinalWinnerMessage(String playerName, int playerScore, int opponentScore) {
    return playerName + " wins the game with a result of " + playerScore + "-" + opponentScore + "!";
  }
}
