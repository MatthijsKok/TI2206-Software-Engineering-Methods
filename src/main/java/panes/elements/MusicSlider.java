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
     * Creates a new music slider.
     * @param label the name of the slider.
     */
    public MusicSlider(final String label) {
        super();

        Slider slider = createSlider();
        getChildren().addAll(
                createLabel(label),
                slider
        );
    }

    /**
     * Create a label.
     * @param label String label to create.
     * @return new Label with String label.
     */
    private Label createLabel(final String label) {
        return new Label(label);
    }

    /**
     * Creates a slider.
     * @return a slider with a min, max and step-size.
     */
    @SuppressWarnings("magicnumber")
    private Slider createSlider() {
        final Slider slider = new Slider();
        slider.setLayoutY(32);

        slider.setMin(0);
        slider.setMax(1);
        slider.setBlockIncrement(VOLUME_STEP_SIZE);

        return slider;
    }
}
