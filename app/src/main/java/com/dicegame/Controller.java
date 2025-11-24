package com.dicegame;

import java.util.List;

public class Controller {
  private Game game;
  private Console console;

  public Controller(Game game, Console console) {
    this.game = game;
    this.console = console;
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
}
