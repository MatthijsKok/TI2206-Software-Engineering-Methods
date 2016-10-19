package util.sound;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Test for the music class
 */
public class MusicTest {

    @Before
    public void setUp() {
        // used for initialisation of classes needed for testing
        new JFXPanel();

        Music.setMusic("yoshi_falls.mp3");
    }

    @Test
    public void testSetMusicValidURI() {
        assertNotNull(Music.getCurrentMusic());
    }

    @Test(expected=MediaException.class)
    public void testSetMusicInvalidURI() {
        Music.setMusic("does_not_exist.wav");
    }

    @Test
    public void testSetInvalidSoundEffectVolume() {
        Music.setMusicVolume(5);
        assertEquals(1, Music.getMusicVolume(), 0);
    }

    @Test
    public void testSetValidSoundEffectVolume() {
        Music.setMusicVolume(0.5);
        assertEquals(0.5, Music.getMusicVolume(), 0);
    }
}