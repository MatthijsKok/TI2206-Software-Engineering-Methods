package game.state;

import game.Game;
import level.Level;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test suite for the LevelLostState class.
 */
public class LevelLostStateTest extends AbstractGameStateTest {
    @Test
    public void testConstructor() {
        new LevelLostState(new Level(""), false);
        assertChildAmount(2);
        assertTitle("You died...");
    }

    @Test
    public void testConstructorTimeUp() {
        new LevelLostState(new Level(""), true);
        assertChildAmount(2);
        assertTitle("Time's up!");
    }

    @Test
    public void testFire() {
        Game.setState(new LevelLostState(Game.getCurrentLevel(), false));
        fireButton(0);
        assertTrue(Game.getState() instanceof InProgressState);
    }
}
