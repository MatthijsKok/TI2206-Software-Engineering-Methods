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
    private static final Sprite POWER_UP_SPRITE = new Sprite("images/powerUps/speed_boost.png");

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
        setSprite(POWER_UP_SPRITE);
    }

    /**
     * Activates the effect of this power-up.
     */
    protected abstract void activate();

    /**
     * Enables the effect of the specific power-up.
     * @param character Character - character that picked up the power-up.
     */
    /* default */ final void setTarget(final Character character) {
        target = character;
        activate();
    }

    /**
     * @return The character which picked up the power-up.
     */
    /* default */ final Character getTarget() {
        return target;
    }

    /**
     * Sets the sprite of this power-up.
     * @param sprite Sprite - sprite to set.
     */
    protected final void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * @return Sprite - sprite of the power-up.
     */
    protected final Sprite getSprite() {
        return sprite;
    }
}
