package entities.powerups;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a AbstractPowerUp which effect is lasting some time.
 */
abstract class AbstractDuringPowerUp extends AbstractPowerUp {

    /**
     * The default duration of a during power-up.
     */
    private static final long DEFAULT_DURATION = 30000;
    /**
     * the duration of this power-up.
     */
    private long duration = DEFAULT_DURATION;

    @Override
    void activate() {
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
