package main;

import javafx.application.Application;
import javafx.stage.Stage;
import panes.GamePane;
import panes.MainMenu;
import panes.SettingsMenu;
import util.KeyboardInputManager;
import util.SceneManager;
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

        SceneManager.setStage(stage);

        SceneManager.addScene("MainMenu", new MainMenu(stage));
        SceneManager.addScene("SettingsMenu", new SettingsMenu(stage));
        SceneManager.addScene("Game", new GamePane(stage));

        SceneManager.goToScene("MainMenu");

        KeyboardInputManager.addScene(SceneManager.getScene("Game"));

        LOGGER.info("App started");
    }

}
