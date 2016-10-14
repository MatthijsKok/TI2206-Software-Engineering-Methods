package entities.powerups;
import entities.Character;

/**
 * Interface that all power-ups should implement.
 */
public interface PowerUp {

    /**
     * Enables the effect of the specific power up.
     * @param character The character that picked up the powerup
     */
    void enableEffect(Character character);

    /**
     * Disables the effect of the specific power up.
     * @param character The character that picked up the powerup
     */
   void disableEffect(Character character);

}
