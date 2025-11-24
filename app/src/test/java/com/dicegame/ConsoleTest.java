package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ConsoleTest {
    @Test
    public void promptForPlayerNamesShouldReadNamesForGivenInput() {
        String input = "Alice\nEva\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(scanner, printStream);

        String[] result = console.promptForPlayerNames();

        assertArrayEquals(new String[] { "Alice", "Eva" }, result);
    }

    @Test
    public void promptForPlayerNamesShouldDisplayPromptForPlayerNames() {
        String input = "Alice\nEva\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(scanner, printStream);

        console.promptForPlayerNames();

        assertEquals(
                "Enter name for Player 1:\nEnter name for Player 2 (leave empty to play against the computer):",
                output.toString().trim());
    }

    @Test
    public void promptForPlayerNamesShouldUseComputerPlayerForEmptyPlayer2() {
        String input = "Alice\n\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(scanner, printStream);

        String[] result = console.promptForPlayerNames();

        assertArrayEquals(new String[] { "Alice", "Bot" }, result);
    }

    @Test
    public void displayWelcomeScreenShouldDisplayNamesForGivenInput() {
        String input = "Alice\nEva\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(scanner, printStream);

        console.promptForPlayerNames();
        output.reset();

        console.displayWelcomeScreen("Alice", "Eva");

        String expected = "Welcome to the dice game, Alice and Eva!"
                + System.lineSeparator()
                + "Press enter to start playing.";

        String actual = output.toString().trim();

        assertEquals(expected, actual);

    }
}
