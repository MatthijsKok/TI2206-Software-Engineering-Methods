package util;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class tests the GameCanvasManger test.
 */
public class GameCanvasManagerTest extends ApplicationTest {

    private static GameCanvasManager gcm;

    @Override
    public void start(Stage stage) {
        StageManager.init(stage);
    }

    @Before
    public void setUp() {
        Platform.runLater(() -> gcm = GameCanvasManager.getInstance());
    }

    @Test
    public void testGetInstance() {
        Platform.runLater(() -> assertEquals(gcm, GameCanvasManager.getInstance()));
    }

    @Test
    public void testGetCanvas() {
        Platform.runLater(() -> assertNotNull(gcm.getCanvas()));
    }

    @Test
    public void testGetContext() {
        Platform.runLater(() -> assertNotNull(gcm.getContext()));
    }

    @Test
    public void testContextEqualsCanvasContext() {
        Platform.runLater(() -> assertEquals(gcm.getCanvas().getGraphicsContext2D(), gcm.getContext()));
    }
}
