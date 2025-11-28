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
    addPlayersToGame();
    displayWelcomeMessage();
  }

  private void addPlayersToGame() {
    String[] playerNames = console.promptForPlayerNames();
    for (String name : playerNames) {
      Player player = new Player(name);
      game.addPlayer(player);
    }
  }

  private void displayWelcomeMessage() {
    List<Player> players = game.getPlayers();

    String message = this.message.getWelcomeMessage(
        players.get(0).getName(), players.get(1).getName());

    console.printMessage(message);
  }

  public void startGame() {
    if (console.promptForGameStart()) {
      Player winner = game.play(this);
      displayWinner(winner.getName());
    } else {
      console.printMessage("Good bye!");
    }
  }

  private void displayWinner(String name) {
    String message = this.message.getFinalWinnerMessage(name);
    console.printMessage(message);
  }

  public void displayRoundResult(RoundResult roundResult) {
    printRollAndSumMessage(
        roundResult.getPlayer1().getName(),
        roundResult.getSum1());

    printRollAndSumMessage(
        roundResult.getPlayer2().getName(),
        roundResult.getSum2());

    printRoundWinnerMessage(roundResult);

    printScoreMessage(
        roundResult.getPlayer1(),
        roundResult.getPlayer2());

    console.waitForNextRound();
  }

  private void printRollAndSumMessage(String playerName, int sum) {
    String message = this.message.getRollAndSumMessage(playerName, sum);
    console.printMessage(message + "\n");
  }

  private void printRoundWinnerMessage(RoundResult roundResult) {
    Player roundWinner = roundResult.getWinner();
    String roundWinnerMessage;
    if (roundWinner == null) {
      roundWinnerMessage = "It's a tie.";
    } else {
      roundWinnerMessage = this.message.getRoundWinnerMessage(roundWinner.getName());
    }
    console.printMessage(roundWinnerMessage + "\n\n");
  }

  private void printScoreMessage(Player player1, Player player2) {
    String scoreMessage = this.message.getScoreMessage(player1, player2);
    console.printMessage(scoreMessage);
  }
}
