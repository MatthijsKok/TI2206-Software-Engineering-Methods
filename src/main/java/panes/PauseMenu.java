package panes;

import game.Game;
import game.state.PausedState;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.SceneManager;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Class responsible for the look and functionality of the Pause menu.
 */
@SuppressWarnings("magicnumber")
public class PauseMenu extends Pane {


    /**
     * The X coordinate of the menu.
     */
    private static final double MENU_X_POSITION = 180;

    /**
     * The Y coordinate of the menu.
     */
    private static final double MENU_Y_POSITION = 200;

    /**
     * The state used for implementing button behaviour.
     */
    private final PausedState state;

    /**
     * Create a new menu element with all sub nodes.
     * @param stage The stage this menu is drawn on.
     * @param state The state used to handle this menu's behaviour
     */
    public PauseMenu(Stage stage, PausedState state) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createResumeButton());
        getChildren().add(createMenuButton());
        getChildren().add(createSettingsButton());
        getChildren().add(createQuitButton());
        getChildren().add(createPausedLabel());
        this.state = state;
    }

    /**
     * Button to resume the game.
     * @return Button - Resume button
     */
    private Label createPausedLabel() {
        Label label = new Label("Game Paused");
        label.setLayoutX(MENU_X_POSITION);
        label.setLayoutY(MENU_Y_POSITION - 50);
        label.idProperty().set("pausedLabel");

        return label;
    }

    /**
     * Button to resume the game.
     * @return Button - Resume button
     */
    private Button createResumeButton() {
        Button button = new Button("Resume");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION);
        button.idProperty().set("resumeButton");

        button.setOnMouseClicked(e -> state.handleResume());

        return button;
    }

    /**
     * Button to go to the menu.
     * @return Button - Menu button
     */
    private Button createMenuButton() {
        Button button = new Button("Go To Menu");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION + 58);
        button.getStyleClass().add("green");
        button.idProperty().set("menuButton");

        button.setOnMouseClicked(e -> state.handleMenu());
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

        button.setOnMouseClicked(e -> state.handleSettings());

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
