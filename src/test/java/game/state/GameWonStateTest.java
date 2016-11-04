package game.state;

import javafx.application.Platform;
import org.junit.Before;
import org.junit.Test;
import util.SceneManager;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the GameWonState class.
 */
public class GameWonStateTest extends AbstractGameStateTest {

    private List<Integer> scores = new ArrayList<>();

    @Before
    public void setUp() {
        scores.add(10);
    }

    @Test
    public void testConstructor() {
        Platform.runLater(() -> {
            new GameWonState(scores);
            assertChildAmount(4);
        });
    }

    @Test
    public void testFire() {
        Platform.runLater(() -> {
            new GameWonState(scores);
            fireButton(0);
            assertThat(SceneManager.getCurrentScene(),
                    is(SceneManager.getScene("MainMenu")));
        });
    }
}
