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

        int player1Roll = diceCupMock.rollAndSum();
        int player2Roll = diceCupMock.rollAndSum();

        assertEquals(player2, game.compareFaceValues(player1, player1Roll, player2, player2Roll), "Eva should win with higher roll");
    }

}
