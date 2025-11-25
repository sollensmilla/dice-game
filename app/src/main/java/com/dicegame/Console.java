package com.dicegame;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private PrintStream out;

    public Console() {
        this(new Scanner(System.in), System.out);
    }

    public Console(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    public void printMessage(String message) {
        out.println(message);
    }

    public String[] promptForPlayerNames() {
        String player1 = "";
        while (player1.isBlank()) {
            out.println("Enter name for Player 1:");
            player1 = scanner.nextLine().trim();

            if (player1.isBlank()) {
                out.println("Name cannot be empty. Please try again.\n");
            }
        }

        out.println("Enter name for Player 2 (leave empty to play against the computer):");
        String player2 = scanner.nextLine();

        if (player2.isEmpty()) {
            player2 = "Bot";
        }
        return new String[] { player1, player2 };
    }

    public void displayWelcomeScreen(String player1, String player2) {

        out.print("Welcome to the dice game, " + player1 + " and " + player2 + "!");
    }

    public boolean promptForGameStart() {
        out.println("\nPress ENTER to start playing.");
        String line = scanner.nextLine();
        if (line.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void displayRoundResult(String string1, String string2, String string3) {
        out.print(string1 + "\n" +
                string2 + "\n" +
                string3);
    }
}
