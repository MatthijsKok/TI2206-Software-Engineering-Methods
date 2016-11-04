package game.state;

import javafx.application.Platform;
import org.junit.Test;
import util.SceneManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the GameWonState class.
 */
public class GameWonStateTest extends AbstractGameStateTest {
    @Test
    public void testConstructor() {
        new GameWonState();
        assertChildAmount(2);
    }

    @Test
    public void testFire() {
        Platform.runLater(() -> {
            new GameWonState();
            fireButton(0);
            assertThat(SceneManager.getCurrentScene(),
                    is(SceneManager.getScene("MainMenu")));
        });
    }
}
