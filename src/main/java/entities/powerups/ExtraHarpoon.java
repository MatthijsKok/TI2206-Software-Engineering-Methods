package entities.powerups;

import graphics.Sprite;

/**
 * Gives the player a extra rope to shoot.
 */
class ExtraHarpoon extends AbstractDuringPowerUp {

    /**
     * The sprite of the extra harpoon power-up.
     */
    private static final Sprite SPRITE = new Sprite("powerUps/extra_harpoon.png");

    /**
     * The duration of the extra harpoon power-up (in ms).
     */
    private static final long DURATION = 10000;

    /**
     * Creates a new extra harpoon power-up.
     */
    ExtraHarpoon() {
        super();
        setSprite(SPRITE);
        setDuration(DURATION);
    }

    /**
     * Increase the amount of ropes the entities.character can shoot.
     */
    /* default */ void enableEffect() {
        getTarget().increaseMaxHarpoonCount(1);
    }

    /**
     * Disables the effect of the specific power up.
     */
    /* default */ void disableEffect() {
        getTarget().increaseMaxHarpoonCount(-1);
    }

}
