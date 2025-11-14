package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTest {
   @Test
   public void gameShouldHaveTwoPlayers() {
         Player player1 = new Player("Alice");
         Player player2 = new Player("Eva");
         Game game = new Game();

        game.addPlayer(player1);
        game.addPlayer(player2);

        assert game.getPlayers().size() == 2 : "Game should have two players";    
   }
}
