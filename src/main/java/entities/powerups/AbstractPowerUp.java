package entities.powerups;
import entities.character.Character;
import graphics.Sprite;

/**
 * Interface that all power-ups should implement.
 */
abstract class AbstractPowerUp {

    /**
     * The default power-up sprite.
     */
    private static final Sprite POWERUP_SPRITE = new Sprite("powerUps/speed_boost.png");

    /**
     * The character which picked up the power-up.
     */
    private Character target;

    /**
     * The sprite of the power-up.
     */
    private Sprite sprite;

    /**
     * Creates a new ExtraTime power-up.
     */
    AbstractPowerUp() {
        setSprite(POWERUP_SPRITE);
    }

    /**
     * Enables the effect of the specific power-up.
     * @param character The character that picked up the power-up.
     */
    /* default */ void setTarget(final Character character) {
        target = character;
    }

    /**
     * @return The character which picked up the power-up.
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
