package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;

import org.junit.jupiter.api.Test;

public class GameTest {
    private DiceCup diceCupMock;
    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        diceCupMock = Mockito.mock(DiceCup.class);
        game = new Game(diceCupMock);

        player1 = new Player("Alice");
        player2 = new Player("Eva");

        game.addPlayer(player1);
        game.addPlayer(player2);
    }

    @Test
    public void gameShouldHaveTwoPlayers() {
        assert game.getPlayers().size() == 2 : "Game should have two players";
    }

    @Test
    public void addingMoreThanTwoPlayersShouldThrowException() {
        Player player3 = new Player("Bob");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            game.addPlayer(player3);
        });

        String expectedMessage = "Cannot add more than two players";
        String actualMessage = exception.getMessage();

        assert actualMessage.contains(expectedMessage) : "Exception message should indicate max players reached";
    }

    @Test
    public void gameShouldCheckWhichPlayerRollsHighest() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(7)  
                .thenReturn(10);


        assertEquals(player2, game.playRound(), "Eva should win with higher roll");
    }

    @Test
    public void playerShouldGetOnePointAfterWinningRound() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(12)  
                .thenReturn(10);

        game.playRound();

        assertEquals(1, player1.getScore(), "Alice should have 1 point after winning");
        assertEquals(0, player2.getScore(), "Eva should have 0 point after losing");
    }

    @Test
    public void gameShouldEndWhenOnePlayerHasFivePoints() {

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
