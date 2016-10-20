package entities.powerups;
import entities.character.Character;
import graphics.Sprite;

/**
 * Interface that all power-ups should implement.
 */
abstract class AbstractPowerUp {

    /**
     * The entities.character which picked up the power-up.
     */
    private Character target;

    /**
     * The sprite of the power-up.
     */
    private Sprite sprite;

    /**
     * Enables the effect of the specific power-up.
     * @param character The entities.character that picked up the power-up.
     */
    /* default */ void setTarget(final Character character) {
        target = character;
    }

    /**
     * @return The entities.character which picked up the power-up.
     */
    /* default */ Character getTarget() {
        return target;
    }

    /**
     * Sets the sprite of this power-up.
     * @param sprite The sprite to set.
     */
    /* default */ void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @return The sprite of the power-up.
     */
    /* default */ Sprite getSprite() {
        return sprite;
    }
}
