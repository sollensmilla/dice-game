package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ConsoleTest {
    @Test
    public void consoleShouldPromptForPlayerNames() {
       Console console = new Console();

       assertArrayEquals(new String[]{"Enter name for Player 1:", "Enter name for Player 2:"},
               console.promptForPlayerNames(),
               "Console should prompt for two player names");

    }
}
