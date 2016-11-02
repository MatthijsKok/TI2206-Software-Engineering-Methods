package util.sound;

import javafx.embed.swing.JFXPanel;
import main.UtilityClassTest;
import org.junit.Before;
import org.junit.Test;
import util.JSONParser;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test for the music class
 */
public class MusicTest {

    @Before
    public void setUp() {
        // used for initialisation of classes needed for testing
        new JFXPanel();
    }

    @Test
    public void testSetMusicInvalidURI() {
        Music.setMusic("does_not_exist.wav");
        assertNull(Music.getCurrentMusic());
    }

    @Test
    public void testSetInvalidSoundEffectVolume() {
        Music.setMusicVolume(1);
        Music.setMusicVolume(5);
        assertEquals(1, Music.getMusicVolume(), 0);
    }

    @Test
    public void testSetValidSoundEffectVolume() {
        Music.setMusicVolume(0.5);
        assertEquals(0.5, Music.getMusicVolume(), 0);
    }

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(Music.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}