package util.sound;


import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaException;

import java.nio.file.Paths;

/**
 * Class that creates, stores and plays sound effects.
 */
public final class SoundEffect {

    /**
     * Sound effect for the extra life power-up.
     */
    public static final SoundEffect EXTRA_LIFE = new SoundEffect("1-up.wav");
    /**
     * Sound effect for the speed boost power-up.
     */
    public static final SoundEffect SPEED_BOOST = new SoundEffect("speed_boost.wav");
    /**
     * Sound effect for the shield power-up.
     */
    public static final SoundEffect SHIELD = new SoundEffect("shield.wav");
    /**
     * Sound effect for the extra time power-up.
     */
    public static final SoundEffect EXTRA_TIME = new SoundEffect("mario_time.wav");
    /**
     * Sound effect for pausing the game.
     */
    public static final SoundEffect PAUSE = new SoundEffect("pause.wav");
    /**
     * Sound effect for shooting.
     */
    public static final SoundEffect SHOOT = new SoundEffect("vine_shot.wav");
    /**
     * Sound effect played when a ball bounces.
     */
    public static final SoundEffect BALL_BOUNCE = new SoundEffect("ball_bounce.wav");
    /**
     * Sound effect played when time is almost up.
     */
    public static final SoundEffect TIME_ALMOST_UP = new SoundEffect("hurry_up.wav");
    /**
     * Sound effect that plays once all levels are completed.
     */
    public static final SoundEffect GAME_WON = new SoundEffect("game_won.wav");
    /**
     * Sound effect played when a vine is picked up.
     */
    public static final SoundEffect EXTRA_VINE = new SoundEffect("happy_mario2.wav");

    /**
     * Path where the sound effects are stored.
     */
    private static final String SFX_PATH = "src/main/resources/sounds/soundEffects/";
    /**
     * The volume at which the background is played.
     */
    private static double sfxVolume = 1.0;
    /**
     * The audio clip to  of this SoundEffect.
     */
    private final AudioClip audio;
    /**
     * counts how many times the sound has been played for sounds
     * that play once in x times.
     */
    private transient int playCount;

    /**
     * Creates a new SoundEffect Object.
     * @param soundEffectName The name of the audio file, for example 'effect.wav'.
     * @throws MediaException When the name is not valid.
     */
    SoundEffect(final String soundEffectName) throws MediaException {
        audio = new AudioClip(Paths.get(SFX_PATH + soundEffectName).toUri().toString());
    }

    /**
     * Plays the sound of the effect that is passed.
     */
    public void play() {
        if (audio != null) {
            audio.play(sfxVolume);
        }
    }

    /**
     * Plays the sound once every x times.
     * @param occurrenceRate The amount of times the playSometimes
     *                       function should be called before playing
     *                       the sound again.
     */
    public void playSometimes(final int occurrenceRate) {
        if (playCount % occurrenceRate == 0) {
            play();
        }
        playCount++;
    }

    /**
     * Sets the volume at which all sound effects are played.
     * @param sfxVolume A double between 0 and 1, with 1 being full volume.
     */
    public static void setSoundEffectsVolume(final double sfxVolume) {
        if (sfxVolume >= 0 && sfxVolume <= 1) {
            SoundEffect.sfxVolume = sfxVolume;
        }
    }

    /**
     * @return The AudioClip object related to this SoundEffect.
     */
    public AudioClip getAudio() {
        return audio;
    }

    /**
     * @return The volume at which the sound effects are being played.
     */
    public static double getSoundEffectsVolume() {
        return sfxVolume;
    }
}
