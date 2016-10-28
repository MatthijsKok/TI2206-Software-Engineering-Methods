package panes.overlays;

import game.state.GameLostState;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class responsible for the look and functionality of the Game lost overlay.
 */
@SuppressWarnings("magicnumber")
public class GameLostOverlay extends Pane {

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
    private final GameLostState state;

    /**
     * Create a new menu element with all sub nodes.
     * @param stage The stage this menu is drawn on.
     * @param state The state implementing behaviour for the buttons.
     */
    public GameLostOverlay(Stage stage, GameLostState state) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createMenuButton());
        getChildren().add(createQuitButton());
        getChildren().add(createYouLostLabel());
        this.state = state;
    }

    /**
     * Button to resume the game.
     * @return Button - Resume button
     */
    private Label createYouLostLabel() {
        Label label = new Label("Game Over...");
        label.setLayoutX(MENU_X_POSITION);
        label.setLayoutY(MENU_Y_POSITION - 50);
        label.idProperty().set("Label");

        return label;
    }

    /**
     * Button to go to the menu.
     * @return Button - Menu button
     */
    private Button createMenuButton() {
        Button button = new Button("Go To Menu");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION + 58);
        button.idProperty().set("menuButton");

        button.setOnMouseClicked(e -> state.handleMenu());
        return button;
    }

    /**
     * Button to quit the game.
     * @return Button - Quit button.
     */
    private Button createQuitButton() {
        Button button = new Button("Quit");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION);
        button.getStyleClass().add("green");
        button.idProperty().set("quitButton");

        button.setOnMouseClicked(e -> Platform.exit());

        return button;
    }
}
