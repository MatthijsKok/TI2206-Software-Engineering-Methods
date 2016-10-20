package entities.powerups;

import graphics.Sprite;
import util.sound.SoundEffect;

/**
 * Gives the player a extra rope to shoot.
 */
class ExtraVine extends AbstractDuringPowerUp {

    /**
     * The sprite of the extra harpoon power-up.
     */
    private static final Sprite SPRITE = new Sprite("powerUps/extra_vine.png");

    /**
     * The duration of the extra harpoon power-up (in ms).
     */
    private static final long DURATION = 10000;

    /**
     * Creates a new extra harpoon power-up.
     */
    ExtraVine() {
        super();
        setSprite(SPRITE);
        setDuration(DURATION);
    }

    /**
     * Increase the amount of ropes the character can shoot.
     */
    /* default */ void enableEffect() {
        getTarget().increaseMaxHarpoonCount(1);
        SoundEffect.EXTRA_VINE.play();
    }

    /**
     * Disables the effect of the specific power up.
     */
    /* default */ void disableEffect() {
        getTarget().increaseMaxHarpoonCount(-1);
    }

}
