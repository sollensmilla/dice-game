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

    public String[] promptForPlayerNames() {
        out.println("Enter name for Player 1:");
        String player1 = scanner.nextLine();

        out.println("Enter name for Player 2:");
        String player2 = scanner.nextLine();
        
        return new String[] { player1, player2 };
    }
}
