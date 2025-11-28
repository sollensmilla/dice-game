package com.dicegame;

import java.util.Random;

public class Dice {
  private Random random = new Random();
  private int faceValue;

  public int roll() {
    this.faceValue = random.nextInt(6) + 1;
    return this.faceValue;
  }
}
