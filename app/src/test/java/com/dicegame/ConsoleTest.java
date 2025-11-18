package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ConsoleTest {
    @Test
    public void consoleShouldPromptForPlayerNames() {
        Console consoleMock = mock(Console.class);

        assertArrayEquals(new String[]{"Enter name for Player 1:", "Enter name for Player 2:"},
                consoleMock.getPlayerPrompts(),
                "Console should prompt for two player names");
    }
}
