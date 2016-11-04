package panes;

import game.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import panes.elements.MarioButton;
import util.SceneManager;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MainMenu class is an javafx element which displays the main game menu.
 */
@SuppressWarnings("checkstyle:magicnumber")
public class MainMenu extends Pane {

    /**
     * The list containing the default level files in the game.
     */
    private static final List<String> DEFAULT_LEVELS = new ArrayList<>();
    static {
        DEFAULT_LEVELS.add("src/main/resources/levels/level1.json");
        DEFAULT_LEVELS.add("src/main/resources/levels/level2.json");
        DEFAULT_LEVELS.add("src/main/resources/levels/level3.json");
        DEFAULT_LEVELS.add("src/main/resources/levels/level4.json");
        DEFAULT_LEVELS.add("src/main/resources/levels/level5.json");
    }

    /**
     * Create a new menu element with all sub nodes.
     * @param stage the stage this menu is drawn on.
     */
    public MainMenu(Stage stage) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().addAll(
                createSinglePlayerButton(),
                createMultiPlayerButton(),
                createSettingsButton(),
                createQuitButton());

        setBackground(new Background(createBackgroundImage()));
    }

    /**
     * Creates the background.
     * @return backgroundImage.
     */
    private BackgroundImage createBackgroundImage() {
        Image image = new Image("images/menu.jpg");
        return new BackgroundImage(image, null, null, null, null);
    }

    /**
     * Button to start a singlePlayerGame.
     * @return Button singlePlayerGame.
     */
    private Button createSinglePlayerButton() {
        Button button = new MarioButton("Start Single Player Game",
                event -> startGame(DEFAULT_LEVELS, 1));
        button.setLayoutX(180);
        button.setLayoutY(224);

        return button;
    }

    /**
     * Button to start a multiPlayerGame.
     * @return Button multiPlayerGame.
     */
    private Button createMultiPlayerButton() {
        Button button = new MarioButton("Start Multi Player Game",
                event -> startGame(DEFAULT_LEVELS, 2));
        button.setLayoutX(180);
        button.setLayoutY(282);

        return button;
    }

    /**
     * Creates a settings button.
     * @return Button Settings button.
     */
    private Button createSettingsButton() {
        Button button = new MarioButton("Settings",
                event -> SceneManager.goToScene("SettingsMenu"));
        button.setLayoutX(180);
        button.setLayoutY(342);

        return button;
    }

    /**
     * Button to quit the game.
     * @return Button, Quit button.
     */
    private Button createQuitButton() {
        Button button = new MarioButton("Quit",
                event -> Platform.exit());
        button.setLayoutX(180);
        button.setLayoutY(402);

        return button;
    }

    /**
     * Start a new game.
     * @param levels      List<String> of levels that the game contains.
     * @param playerCount int with amount of players.
     */
    private void startGame(List<String> levels, int playerCount) {
        Game.setLevelsFromFiles(levels);
        Game.setPlayerCount(playerCount);
        SceneManager.goToScene("Game");

        try {
            Game.start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
