package panes;

import game.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
        DEFAULT_LEVELS.add("src/main/resources/levels/level6.json");
    }

    /**
     * Create a new menu element with all sub nodes.
     * @param stage the stage this menu is drawn on.
     */
    public MainMenu(Stage stage) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createSinglePlayerButton());
        getChildren().add(createMultiPlayerButton());
        getChildren().add(createSettingsButton());
        getChildren().add(createQuitButton());
        setBackground(new Background(createBackgroundImage()));
    }

    /**
     * Creates the background.
     * @return backgroundImage
     */
    private BackgroundImage createBackgroundImage() {
        Image image = new Image("images/menu.jpg");
        return new BackgroundImage(image, null, null, null, null);
    }

    /**
     * Button to start a singlePlayerGame.
     * @return singlePlayerGame
     */
    private Button createSinglePlayerButton() {
        Button button = new Button("Start Singleplayer Game");
        button.setLayoutX(180);
        button.setLayoutY(224);
        button.getStyleClass().add("green");
        button.idProperty().set("singlePlayerButton");

        button.setOnMouseClicked(e -> startGame(DEFAULT_LEVELS, 1));
        return button;
    }

    /**
     * Button to start a multiPlayerGame.
     * @return multiPlayerGame
     */
    private Button createMultiPlayerButton() {
        Button button = new Button("Start Multiplayer Game");
        button.setLayoutX(180);
        button.setLayoutY(282);
        button.getStyleClass().add("green");
        button.idProperty().set("multiPlayerButton");

        button.setOnMouseClicked(e -> startGame(DEFAULT_LEVELS, 2));

        return button;
    }

    /**
     * Creates a settings button.
     * @return settings button
     */
    private Button createSettingsButton() {
        Button button = new Button("Settings");
        button.setLayoutX(180);
        button.setLayoutY(342);
        button.getStyleClass().add("green");

        button.setOnMouseClicked(e -> SceneManager.goToScene("SettingsMenu"));

        button.idProperty().set("settingsButton");
        return button;
    }

    /**
     * Button to quit the game.
     * @return Quit
     */
    private Button createQuitButton() {
        Button button = new Button("Quit");
        button.setLayoutX(180);
        button.setLayoutY(402);
        button.getStyleClass().add("green");
        button.idProperty().set("quitButton");

        button.setOnMouseClicked(e -> Platform.exit());

        return button;
    }

    private void startGame(List<String> levels, int playerCount) {
        Game game = Game.getInstance();
        game.setPlayerCount(playerCount);
        game.setLevelsFromFiles(levels);
        SceneManager.goToScene("Game");

        try {
            game.start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
