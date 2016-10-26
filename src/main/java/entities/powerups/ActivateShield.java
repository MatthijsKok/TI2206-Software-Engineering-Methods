package entities.powerups;

import graphics.Sprite;
import util.sound.SoundEffect;

/**
 * Gives the images.player a shield that allows it to be hit one time.
 */
class ActivateShield extends AbstractInstantPowerUp {

    /**
     * The sprite of the shield power-up.
     */
    private static final Sprite SHIELD_SPRITE = new Sprite("images/powerUps/Shield.png");

    /**
     * Constructor for a new shield power-up.
     */
    ActivateShield() {
        setSprite(SHIELD_SPRITE);
    }

    @Override
    void applyEffect() {
        getTarget().activateShield();

        SoundEffect.SHIELD.play();
    }

}
