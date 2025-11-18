package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ConsoleTest {
    @Test
    public void consoleShouldPromptForPlayerNames() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        Console console = new Console(new Scanner(""), printStream);

         console.promptForPlayerNames();

        String result = output.toString().trim();

               assertEquals(
            "Enter name for Player 1:\nEnter name for Player 2:",
            result
        );

    }
}
