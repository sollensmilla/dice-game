package com.dicegame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (players.size() >= 2) {
            throw new IllegalStateException("Cannot add more than two players");
        }
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;

    }
}
