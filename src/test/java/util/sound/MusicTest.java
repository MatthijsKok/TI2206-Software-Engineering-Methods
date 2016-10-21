package util.sound;

import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

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

//    @Test
//    public void testSetMusicValidURI() {
//        Music.setMusic("yoshi_falls.mp3");
//        assertNotNull(Music.getCurrentMusic());
//    }

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