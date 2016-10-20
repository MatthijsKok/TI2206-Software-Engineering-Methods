package panes;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.SceneManager;

/**
 * The settings menu.
 */
public class SettingsMenu extends Pane {

    /**
     * Creates a new settings menu.
     * @param stage the stage this menu is drawn on.
     */
    public SettingsMenu(Stage stage) {
        setPrefSize(stage.getWidth(), stage.getHeight());
        getChildren().add(createBackButton());
    }

    private Button createBackButton() {
        Button button = new Button();
        button.setText("back");
        button.setOnMouseClicked(e -> SceneManager.goBack());

        return button;
    }
}
