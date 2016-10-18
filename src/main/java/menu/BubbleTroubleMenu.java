package menu;

import game.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
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
        return new BackgroundImage(image, null, null, null, null);
    }

    /**
     * Button to start a singlePlayerGame.
     * @return singlePlayerGame
     */
    private Button createSinglePlayerButton() {
        Button buttonSP = new Button("Start single player game.");
        buttonSP.setLayoutX(190);
        buttonSP.setLayoutY(420);
        buttonSP.getStyleClass().add("green");

        buttonSP.setOnMouseClicked(e -> {
            Game game = Game.getInstance();
            game.setPlayerCount(1);
            game.setLevelsFromFiles(DEFAULT_LEVELS);
            startGame(game);
        });
        return buttonSP;
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

        button.setOnMouseClicked(e -> {
            Game game = Game.getInstance();
            game.setPlayerCount(2);
            game.setLevelsFromFiles(DEFAULT_LEVELS);
            startGame(game);
        });

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

        button.setOnMouseClicked(e -> Platform.exit());

        return button;
    }

    private void startGame(Game game) {
        try {
            game.start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
