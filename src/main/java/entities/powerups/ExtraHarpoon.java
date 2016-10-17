package entities.powerups;

import graphics.Sprite;

/**
 * Gives the player a extra rope to shoot.
 */
class ExtraHarpoon extends DuringPowerUp {

    /**
     * The sprite of the extra harpoon power-up.
     */
    private static final Sprite EXTRA_HARPOON_SPRITE = new Sprite("background.jpg");

    /**
     * Creates a new extra harpoon power-up.
     */
    ExtraHarpoon() {
        setSprite(EXTRA_HARPOON_SPRITE);
    }

    /**
     * Increase the amount of ropes the character can shoot.
     */
    void enableEffect() {
        getTarget().increaseMaxHarpoonCount(1);
    }

    /**
     * Disables the effect of the specific power up.
     */
    void disableEffect() {
        getTarget().increaseMaxHarpoonCount(-1);
    }

}
