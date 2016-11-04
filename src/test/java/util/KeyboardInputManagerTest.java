package util;

import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.UtilityClassTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * This class tests the KeyboardInputManager test.
 */
public class KeyboardInputManagerTest {

    private Scene scene = Mockito.mock(Scene.class);

    @Before
    public void setUp() {
        KeyboardInputManager.addScene(scene);
    }

    @Test
    public void testKeyPressedNull() {
        assertFalse(KeyboardInputManager.keyPressed(null));
    }

    @Test
    public void testPressKey() {
        pressKey(KeyCode.B);
        assertThat(KeyboardInputManager.keyPressed("B"), is(true));
    }

    @Test
    public void testKeyDown() {
        pressKey(KeyCode.A);
        assertThat(KeyboardInputManager.keyDown("A"), is(true));
    }

    @Test
    public void testUpdate() {
        pressKey(KeyCode.A);
        releaseKey(KeyCode.B);
        KeyboardInputManager.update();
        assertThat(KeyboardInputManager.keyPressed("A"), is(false));
        assertThat(KeyboardInputManager.keyReleased("B"), is(false));
    }

    private void pressKey(KeyCode code) {
        handleKeyEvent(KeyEvent.KEY_PRESSED, code);
    }

    private void releaseKey(KeyCode code) {
        handleKeyEvent(KeyEvent.KEY_RELEASED, code);
    }

    private void handleKeyEvent(EventType<KeyEvent> type,
                                KeyCode code) {
        KeyEvent event = new KeyEvent(
                type, "", "", code,
                false, false, false, false);
        scene.getOnKeyPressed().handle(event);
    }

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(KeyboardInputManager.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}
