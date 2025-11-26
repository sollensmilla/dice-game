package com.dicegame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new Console(new Scanner(System.in), System.out);
        Game game = new Game(new DiceCup(new Dice(), new Dice()));
        Message message = new Message();
        Controller controller = new Controller(game, console, message);
        controller.setUpGame();
        controller.startGame();
    }
}
