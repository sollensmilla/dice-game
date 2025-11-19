package com.dicegame;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new DiceCup(new Dice(), new Dice()), new Console());
        game.setUpGame();
        game.play();
    }
}
