package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import menu.BubbleTroubleMenu;
import util.CanvasManager;
import util.StageManager;
import util.logging.LogLevel;
import util.logging.Logger;

/**
 * Bubble Trouble is a game written in JavaFX.
 */
public class BubbleTrouble extends Application {
    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = Logger.getInstance();

    /**
     * Entry method of the whole game.
     * Loads the config file and starts the game.
     * @param args optional arguments to start the game with.
     */
    public static void main(final String... args) {
        launch(args);
    }

    /**
     * This method opens a new window and starts the game.
     * @param stage the window in which the game will be displayed.
     */
    public final void start(final Stage stage) {
        LOGGER.setLevel(LogLevel.INFO);
        StageManager.init(stage);
        ObservableList<Node> children = StageManager.getRoot().getChildren();

        Canvas canvas = CanvasManager.createCanvas(stage);
        canvas.setVisible(false);

        children.add(new BubbleTroubleMenu());
        children.add(canvas);

        CanvasManager.setCanvas(canvas);
        LOGGER.info("App started");
    }

}
