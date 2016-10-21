package panes.elements;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import util.Config;

/**
 * This class is a slider that controls music volume.
 */
public class MusicSlider extends Pane {

    /**
     * The maximal of the volume.
     */
    private static final int MAX_VOLUME = 100;
    /**
     * The steps between to consecutive volume values.
     */
    private static final int VOLUME_STEP_SIZE = 10;

    /**
     * Creates a new music slider.
     * @param label the name of the slider.
     * @param configKey the key of the configuration file this slider handles.
     */
    public MusicSlider(String label, String configKey) {
        getChildren().addAll(
                createLabel(label),
                createSlider(configKey)
        );
    }

    private Label createLabel(String label) {
        return new Label(label);
    }

    private Slider createSlider(String configKey) {
        Slider slider = new Slider();
        slider.setLayoutY(32);

        slider.setMin(0);
        slider.setMax(MAX_VOLUME);
        slider.setBlockIncrement(VOLUME_STEP_SIZE);

        slider.setOnDragDone(e -> {

            Config.put(configKey, String.valueOf(slider.getValue()));
        });
        return slider;
    }
}
