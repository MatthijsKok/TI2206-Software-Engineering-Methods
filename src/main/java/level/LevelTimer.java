package level;

import util.sound.Music;
import util.sound.SoundEffect;

/**
 * A timer which handles all time related things for
 * a level.
 */
public class LevelTimer {
    /**
     * The default duration of a level.
     */
    private static final double DEFAULT_DURATION = 30;
    /**
     * The time that is left in the level when the time almost up should play.
     */
    private static final double TIME_ALMOST_UP = 2.1;

    /**
     * Duration of the level.
     */
    private final double duration;
    /**
     * Time spend on the level.
     */
    private transient double timeSpend;

    /**
     * Creates a new LevelTimer with default duration.
     */
    LevelTimer() {
        this.duration = DEFAULT_DURATION;
    }

    /**
     * Creates a new LevelTimer.
     * @param duration The duration of the level.
     */
    LevelTimer(final double duration) {
        this.duration = duration;
    }

    /**
     * Update the timer.
     * @param timeDifference Double - The time between now and the last
     *                       time the timer was updated.
     */
    protected void update(final double timeDifference) {
        timeSpend += timeDifference;

        timeAlmostUp();
    }

    /**
     * Resets the timer.
     */
    /* default */ void reset() {
        timeSpend = 0;
    }

    /**
     * @return The amount of seconds there is left to complete the level.
     */
    public double getTimeLeft() {
        return duration - timeSpend;
    }

    /**
     * @return The total amount of seconds a players has to complete the level.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Increases the amount of time left in the level.
     * @param extraTime The amount of time extra.
     */
    public void increaseTime(final double extraTime) {
        timeSpend = Math.max(0, timeSpend - extraTime);
    }

    /**
     * Plays a sound effect when the time of the level is almost up.
     */
    private void timeAlmostUp() {
        if (!SoundEffect.TIME_ALMOST_UP.getAudio().isPlaying()
                && getTimeLeft() < TIME_ALMOST_UP) {
            Music.pauseMusic();
            SoundEffect.TIME_ALMOST_UP.play();
        }
    }
}
