package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameConsoleIntegrationTest {

    @Test
    public void setUpGameShouldAddPlayersToGame() {
      DiceCup diceCupMock = Mockito.mock(DiceCup.class);
      Game game = new Game(null);
      game.setUpGame();

      List<Player> players = game.getPlayers();

      assertEquals("Alice", players.get(0).getName(), "Player 1 name should be Alice");
      assertEquals("Eva", players.get(1).getName(), "Player 2 name should be Eva");
    }
}
