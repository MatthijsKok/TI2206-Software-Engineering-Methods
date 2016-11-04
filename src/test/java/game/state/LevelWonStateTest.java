package game.state;

import game.Game;
import level.Level;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test suite for the LevelWonState class.
 */
public class LevelWonStateTest extends AbstractGameStateTest {
    @Test
    public void testConstructor() {
        new LevelWonState(new Level("level1.json"), new Level("level2.json"));
        assertChildAmount(2);
    }

    @Test
    public void testFire() {
        Game.setState(new LevelWonState(Game.getCurrentLevel(), Game.getCurrentLevel()));
        fireButton(0);
        assertTrue(Game.getState() instanceof InProgressState);
    }
}
