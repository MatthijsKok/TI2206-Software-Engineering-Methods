package entities.powerups;
import entities.Character;

public class SpeedBoost implements PowerUp {

    Character character;

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect() {
        character.setRunSpeed(character.getRunSpeed() + 10.0);
    }

    @Override
    public void disableEffect() {
        character.setRunSpeed(character.getRunSpeed() - 10.0);
    }

}
