package entities.powerups;

import graphics.Sprite;
import util.SoundManager;

/**
 * Speeds up the players movements.
 */
class SpeedBoost extends AbstractDuringPowerUp {

    /**
     * The applied speed boost in px/s.
     */
    private static final double SPEED_BOOST = 10;

    /**
     * Sprite of the speed boost power-up.
     */
    private static final Sprite SPEED_BOOST_SPRITE = new Sprite("powerUps/speed_boost.png");

    /**
     * The name of the sound effect that will be played when the power-up is picked up.
     */
    private static final String SOUND_EFFECT_NAME = "speed_boost.wav";

    /**
     * Constructor of the speed boost power-up.
     */
    SpeedBoost() {
        setSprite(SPEED_BOOST_SPRITE);
    }

    @Override
    void enableEffect() {
        getTarget().increaseRunSpeed(SPEED_BOOST);
        SoundManager.playSoundEffect(SOUND_EFFECT_NAME);
    }

    @Override
    void disableEffect() {
        getTarget().increaseRunSpeed(-SPEED_BOOST);
    }
}
