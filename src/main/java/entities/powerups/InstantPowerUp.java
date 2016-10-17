package entities.powerups;

import entities.Character;

/**
 * Represents an PowerUp which effect is instant.
 */
abstract class InstantPowerUp extends PowerUp {
    /**
     * Enables the effect of the specific power up.
     */
    abstract void applyEffect();

    /**
     * Enables the effect of the specific power-up.
     * @param character The character that picked up the power-up.
     */
    void setTarget(Character character) {
        super.setTarget(character);
        applyEffect();
    }
}
