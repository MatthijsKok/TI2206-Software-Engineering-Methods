package panes;

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
 * Class responsible for the look and functionality of the Pausemenu.
 */
@SuppressWarnings("magicnumber")
public class PauseMenu extends Pane {

    /**
     * The X coordinate of the menu.
     */
    private static final double MENU_X_POSITION = 430;

    /**
     * The Y coordinate of the menu.
     */
    private static final double MENU_Y_POSITION = 200;

    /**
     * Create a new menu element with all sub nodes.
     * @param stage the stage this menu is drawn on.
     */
    public PauseMenu(Stage stage) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createResumeButton());
        getChildren().add(createMenuButton());
        getChildren().add(createSettingsButton());
        getChildren().add(createQuitButton());
    }

    /**
     * Button to start a singlePlayerGame.
     * @return Button - singlePlayerGame
     */
    private Button createResumeButton() {
        Button button = new Button("Resume");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION);
        button.getStyleClass().add("green");
        button.idProperty().set("singlePlayerButton");

        return button;
    }

    /**
     * Button to start a multiPlayerGame.
     * @return Button - multiPlayerGame
     */
    private Button createMenuButton() {
        Button button = new Button("Go To Menu");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION + 58);
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
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION + 116);
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
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION + 174);
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
