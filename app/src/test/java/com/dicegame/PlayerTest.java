package com.dicegame;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void getNameShouldReturnNameForPlayer() {
        Player player = new Player("Alice");
        assert player.getName().equals("Alice") : "Player name should be Alice";
    }

    @Test 
    public void getScoreShouldStartWithZeroScoreForPlayer() {
        Player player = new Player("Eva");
        assert player.getScore() == 0 : "Player score should start at 0";
    }

    @Test
    public void addScoreShouldIncreasePlayerScoreForPlayer() {
        Player player = new Player("Eva");

        player.addScore();
        assert player.getScore() == 1 : "Player score should be 1 after adding score";
    }
}
