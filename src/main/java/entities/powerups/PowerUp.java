package entities.powerups;
import level.Level;

public interface PowerUp {

    Character character = null;

    /**
     * Enables the effect of the specific power up.
     */
    public void enableEffect();

    /**
     * Applies the effect of the specific power up.
     */
    public void disableEffect();

}
