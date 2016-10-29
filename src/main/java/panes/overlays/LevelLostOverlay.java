package panes.overlays;

import game.state.LevelLostState;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Overlay to be shown when the level is lost.
 */
@SuppressWarnings("magicnumber")
public class LevelLostOverlay extends Pane {

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
    private final LevelLostState state;

    /**
     * Create a new menu element with all sub nodes.
     * @param stage The stage this menu is drawn on.
     * @param state The state implementing behaviour for the buttons.
     */
    public LevelLostOverlay(Stage stage, LevelLostState state) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createLevelLostLabel());
        getChildren().add(createRetryButton());
        this.state = state;
    }

    /**
     * Button to resume the game.
     * @return Button - Resume button
     */
    private Label createLevelLostLabel() {
        Label label = new Label("You Died!");
        label.setLayoutX(MENU_X_POSITION);
        label.setLayoutY(MENU_Y_POSITION - 50);
        label.idProperty().set("levelLostLabel");

        return label;
    }

    /**
     * Button to go to the menu.
     * @return Button - Menu button
     */
    private Button createRetryButton() {
        Button button = new Button("Retry");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION);
        button.idProperty().set("retryButton");

        button.setOnMouseClicked(e -> state.handleRetry());
        return button;
    }
}
