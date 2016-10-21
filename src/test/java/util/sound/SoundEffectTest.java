package util.sound;

import javafx.scene.media.MediaException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for the SoundEffect class
 */
public class SoundEffectTest {

    SoundEffect validSoundEffect;

    @Before
    public void setUp(){
        validSoundEffect = new SoundEffect("pause.wav");
    }

    @Test
    public void testContructorValidFilename() {
        assertTrue(validSoundEffect.getAudio() != null);
    }

    @Test(expected=MediaException.class)
    public void testConstructorInvalidFilename() {
        SoundEffect invalidSoundEffect = new SoundEffect("file_does_not_exist.mp3");
        invalidSoundEffect.getAudio();
    }

    @Test
    public void testSetInvalidSoundEffectVolume() {
        SoundEffect.setSoundEffectsVolume(5);
        assertEquals(1, SoundEffect.getSoundEffectsVolume(), 0);
    }

    @Test
    public void testSetValidSoundEffectVolume() {
        SoundEffect.setSoundEffectsVolume(0.5);
        assertEquals(0.5, SoundEffect.getSoundEffectsVolume(), 0);
    }
}