package entities.powerups;

import entities.character.Character;

/**
 * Represents an AbstractPowerUp which effect is instant.
 */
abstract class AbstractInstantPowerUp extends AbstractPowerUp {
    /**
     * Enables the effect of the specific power up.
     */
    abstract /* default */ void applyEffect();

    /**
     * Enables the effect of the specific power-up.
     * @param character The entities.character that picked up the power-up.
     */
    /* default */ void setTarget(final Character character) {
        super.setTarget(character);
        applyEffect();
    }
}
