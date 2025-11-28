package com.dicegame;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private PrintStream out;

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
            this.printMessage("Enter name for Player 1:");
            player1 = scanner.nextLine().trim();

            if (player1.isBlank()) {
                this.printMessage("Name cannot be empty. Please try again.\n");
            }
        }

        this.printMessage("Enter name for Player 2 (leave empty to play against the computer):");
        String player2 = scanner.nextLine();

        if (player2.isEmpty()) {
            player2 = "Bot";
        }
        return new String[] { player1, player2 };
    }

    public boolean promptForGameStart() {
        this.printMessage("\nPress ENTER to start playing.");
        String line = scanner.nextLine();
        if (line.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void waitForNextRound() {
        this.printMessage("Press ENTER for next round...");
        scanner.nextLine();
}

}
