package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class tests the GameCanvasManger test.
 */
public class GameCanvasManagerTest {

    private GameCanvasManager gcm;

    @Before
    public void setUp() {
        gcm = GameCanvasManager.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertEquals(gcm, GameCanvasManager.getInstance());
    }

    @Test
    public void testGetCanvas() {
        assertNotNull(gcm.getCanvas());
    }

    @Test
    public void testGetContext() {
        assertNotNull(gcm.getContext());
    }

    @Test
    public void testContextEqualsCanvasContext() {
        assertEquals(gcm.getCanvas().getGraphicsContext2D(), gcm.getContext());
    }
}
