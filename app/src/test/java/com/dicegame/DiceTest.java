package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class DiceTest {

  @Test
  public void rollShouldBeBetweenOneAndSixForDice() {
    Dice dice = new Dice();
    int roll = dice.roll();
    assertTrue(roll >= 1, "Roll should be at least 1");
    assertTrue(roll <= 6, "Roll should be max 6");
  }

}
