package panes;

import game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import util.CanvasManager;
import util.StageManager;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for BubbleTroubleMenu
 */
public class BubbleTroubleMenuTest extends ApplicationTest {

    private MainMenu menu;
    private Game game;

    @Override
    public void start(Stage stage) throws Exception {
        StageManager.init(stage);
        Canvas canvas = CanvasManager.createCanvas(stage);
        CanvasManager.setCanvas(canvas);
        menu = new MainMenu(stage);

    }

    @Before
    public void setUp() {
        game = Game.getInstance();
    }

    @After
    public void tearDown() throws TimeoutException {
        game.stop();
        release(new MouseButton[] {});
    }

    @Test
    public void testSinglePlayerButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(0));
        assertTrue(menu.getChildren().get(0) instanceof Button);
    }

    @Test
    public void testMultiPlayerButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(1));
        assertTrue(menu.getChildren().get(1) instanceof Button);
    }

    @Test
    public void testSettingsButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(2));
        assertTrue(menu.getChildren().get(2) instanceof Button);
    }

    @Test
    public void testQuitButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(3));
        assertTrue(menu.getChildren().get(3) instanceof Button);
    }

//    @Test
//    public void testOnClickSinglePlayerButtons() {
//        clickOn("#singlePlayerButton");
//        assertThat(game.getPlayerCount(), is(1));
//    }
//
//    @Test
//    public void testOnClickMultiPlayerButtons() {
//        clickOn("#multiPlayerButton");
//        assertThat(game.getPlayerCount(), is(2));
//    }
}
