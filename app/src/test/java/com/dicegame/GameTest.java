package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;

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

    @Test
    public void addingMoreThanTwoPlayersShouldThrowException() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Eva");
        Player player3 = new Player("Bob");
        Game game = new Game();

        game.addPlayer(player1);
        game.addPlayer(player2);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            game.addPlayer(player3);
        });

        String expectedMessage = "Cannot add more than two players";
        String actualMessage = exception.getMessage();

        assert actualMessage.contains(expectedMessage) : "Exception message should indicate max players reached";
    }

    @Test
    public void gameShouldCheckWhichPlayerRollsHighest() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Eva");
        Game game = new Game();

        game.addPlayer(player1);
        game.addPlayer(player2);

        DiceCup diceCupMock = Mockito.mock(DiceCup.class);

            Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(7)  
                .thenReturn(10);

        int player1Roll = diceCupMock.rollAndSum();
        int player2Roll = diceCupMock.rollAndSum();


        assertEquals(player2, game.compareFaceValues(player1, player1Roll, player2, player2Roll), "Eva should win with higher roll");
    }

}
