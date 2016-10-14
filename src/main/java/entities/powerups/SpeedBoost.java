package entities.powerups;
import entities.Character;

/**
 * Speeds up the players movements.
 */
public class SpeedBoost implements PowerUp {


    /**
     * The applied speed boost in px/s.
     */
    private static final double SPEED_BOOST = 10;

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect(Character character) {
        System.out.println("Speed boost");
        character.setRunSpeed(character.getRunSpeed() + SPEED_BOOST);
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    public void disableEffect(Character character) {
        character.setRunSpeed(character.getRunSpeed() - SPEED_BOOST);
    }
}
