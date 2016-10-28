package game;

import bubbletrouble.BubbleTroubleApplicationTest;
import game.player.Player;
import game.state.InProgressState;
import game.state.LevelLostState;
import game.state.LevelWonState;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test suite for the game class.
 */
public class GameTest extends BubbleTroubleApplicationTest {

    @Test
    public void testSetPlayerCount() {
        final int count = 2;
        Game.setPlayerCount(count);
        assertThat(Game.getPlayers().size(), is(count));
    }

    @Test
    public void testGetPlayer() {
        Game.setPlayerCount(1);
        assertThat(Game.getPlayers().get(0), instanceOf(Player.class));
    }

    @Test
    public void testGetState() {
        assertNotNull(Game.getState());
    }

    @Test
    public void testStart() {
        try {
            Game.start();
        } catch (IOException e) {
            fail();
        }
        assertTrue(Game.getState() instanceof InProgressState);


        Game.stop();
    }

    @Test
    public void testWinLevel() {
        try {
            Game.winLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(Game.getState() instanceof LevelWonState);
    }

    @Test
    public void testLoseLevel() {
        try {
            Game.loseLevel(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(Game.getState() instanceof LevelLostState);
    }

    @Test
    public void testTimeUp() {
        try {
            Game.loseLevel(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(Game.getState() instanceof LevelLostState);
    }

}
