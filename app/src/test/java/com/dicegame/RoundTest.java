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

    @BeforeEach
    public void setUp() {
        diceCupMock = Mockito.mock(DiceCup.class);

        player1 = new Player("Alice");
        player2 = new Player("Eva");
    }

    @Test
    public void playRoundShouldCheckWhichPlayerRollsHighestForEachRound() {
         Round round = new Round(player1, player2, diceCupMock);
         
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(7)
                .thenReturn(10);

        Player winner = round.playRound();
        Mockito.verify(diceCupMock, Mockito.times(2)).rollAndSum();

        assertEquals(player2, winner, "Eva should win with higher roll");
    }
}
