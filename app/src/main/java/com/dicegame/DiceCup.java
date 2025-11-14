package com.dicegame;

public class DiceCup {
    private Dice dice1;
    private Dice dice2;

    public DiceCup(Dice dice1, Dice dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public int rollAndSum() {
        return dice1.roll() + dice2.roll();
    }
}
