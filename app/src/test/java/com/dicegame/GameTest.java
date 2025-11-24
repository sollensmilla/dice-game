package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class GameTest {
    private DiceCup diceCupMock;
    private Console consoleMock;
    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        consoleMock = Mockito.mock(Console.class);
        Mockito.when(consoleMock.promptForPlayerNames())
                .thenReturn(new String[] { "Alice", "Eva" });

        diceCupMock = Mockito.mock(DiceCup.class);
        game = new Game(diceCupMock, consoleMock);
        game.setUpGame();

        player1 = game.getPlayers().get(0);
        player2 = game.getPlayers().get(1);
    }

    @Test
    public void getPlayersShouldHaveSizeTwoForTwoPlayers() {
        assert game.getPlayers().size() == 2 : "Game should have two players";
    }

    @Test
    public void playRoundShouldCheckWhichPlayerRollsHighestForEachRound() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(7)
                .thenReturn(10);

        Player winner = game.playRound();
        Mockito.verify(diceCupMock, Mockito.times(2)).rollAndSum();
        
        assertEquals(player2, winner, "Eva should win with higher roll");
    }

    @Test
    public void playRoundShouldNotAwardPointsForPlayerTie() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(8)
                .thenReturn(8);

        game.playRound();
        assertEquals(0, player1.getScore(), "Alice should have 0 points after tie");
        assertEquals(0, player2.getScore(), "Eva should have 0 points after tie");
    }

    @Test
    public void playRoundShouldAwardPointToPlayerForWinningRound() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(12)
                .thenReturn(10);

        game.playRound();

        assertEquals(1, player1.getScore(), "Alice should have 1 point after winning");
        assertEquals(0, player2.getScore(), "Eva should have 0 point after losing");
    }

    @Test
    public void playShouldRunUntilOnePlayerHasFivePoints() {

        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5);

        Player winner = game.play();

        assertEquals(player1, winner, "Alice should win the game");
    }

    @Test
    public void playShouldNotAwardScoreForPlayerTie() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(10, 10)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5);

        Player winner = game.play();

        assertEquals(player1, winner, "Alice should win the game");
    }
}
