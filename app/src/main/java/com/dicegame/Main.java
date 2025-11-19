package com.dicegame;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        Game game = new Game(new DiceCup(new Dice(), new Dice()), console);
        game.setUpGame();
        game.play();
    }
}
