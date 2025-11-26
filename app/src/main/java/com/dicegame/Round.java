package com.dicegame;

public class Round {
    private Player player1;
    private Player player2;
    private DiceCup diceCup;

    public Round(Player player1, Player player2, DiceCup diceCup) {
        this.player1 = player1;
        this.player2 = player2;
        this.diceCup = diceCup;
    }

      public RoundResult playRound() {
        int player1Roll = diceCup.rollAndSum();
        int player2Roll = diceCup.rollAndSum();

        Player winner = compareFaceValues(player1Roll, player2Roll);
        if (winner != null) {
            winner.addScore();
        }
        return new RoundResult(player1, player1Roll, player2, player2Roll, winner);
    }

    private Player compareFaceValues(int player1Roll, int player2Roll) {
        if (player1Roll > player2Roll) {
            return player1;
        } else if (player2Roll > player1Roll) {
            return player2;
        } else {
            return null;
        }
    }
}
