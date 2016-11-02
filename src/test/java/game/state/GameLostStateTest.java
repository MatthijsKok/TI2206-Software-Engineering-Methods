package game.state;

import org.junit.Test;
import util.SceneManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the GameLostState class.
 */
public class GameLostStateTest extends AbstractGameStateTest {
    @Test
    public void testConstructor() {
        new GameLostState();
        testChildAmount(2);
    }

    @Test
    public void testFire() {
        new GameLostState();
        fireButton(0);
        assertThat(SceneManager.getCurrentScene(),
                is(SceneManager.getScene("MainMenu")));
    }
}
