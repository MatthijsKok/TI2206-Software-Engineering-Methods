package util;


import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * Class that manages all the sounds in the game.
 */
public final class SoundManager {

    /**
     * Path where the music is stored.
     */
    private static final String MUSIC_PATH = "src/main/resources/sounds/music/";

    /**
     * Path where the sound effects are stored.
     */
    private static final String SOUND_EFFECTS_PATH = "src/main/resources/sounds/soundEffects/";

    /**
     * The mediaplayer that plays the background music.
     */
    private static MediaPlayer mediaPlayer;

    /**
     * Background music that is currently playing.
     */
    private static Media music = null;

    /**
     * The volume at which the background is played.
     */
    private static double musicVolume = 1.0;

    /**
     * The volume at which the background is played.
     */
    private static double soundEffectsVolume = 1.0;

    /**
     * Empty constructor.
     */
    private SoundManager() { }

    /**
     * Plays the sound of the effect that is passed.
     * @param soundEffectName The name of the soundEffect file, for example 'effect.wav'.
     */
    public static void playSoundEffect(String soundEffectName) {
        AudioClip soundEffect = null;

        try {
            soundEffect = new AudioClip(Paths.get(MUSIC_PATH + soundEffectName).toUri().toString());
        }
        catch (Exception e) {
            System.err.println("You probably made a typo in the sound effect name: " + soundEffectName);
            e.printStackTrace();
        }

        if (soundEffect != null) {
            soundEffect.play(soundEffectsVolume);
        }
    }

    /**
     * Starts the music that is defined in the music property.
     */
    public static void startMusic() {
        if (music != null) {
            mediaPlayer.play();
        }
    }

    /**
     * Stops the music that is defined in the music property.
     */
    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    /**
     * Pauses the music.
     */
    public static void pauseMusic() {
        mediaPlayer.pause();
    }

    /**
     * Sets the music that will be played when the .startMusic() method is called.
     * @param musicName The name of the music file, for example 'music.mp3'.
     */
    public static void setMusic(String musicName) {
        try {
            SoundManager.music = new Media(Paths.get(MUSIC_PATH + musicName).toUri().toString());
            mediaPlayer = new MediaPlayer(music);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        catch (Exception e) {
            System.err.println("You probably made a typo in the music name: " + musicName);
            e.printStackTrace();
        }
    }

    /**
     * Sets the volume at which the music should be played.
     * @param musicVolume A double between 0 and 1, with 1 being full volume.
     */
    public static void setMusicVolume(double musicVolume) {
        if (musicVolume >= 0.0 && musicVolume <= 1.0) {
            SoundManager.musicVolume = musicVolume;
        }
    }

    /**
     * Sets the volume wat which the sound effects should be played.
     * @param soundEffectsVolume A double between 0 and 1, with 1 being full volume.
     */
    public static void setSoundEffectsVolume(double soundEffectsVolume) {
        if (musicVolume >= 0.0 && musicVolume <= 1.0) {
            SoundManager.soundEffectsVolume = soundEffectsVolume;
        }
    }
}
