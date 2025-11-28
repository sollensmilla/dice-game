package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ConsoleTest {
    @Test
    public void printMessageShouldPrintGivenMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        Scanner fakeScanner = new Scanner("");

        Console console = new Console(fakeScanner, printStream);

        console.printMessage("Hello, World!");

        assertEquals("Hello, World!", output.toString().trim());
    }

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
    public void promptForPlayerNamesShouldRepromptForPlayer1NameIfEmpty() {
        String input = "\nAlice\nEva\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(scanner, printStream);

        String[] result = console.promptForPlayerNames();

        assertArrayEquals(new String[] { "Alice", "Eva" }, result);
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
    public void promptForGameStartShouldDisplayPromptForUserAction() {
        String input = "\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(scanner, printStream);

        console.promptForGameStart();

        assertEquals(
                "Press ENTER to start playing.",
                output.toString().trim());
    }

    @Test
    public void promptForGameStartShouldReturnTrueForEnterKey() {
        String input = "\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        output.reset();

        Console console = new Console(scanner, printStream);

        assertTrue(console.promptForGameStart());
    }

    @Test
    public void promptForGameStartShouldReturnFalseForOtherInputThanEnterKey() {
        String input = "Test\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        output.reset();

        Console console = new Console(scanner, printStream);

        assertFalse(console.promptForGameStart());
    }

    @Test
    public void waitForNextRoundShouldDisplayPromptForUserAndWaitForEnter() {
        Scanner scanner = new Scanner("\n");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(scanner, printStream);

        console.waitForNextRound();

        assertEquals("Press ENTER for next round...", output.toString().trim());
    }

}
