package com.dicegame;

public class PlayerTest {
    public void playerShouldHaveAName() {
        Player player = new Player("Alice");
        assert player.getName().equals("Alice") : "Player name should be Alice";
    }
}
