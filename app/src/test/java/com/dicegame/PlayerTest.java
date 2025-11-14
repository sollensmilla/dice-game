package com.dicegame;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void playerShouldHaveAName() {
        Player player = new Player("Alice");
        assert player.getName().equals("Alice") : "Player name should be Alice";
    }
}
