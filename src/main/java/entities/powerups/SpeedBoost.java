package entities.powerups;

import graphics.Sprite;
import util.sound.SoundEffect;

/**
 * Speeds up the players movements.
 */
class SpeedBoost extends AbstractDuringPowerUp {

    /**
     * The applied speed boost in px/s.
     */
    private static final double SPEED_BOOST = 100;

    /**
     * Sprite of the speed boost power-up.
     */
    private static final Sprite SPEED_BOOST_SPRITE = new Sprite("images/powerUps/speed_boost.png");

    /**
     * Constructor of the speed boost power-up.
     */
    SpeedBoost() {
        setSprite(SPEED_BOOST_SPRITE);
    }

    @Override
    void enableEffect() {
        getTarget().increaseRunSpeed(SPEED_BOOST);
        SoundEffect.SPEED_BOOST.play();
    }

    @Override
    void disableEffect() {
        getTarget().increaseRunSpeed(-SPEED_BOOST);
    }
}
