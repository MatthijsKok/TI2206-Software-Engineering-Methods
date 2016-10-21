package util.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * Manages the playback of music in the game.
 */
public final class Music {

    /**
     * The mediaplayer that plays the background music.
     */
    private static MediaPlayer mediaPlayer;

    /**
     * Path where the music is stored.
     */
    private static final String MUSIC_PATH = "src/main/resources/sounds/music/";

    /**
     * The music that is currently playing.
     */
    private static Media currentMusic;

    /**
     * The volume at which the background is played.
     */
    private static double musicVolume = 1.0;

    /**
     * Empty constructor.
     */
    private Music() { }

    /**
     * Pauses the music.
     */
    public static void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }


    /**
     * Starts the music that is defined in the music property.
     */
    public static void startMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(musicVolume);
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
     * Sets the music that will be played when the .startMusic() method is called.
     * @param musicName The name of the music file, for example 'music.mp3'.
     */
    public static synchronized void setMusic(String musicName) {
        Music.stopMusic();

        try {
            Music.currentMusic = new Media(Paths.get(MUSIC_PATH + musicName).toUri().toString());
            mediaPlayer = new MediaPlayer(currentMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        catch (MediaException e) {
            System.err.println("You probably made a typo in the music name: " + musicName);
            mediaPlayer = null;
            currentMusic = null;
        }
    }

    /**
     * Sets the volume at which the music should be played.
     * @param musicVolume A double between 0 and 1, with 1 being full volume.
     */
    public static void setMusicVolume(double musicVolume) {
        if (musicVolume >= 0.0 && musicVolume <= 1.0) {
            Music.musicVolume = musicVolume;
        }
    }

    /**
     * @return The volume at which the music is being played.
     */
    public static double getMusicVolume() {
        return musicVolume;
    }

    /**
     * @return The MediaPlayer object controlling the music.
     */
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * @return The Media object containing the music currently played.
     */
    public static Media getCurrentMusic() {
        return currentMusic;
    }
}
