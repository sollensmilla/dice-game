package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;

import org.junit.jupiter.api.Test;

public class GameTest {
    private DiceCup diceCupMock;
    private Game game;

    @BeforeEach
    public void setUp() {
        diceCupMock = Mockito.mock(DiceCup.class);
        game = new Game(diceCupMock);
    }

    @Test
    public void gameShouldHaveTwoPlayers() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Eva");

        game.addPlayer(player1);
        game.addPlayer(player2);

        assert game.getPlayers().size() == 2 : "Game should have two players";
    }

    @Test
    public void addingMoreThanTwoPlayersShouldThrowException() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Eva");
        Player player3 = new Player("Bob");

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

        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(7)  
                .thenReturn(10);

        game.addPlayer(player1);
        game.addPlayer(player2);

        assertEquals(player2, game.playRound(), "Eva should win with higher roll");
    }

    @Test
    public void playerShouldGetOnePointAfterWinningRound() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Eva");

        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(12)  
                .thenReturn(10);

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.playRound();

        assertEquals(1, player1.getScore(), "Alice should have 1 point after winning");
        assertEquals(0, player2.getScore(), "Eva should have 0 point after losing");
    }

    @Test
    public void gameShouldEndWhenOnePlayerHasFivePoints() {
        Player player1 = new Player("Alice");
        Player player2 = new Player("Eva");

        Mockito.when(diceCupMock.rollAndSum())
            .thenReturn(10, 5)
            .thenReturn(10, 5)
            .thenReturn(10, 5)
            .thenReturn(10, 5)
            .thenReturn(10, 5);

        Player winner = game.play();
    
        assertEquals(player1, winner, "Alice should win the game");
    }

}
