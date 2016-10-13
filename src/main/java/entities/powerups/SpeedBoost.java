package entities.powerups;

import com.apple.laf.AquaButtonBorder;

public class SpeedBoost implements DynamicPowerUp {

    /**
     * Time the power up lasts.
     */
    int timeRemaining;

    /**
     * Returns the time the effect of the power up will still last.
     */
    @Override
    public int getTimeRemaining() {
        return 0;
    }

    /**
     * Decreases the time the effect of the power up will still last.
     */
    @Override
    public int decreaseTimeRemaining() {
        return 0;
    }

    /**
     * Applies the effect of the specific power up.
     */
    @Override
    public void applyEffect() {
    }

}
