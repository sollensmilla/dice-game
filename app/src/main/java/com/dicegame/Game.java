package com.dicegame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private DiceCup diceCup;
    private Console console;

    public Game(DiceCup diceCup, Console console) {
        players = new ArrayList<>();
        this.diceCup = diceCup;
        this.console = console;
    }

    public void setUpGame() {
            String[] playerNames = console.promptForPlayerNames();
            for (String name : playerNames) {
                Player player = new Player(name);
                addPlayer(player);
            }
    }

    private void addPlayer(Player player) {
        if (players.size() >= 2) {
            throw new IllegalStateException("Cannot add more than two players");
        }
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player play() {
        while (true) {
            Player roundWinner = playRound();
            if (roundWinner.getScore() >= 5) {
                return roundWinner;
            }
        }
    }

    public Player playRound() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        int player1Roll = diceCup.rollAndSum();
        int player2Roll = diceCup.rollAndSum();

        Player winner = compareFaceValues(player1, player1Roll, player2, player2Roll);
        if (winner != null) {
            winner.addScore();
        }
        return winner;
    }

    private Player compareFaceValues(Player player1, int player1Roll, Player player2, int player2Roll) {
        if (player1Roll > player2Roll) {
            return player1;
        } else if (player2Roll > player1Roll) {
            return player2;
        } else {
            return null;
        }
    }
}
