package util.sound;


import javafx.scene.media.AudioClip;

import java.nio.file.Paths;

/**
 * Class that creates, stores and plays sound effects.
 */
public final class SoundEffect {

    // ------ SOUND EFFECTS --------

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
     * Sound effect played when the player is game over.
     */
    public static final SoundEffect GAME_OVER = new SoundEffect("game_over2.wav");

    /**
     * Sound effect played when a ball bounces.
     */
    public static final SoundEffect BALL_BOUNCE = new SoundEffect("ball_bounce.wav");

    /**
     * Sound that plays when time is almost up.
     */
    public static final SoundEffect TIME_ALMOST_UP = new SoundEffect("hurry_up2.wav");


    // -------- CLASS LOGIC --------
    /**
     * Path where the sound effects are stored.
     */
    private static final String SOUND_EFFECTS_PATH = "src/main/resources/sounds/soundEffects/";


    /**
     * The volume at which the background is played.
     */
    private static double soundEffectsVolume = 1.0;

    /**
     * The audio clip to  of this SoundEffect.
     */
    private AudioClip soundEffect;

    /**
     * Creates a new SoundEffect Object.
     * @param soundEffectName The name of the soundEffect file, for example 'effect.wav'.
     */
    SoundEffect(String soundEffectName) {
        try {
            soundEffect = new AudioClip(Paths.get(SOUND_EFFECTS_PATH + soundEffectName).toUri().toString());
        }
        catch (Exception e) {
            System.err.println("You probably made a typo in the sound effect name: " + soundEffectName);
            System.err.println("The sound will not be played in the game");
        }
    }

    /**
     * Plays the sound of the effect that is passed.
     */
    public void play() {
        if (soundEffect != null) {
            soundEffect.play(soundEffectsVolume);
        }
    }

    /**
     * Sets the volume at which all sound effects are played.
     * @param soundEffectsVolume A double between 0 and 1, with 1 being full volume.
     */
    public static void setSoundEffectsVolume(double soundEffectsVolume) {
        SoundEffect.soundEffectsVolume = soundEffectsVolume;
    }
}
