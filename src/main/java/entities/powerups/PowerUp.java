package entities.powerups;
import entities.Character;
import graphics.Sprite;

/**
 * Interface that all power-ups should implement.
 */
abstract class PowerUp {

    /**
     * The character which picked up the power-up.
     */
    private Character target;

    /**
     * The sprite of the power-up.
     */
    private Sprite sprite;

    /**
     * Enables the effect of the specific power-up.
     * @param character The character that picked up the power-up.
     */
    void setTarget(Character character) {
        target = character;
    }

    /**
     * @return The character which picked up the power-up.
     */
    Character getTarget() {
        return target;
    }

    /**
     * Sets the sprite of this power-up.
     * @param sprite The sprite to set.
     */
    void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @return The sprite of the power-up.
     */
    Sprite getSprite() {
        return sprite;
    }
}
