package panes;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Config;
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
        getChildren().add(createBackgroundMusicSlider());
    }

    private Button createBackButton() {
        Button button = new Button();
        button.setText("back");
        button.getStyleClass().add("back");
        button.setOnMouseClicked(e -> SceneManager.goBack());

        return button;
    }

    private Slider createBackgroundMusicSlider() {
        Slider slider = new Slider(0, 100, 100);
        //slider.setShowTickMarks(true);
        //slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(100);
        slider.setMinorTickCount(98);
        slider.setOnDragDone(dragEvent -> {

            Config.put("backgroundMusic", String.valueOf(slider.getValue()))
        });
        return slider;
    }
}
