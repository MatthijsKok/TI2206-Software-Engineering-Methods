package panes.elements;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

/**
 * This class is a slider that controls music volume.
 */
public class MusicSlider extends Pane {

    /**
     * The steps between to consecutive volume values.
     */
    private static final double VOLUME_STEP_SIZE = 0.1;

    /**
     * The slider in this pane.
     */
    private final Slider slider;

    /**
     * Creates a new music slider.
     * @param label the name of the slider.
     */
    public MusicSlider(String label) {
        slider = createSlider();
        getChildren().addAll(
                createLabel(label),
                slider
        );
    }

    private Label createLabel(String label) {
        return new Label(label);
    }

    private Slider createSlider() {
        Slider slider = new Slider();
        slider.setLayoutY(32);

        slider.setMin(0);
        slider.setMax(1);
        slider.setBlockIncrement(VOLUME_STEP_SIZE);

        return slider;
    }

    /**
     * Gets the slider out of this pane.
     * @return the slider.
     */
    public Slider getSlider() {
        return slider;
    }
}
