package game.state;

import game.Game;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import level.Level;
import main.BubbleTroubleApplicationTest;
import org.junit.Test;
import util.SceneManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

/**
 * Test suite for the GameStateHelper class.
 */
public class GameStateHelperTest extends BubbleTroubleApplicationTest {

    @Test
    public void testSetAnchor() {
        Pane pane = new Pane();
        GameStateHelper.setAnchor(pane);

        Pane overlay = new Pane();
        GameStateHelper.setOverlay(overlay);

        assertThat(pane.getChildren().get(0), is(overlay));
    }

    @Test
    public void testGoToSettings() {
        Platform.runLater(() -> {
            GameStateHelper.goToSettings();

            assertThat(SceneManager.getCurrentScene(),
                    is(SceneManager.getScene("SettingsMenu")));
        });
    }

    @Test
    public void testGoToMainMenu() {
        GameStateHelper.goToMainMenu();

        assertTrue(Game.getState() instanceof NotStartedState);
    }

    @Test
    public void testGoToLevel() {
        Level newLevel = new Level("src/main/resources/levels/level3.json");

        GameStateHelper.goToLevel(
                Game.getCurrentLevel(),
                newLevel);

        assertTrue(Game.getState() instanceof InProgressState);
    }

    @Test
    public void testGoToLevelStop() {
        GameStateHelper.goToLevel(
                Game.getCurrentLevel(),
                new Level("does_not_exist.json"));

        assertTrue(Game.getState() instanceof NotStartedState);
    }

}
