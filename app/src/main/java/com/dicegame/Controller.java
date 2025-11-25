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
      game.play();
    }
  }

  public void displayWinner(String name) {
    String message = this.message.getFinalWinnerMessage(name);
    console.printMessage(message);
  }
}
