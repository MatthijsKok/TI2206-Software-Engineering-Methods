package entities.powerups;

import entities.Character;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a PowerUp which effect is lasting some time.
 */
abstract class DuringPowerUp extends PowerUp {

    /**
     * The default duration of a during power-up.
     */
    private static final long DEFAULT_DURATION = 30;
    /**
     * the duration of this power-up.
     */
    private long duration = DEFAULT_DURATION;

    /**
     * Enables the effect of the specific power-up.
     * @param character The character that picked up the power-up.
     */
    void setTarget(Character character) {
        super.setTarget(character);
        enableEffect();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                disableEffect();
            }
        }, duration);
    }

    /**
     * Sets the duration of the power-up.
     * @param duration the duration of the power-up.
     */
    void setDuration(final long duration) {
        this.duration =  duration;
    }

    /**
     * Enables the effect of the specific power-up.
     */
    abstract void enableEffect();

    /**
     * Disables the effect of the specific power-up.
     */
    abstract void disableEffect();
}
