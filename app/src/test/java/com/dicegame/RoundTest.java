package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RoundTest {
    private DiceCup diceCupMock;
    private Player player1;
    private Player player2;
    private Round round;

    @BeforeEach
    public void setUp() {
        diceCupMock = Mockito.mock(DiceCup.class);
        player1 = new Player("Alice");
        player2 = new Player("Eva");
        
        round = new Round(player1, player2, diceCupMock);
    }

    @Test
    public void playRoundShouldCheckWhichPlayerRollsHighestForEachRound() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(7)
                .thenReturn(10);

        Player winner = round.playRound();
        Mockito.verify(diceCupMock, Mockito.times(2)).rollAndSum();

        assertEquals(player2, winner, "Eva should win with higher roll");
    }

    @Test
    public void playRoundShouldNotAwardPointsForPlayerTie() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(8)
                .thenReturn(8);

        round.playRound();
        assertEquals(0, player1.getScore(), "Alice should have 0 points after tie");
        assertEquals(0, player2.getScore(), "Eva should have 0 points after tie");
    }

    @Test
    public void playRoundShouldAwardPointToPlayerForWinningRound() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(12)
                .thenReturn(10);

        round.playRound();

        assertEquals(1, player1.getScore(), "Alice should have 1 point after winning");
        assertEquals(0, player2.getScore(), "Eva should have 0 point after losing");
    }
}
