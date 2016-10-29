package game.state;

import game.Game;
import javafx.scene.layout.Pane;
import org.junit.Test;
import util.SceneManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

/**
 * Test suite for the GameStateHelper class.
 */
public class GameStateHelperTest {
    @Test
    public void testSetAnchor() {
        Pane pane = new Pane();
        GameStateHelper.setAnchor(pane);

        Pane overlay = new Pane();
        GameStateHelper.setOverlay(overlay);

        assertThat(pane.getChildren().get(0), is(overlay));
    }

    /*@Test
    public void testGoToSettings() {
        SceneManager.addScene("SettingsMenu", new Pane());

        GameStateHelper.goToSettings();
        assertThat(SceneManager.getCurrentScene(),
                is(SceneManager.getScene("SettingsMenu")));
    }

    @Test
    public void testGoToMainMenu() {
        GameStateHelper.goToMainMenu();

        assertTrue(Game.getState() instanceof NotStartedState);
    }*/

}
