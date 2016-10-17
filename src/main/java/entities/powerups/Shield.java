package entities.powerups;

import graphics.Sprite;

/**
 * Gives the player a shield that allows it to be hit one time.
 */
class Shield extends DuringPowerUp {

    /**
     * The sprite of the shield power-up.
     */
    private static final Sprite SHIELD_SPRITE = new Sprite("powerUps/shield.png");

    /**
     * Constructor for a new shield power-up.
     */
    Shield() {
        setSprite(SHIELD_SPRITE);
    }

    @Override
    void enableEffect() {
        getTarget().setInvincible(true);
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    void disableEffect() {
        getTarget().setInvincible(false);
    }

}
