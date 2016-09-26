package util;

import javafx.scene.Group;
import javafx.scene.Scene;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the KeyboardInputManager test.
 */
public class KeyboardInputManagerTest {

    private KeyboardInputManager kim;
    private Scene scene = Mockito.mock(Scene.class);

    @Before
    public void setUp() {
        kim = KeyboardInputManager.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertEquals(kim, KeyboardInputManager.getInstance());
    }

    @Test
    public void testAddScene() {
        kim.addScene(scene);
    }

    @Test
    public void testAddSceneDuplicate() {
        kim.addScene(scene);
    }

    @Test
    public void testKeyPressedNull() {
        assertFalse(kim.keyPressed(null));
    }

}
