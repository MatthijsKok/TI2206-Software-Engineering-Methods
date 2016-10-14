package game;

import bubbletrouble.BubbleTroubleApplicationTest;
import level.Level;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;

/**
 * Test suite for the GameState class.
 */
public class GameStateTest extends BubbleTroubleApplicationTest {

    private static Game mockedGame = Mockito.mock(Game.class);

    private GameState gameState;

    @Before
    public void setUp() {
        List<Level> levels = new ArrayList<>();
        levels.add(Mockito.mock(Level.class));

        Mockito.when(mockedGame.getPlayers()).thenReturn(new ArrayList<>());
        Mockito.when(mockedGame.getLevels()).thenReturn(levels);

        gameState = new GameState(mockedGame);
    }

    @Test
    public void testUpdateOther() {
        // Nothing should have changed

        Object[] expected = { gameState.isWon(), gameState.isLost(), gameState.getCurrentLevel()};

        gameState.update(new Observable(), null);

        Object[] actual = { gameState.isWon(), gameState.isLost(), gameState.getCurrentLevel()};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testReset() {
        gameState.win();
        gameState.reset();
        assertFalse(gameState.isWon() || gameState.isLost());
    }

    @Test
    public void testPause() {
        gameState.pause();
        assertThat(gameState.isInProgress(), is(false));
    }

    @Test
    public void testResume() {
        gameState.resume();
        assertThat(gameState.isInProgress(), is(true));
    }

    @Test
    public void testHasNextLevel() {
        // Current game has 1 level only
        assertThat(gameState.hasNextLevel(), is(true));
    }

    @Test
    public void testWinning() {
        gameState.win();
        assertThat(gameState.isWon(), is(true));
    }

    @Test
    public void testWinningWhileLost() {
        gameState.lose();
        gameState.win();
        assertThat(gameState.isWon(), is(false));
    }

    @Test
    public void testLosing() {
        gameState.lose();
        assertThat(gameState.isLost(), is(true));
    }

    @Test
    public void testLosingWhileWon() {
        gameState.win();
        gameState.lose();
        assertThat(gameState.isLost(), is(false));
    }
}
