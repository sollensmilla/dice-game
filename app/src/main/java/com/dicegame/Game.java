package com.dicegame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private DiceCup diceCup;

    public Game(DiceCup diceCup) {
        players = new ArrayList<>();
        this.diceCup = diceCup;
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

    public Player play(Controller controller) {
        while (true) {
            RoundResult result = playRound();
            controller.displayRoundResult(result);
            Player roundWinner = result.getWinner();
            if (roundWinner != null && roundWinner.getScore() >= 5) {
                return roundWinner;
            }
        }
    }

    private RoundResult playRound() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Round round = new Round(player1, player2, diceCup);
        return round.playRound();
    }
}
