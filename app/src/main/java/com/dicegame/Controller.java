package com.dicegame;

import java.util.List;

public class Controller {
  private Game game;
  private Console console;
  private Message message;

  public Controller(Game game, Console console, Message message) {
    this.game = game;
    this.console = console;
    this.message = message;
  }

  public void setUpGame() {
    String[] playerNames = console.promptForPlayerNames();
    for (String name : playerNames) {
      Player player = new Player(name);
      game.addPlayer(player);
    }

    List<Player> players = game.getPlayers();

    console.displayWelcomeScreen(players.get(0).getName(), players.get(1).getName());
  }

  public void startGame() {
    if (console.promptForGameStart()) {
      Player winner = game.play(this);
      displayWinner(winner.getName());
    }
  }

  public void displayWinner(String name) {
    String message = this.message.getFinalWinnerMessage(name);
    console.printMessage(message);
  }

public void displayRoundResult(RoundResult roundResult) {
    String player1Sum = this.message.getRollAndSumMessage(
            roundResult.getPlayer1().getName(), roundResult.getSum1());
    String player2Sum = this.message.getRollAndSumMessage(
            roundResult.getPlayer2().getName(), roundResult.getSum2());
    Player roundWinner = roundResult.getWinner();
    String roundWinnerMessage;
    if (roundWinner == null) {
        roundWinnerMessage = "It's a tie.";
    } else {
        roundWinnerMessage = this.message.getRoundWinnerMessage(roundWinner.getName());
    }

    console.printMessage(player1Sum + "\n" + player2Sum + "\n" + roundWinnerMessage);

    console.waitForNextRound();
}

}
