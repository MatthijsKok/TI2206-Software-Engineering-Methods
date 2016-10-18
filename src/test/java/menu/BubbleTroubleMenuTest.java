package menu;

import bubbletrouble.BubbleTroubleApplicationTest;
import game.Game;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.util.WaitForAsyncUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import game.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import util.CanvasManager;
import util.StageManager;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;

/**
 * Test class for BubbleTroubleMenu
 */
public class BubbleTroubleMenuTest extends BubbleTroubleApplicationTest {

    private BubbleTroubleMenu menu;
    private Game game;

    private static final List<String> DEFAULT_LEVELS = new ArrayList<>();
    static {
        DEFAULT_LEVELS.add("src/main/resources/levels/level1.json");
        DEFAULT_LEVELS.add("src/main/resources/levels/level2.json");
    }

    @Before
    public void setUp(){
        menu = new BubbleTroubleMenu();
        game = new Game();
    }

    @Override
    public void start(Stage stage) {
        StageManager.init(stage);
        ObservableList<Node> children = StageManager.getRoot().getChildren();

        Canvas canvas = CanvasManager.createCanvas(stage);
        canvas.setVisible(false);

        children.add(new BubbleTroubleMenu());
        children.add(canvas);

        CanvasManager.setCanvas(canvas);
    }

    @Test
    public void testClickOnSinglePlayerButton() {
        Node singlePlayerButton = menu.getChildren().get(0);
        System.out.println(singlePlayerButton);

//        clickOn(singlePlayerButton.getLayoutX()+200,singlePlayerButton.getLayoutY()+300);
        WaitForAsyncUtils.waitForFxEvents();
        System.out.println(game.getPlayerCount());

//        assertTrue(game.getPlayerCount()==1);
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
}
