package main;

import javafx.application.Application;
import javafx.stage.Stage;
import panes.GamePane;
import panes.MainMenu;
import panes.SettingsMenu;
import util.Config;
import util.KeyboardInputManager;
import util.SceneManager;
import util.StageManager;
import util.logging.LogLevel;
import util.logging.Logger;
import util.sound.Music;
import util.sound.SoundEffect;

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

        loadSounds();

        StageManager.init(stage);

        SceneManager.setStage(stage);

        SceneManager.addScene("MainMenu", new MainMenu(stage));
        SceneManager.getScene("MainMenu").getStylesheets().add("stylesheets/mainMenu.css");
        SceneManager.addScene("SettingsMenu", new SettingsMenu(stage));
        SceneManager.getScene("SettingsMenu").getStylesheets().add("stylesheets/settingsMenu.css");
        SceneManager.addScene("Game", new GamePane(stage));

        SceneManager.goToScene("MainMenu");

        KeyboardInputManager.addScene(SceneManager.getScene("Game"));

        LOGGER.info("App started");
    }

    private static void loadSounds() {
        // Set volume
        String volume;
        volume = Config.get("bgVolume");
        if (volume == null) {
            volume = "0";
        }
        Music.setMusicVolume(Double.valueOf(volume));

        volume = Config.get("sfxVolume");
        if (volume == null) {
            volume = "0";
        }
        SoundEffect.setSoundEffectsVolume(Double.valueOf(volume));

        // Load sounds
        try {
            Class.forName("util.sound.SoundEffect");
            Class.forName("util.sound.MultiSoundEffect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Music.setMusic("gusty_garden.mp3");
        Music.startMusic();
    }

}
