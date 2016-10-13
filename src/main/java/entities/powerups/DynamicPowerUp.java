package entities.powerups;

public interface DynamicPowerUp {

    /**
     * Time the power up lasts.
     */
    int timeRemaining = 0;

    /**
     * Returns the time the effect of the power up will still last.
     */
    public int getTimeRemaining();

    /**
     * Decreases the time the effect of the power up will still last.
     */
    public int decreaseTimeRemaining();

    /**
     * Applies the effect of the specific power up.
     */
    public void applyEffect();

}
