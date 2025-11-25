package com.dicegame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class GameTest {
    private DiceCup diceCupMock;
    private Console consoleMock;
    private Game game;
    private Player player1;

    @BeforeEach
    public void setUp() {
        consoleMock = Mockito.mock(Console.class);
        Mockito.when(consoleMock.promptForPlayerNames())
                .thenReturn(new String[] { "Alice", "Eva" });

        diceCupMock = Mockito.mock(DiceCup.class);
        game = new Game(diceCupMock);
        Controller controller = new Controller(game, consoleMock, null);
        controller.setUpGame();

        player1 = game.getPlayers().get(0);
    }

    @Test
    public void getPlayersShouldHaveSizeTwoForTwoPlayers() {
        assert game.getPlayers().size() == 2 : "Game should have two players";
    }

    @Test
    public void playShouldRunUntilOnePlayerHasFivePoints() {

        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5);

        Player winner = game.play();

        assertEquals(player1, winner, "Alice should win the game");
    }

    @Test
    public void playShouldNotAwardScoreForPlayerTie() {
        Mockito.when(diceCupMock.rollAndSum())
                .thenReturn(10, 10)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5)
                .thenReturn(10, 5);

        Player winner = game.play();

        assertEquals(player1, winner, "Alice should win the game");
    }
}
