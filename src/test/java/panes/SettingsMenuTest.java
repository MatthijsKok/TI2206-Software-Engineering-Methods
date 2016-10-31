package panes;

import main.BubbleTroubleApplicationTest;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;
import util.CanvasManager;
import util.StageManager;
import util.sound.Music;
import util.sound.SoundEffect;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the settings menu.
 */
public class SettingsMenuTest extends BubbleTroubleApplicationTest {
    private SettingsMenu menu;

    @Override
    public void start(Stage stage) throws Exception {
        StageManager.init(stage);
        Canvas canvas = CanvasManager.createCanvas(stage);
        CanvasManager.setCanvas(canvas);
        menu = new SettingsMenu(stage);
    }

    @Test
    public void testBGSlider() {
        Slider slider = (Slider) ((Pane) menu.getChildren().get(1)).getChildren().get(1);
        slider.setValue(0.5);
        assertThat(Music.getMusicVolume(), is(0.5));
    }

    @Test
    public void testSFXSlider() {
        Slider slider = (Slider) ((Pane) menu.getChildren().get(2)).getChildren().get(1);
        slider.setValue(0.5);
        slider.setValueChanging(true);
        slider.setValueChanging(false);
        assertThat(SoundEffect.getSoundEffectsVolume(), is(0.5));
    }

}
