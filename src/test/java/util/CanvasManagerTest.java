package util;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class tests the GameCanvasManger test.
 */
public class CanvasManagerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        StageManager.init(stage);
    }

    @Test
    public void testGetCanvas() {
        Platform.runLater(() -> assertNotNull(CanvasManager.getCanvas()));
    }

    @Test
    public void testGetContext() {
        Platform.runLater(() -> assertNotNull(CanvasManager.getContext()));
    }

    @Test
    public void testContextEqualsCanvasContext() {
        Platform.runLater(() -> assertEquals(CanvasManager.getCanvas().getGraphicsContext2D(), CanvasManager.getContext()));
    }
}
