package panes;

import game.Game;
import game.state.InProgressState;
import javafx.application.Platform;
import javafx.scene.control.Button;
import main.BubbleTroubleApplicationTest;
import org.junit.Before;
import org.junit.Test;
import panes.elements.MarioButton;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test class for BubbleTroubleMenu
 */
public class MainMenuTest extends BubbleTroubleApplicationTest {

    private MainMenu menu;

    @Before
    public void setUp() {
        menu = new MainMenu(getStage());
    }

    @Test
    public void testSinglePlayerButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(0));
        assertTrue(menu.getChildren().get(0) instanceof Button);
    }

    @Test
    public void testConstructor() {
        assertThat(menu.getChildren().size(), is(4));
    }

    @Test
    public void testStartGame() {
        Platform.runLater(() -> {
            ((MarioButton) menu.getChildren().get(0)).fire();

            assertTrue(Game.getState() instanceof InProgressState);
        });
    }
}
