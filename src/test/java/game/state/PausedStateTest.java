package game.state;

import game.Game;
import level.Level;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test suite for the PausedState class.
 */
public class PausedStateTest extends AbstractGameStateTest {
    @Test
    public void testConstructor() {
        new PausedState(new Level(""));
        assertChildAmount(4);
    }

    @Test
    public void testResume() {
        new PausedState(Game.getCurrentLevel());
        fireButton(0);

        assertTrue(Game.getState() instanceof InProgressState);
    }
}
