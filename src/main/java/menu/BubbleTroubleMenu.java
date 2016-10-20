package menu;

import game.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The menu.BubbleTroubleMenu class is an javafx element which displays the main game menu.
 */
@SuppressWarnings("checkstyle:magicnumber")
public class BubbleTroubleMenu extends Pane {

    /**
     * The list containing the default level files in the game.
     */
    private static final List<String> DEFAULT_LEVELS = new ArrayList<>();
    static {
        DEFAULT_LEVELS.add("src/main/resources/levels/level1.json");
        DEFAULT_LEVELS.add("src/main/resources/levels/level2.json");
    }

    /**
     * Create a new menu element with all sub nodes.
     */
    public BubbleTroubleMenu() {
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
        Image image = new Image("background.jpg");

        return new BackgroundImage(image, null, null,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    }

    /**
     * Button to start a singlePlayerGame.
     * @return singlePlayerGame
     */
    private Button createSinglePlayerButton() {
        Button button = new Button("Start single player game.");
        button.setLayoutX(190);
        button.setLayoutY(420);
        button.getStyleClass().add("green");
        button.idProperty().set("singlePlayerButton");

        button.setOnMouseClicked(
                e -> startGame(1, DEFAULT_LEVELS));
        return button;
    }

    /**
     * Button to start a multiPlayerGame.
     * @return multiPlayerGame
     */
    private Button createMultiPlayerButton() {
        Button button = new Button("Start multi player game.");
        button.setLayoutX(220);
        button.setLayoutY(480);
        button.getStyleClass().add("green");
        button.idProperty().set("multiPlayerButton");

        button.setOnMouseClicked(
                e -> startGame(2, DEFAULT_LEVELS));

        return button;
    }

    /**
     * Creates a settings button.
     * @return settings button
     */
    private Button createSettingsButton() {
        Button button = new Button("Settings");
        button.setLayoutX(920);
        button.setLayoutY(550);
        button.getStyleClass().add("green");
        button.idProperty().set("settingsButton");
        return button;
    }

    /**
     * Button to quit the game.
     * @return Quit
     */
    private Button createQuitButton() {
        Button button = new Button("Quit");
        button.setLayoutX(64);
        button.setLayoutY(550);
        button.getStyleClass().add("green");
        button.idProperty().set("quitButton");

        button.setOnMouseClicked(e -> Platform.exit());

        return button;
    }

    private void startGame(int playerCount, List<String> levels) {
        Game game = Game.getInstance();
        game.setPlayerCount(playerCount);
        game.setLevelsFromFiles(levels);

        try {
            game.start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
