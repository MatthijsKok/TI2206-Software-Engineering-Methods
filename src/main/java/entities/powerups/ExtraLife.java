package entities.powerups;

import graphics.Sprite;
import util.sound.SoundEffect;

/**
 * Adds a life to the players total.
 */
class ExtraLife extends AbstractInstantPowerUp {

    /**
     * The sprite of the extra life power-up.
     */
    private static final Sprite EXTRA_LIFE_SPRITE = new Sprite("images/powerUps/1-up.png");

    /**
     * Creates a new ExtraLife power-up.
     */
    ExtraLife() {
        setSprite(EXTRA_LIFE_SPRITE);
    }

    @Override
    void applyEffect() {
        SoundEffect.EXTRA_LIFE.play();
        getTarget().increaseLife();
    }
}
