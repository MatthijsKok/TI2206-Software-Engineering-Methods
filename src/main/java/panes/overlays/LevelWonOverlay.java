package panes.overlays;

import game.state.LevelWonState;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class responsible for the look of the level won overlay.
 */
@SuppressWarnings("magicnumber")
public class LevelWonOverlay extends Pane {

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
    private final LevelWonState state;

    /**
     * Create a new menu element with all sub nodes.
     * @param stage The stage this menu is drawn on.
     * @param state The state implementing behaviour for the buttons.
     */
    public LevelWonOverlay(Stage stage, LevelWonState state) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createNextLevelButton());
        getChildren().add(createLevelWonLabel());
        this.state = state;
    }

    /**
     * Button to resume the game.
     * @return Button - Resume button
     */
    private Label createLevelWonLabel() {
        Label label = new Label("Well Done!");
        label.setLayoutX(MENU_X_POSITION);
        label.setLayoutY(MENU_Y_POSITION - 50);
        label.idProperty().set("Label");

        return label;
    }

    /**
     * Button to go to the menu.
     * @return Button - Menu button
     */
    private Button createNextLevelButton() {
        Button button = new Button("Next Level");
        button.setLayoutX(MENU_X_POSITION);
        button.setLayoutY(MENU_Y_POSITION);
        button.idProperty().set("menuButton");

        button.setOnMouseClicked(e -> state.handleNextLevel());
        return button;
    }


}
