package panes.overlays;

import game.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.SceneManager;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Overlay to be shown when the level is lost.
 */
@SuppressWarnings("magicnumber")
public class LevelLostOverlay extends Pane {

    /**
     * Create a new menu element with all sub nodes.
     * @param stage the stage this menu is drawn on.
     */
    public LevelLostOverlay(Stage stage) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createSinglePlayerButton());
        getChildren().add(createMultiPlayerButton());
        getChildren().add(createSettingsButton());
        getChildren().add(createQuitButton());
    }

    /**
     * Button to start a singlePlayerGame.
     * @return Button - singlePlayerGame
     */
    private Button createSinglePlayerButton() {
        Button button = new Button("Start Single Player Game");
        button.setLayoutX(180);
        button.setLayoutY(224);
        button.getStyleClass().add("green");
        button.idProperty().set("singlePlayerButton");

        return button;
    }

    /**
     * Button to start a multiPlayerGame.
     * @return Button - multiPlayerGame
     */
    private Button createMultiPlayerButton() {
        Button button = new Button("Start Multi Player Game");
        button.setLayoutX(180);
        button.setLayoutY(282);
        button.getStyleClass().add("green");
        button.idProperty().set("multiPlayerButton");


        return button;
    }

    /**
     * Creates a settings button.
     * @return Button - Settings button
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
     * @return Button - Quit button.
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
