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
    private static final Sprite EXTRA_LIFE_SPRITE = new Sprite("powerUps/1-up.png");

    /**
     * The name of the sound effect that will be played when the power-up is picked up.
     */
    private static final String SOUND_EFFECT_NAME = "1-up.wav";

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
