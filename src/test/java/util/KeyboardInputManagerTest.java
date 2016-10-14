package util;

import javafx.scene.Scene;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;

/**
 * This class tests the KeyboardInputManager test.
 */
public class KeyboardInputManagerTest {

    private Scene scene = Mockito.mock(Scene.class);

    @Test
    public void testAddScene() {
        KeyboardInputManager.addScene(scene);
    }

    @Test
    public void testAddSceneDuplicate() {
        KeyboardInputManager.addScene(scene);
    }

    @Test
    public void testKeyPressedNull() {
        assertFalse(KeyboardInputManager.keyPressed(null));
    }

}
