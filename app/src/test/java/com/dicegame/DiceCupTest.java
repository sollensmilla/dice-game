package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DiceCupTest {
    @Test
    public void rollAndSumShouldCalculateSumForDiceCup() {
        Dice dice1 = Mockito.mock(Dice.class);
        Dice dice2 = Mockito.mock(Dice.class);

        Mockito.when(dice1.roll()).thenReturn(3);
        Mockito.when(dice2.roll()).thenReturn(5);

        DiceCup diceCup = new DiceCup(dice1, dice2);
        int sum = diceCup.rollAndSum();

        assertEquals(sum, 8, "Sum should be 8");
    }
}
