package entities.powerups;

import graphics.Sprite;

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
     * Constructor of the speed boost power-up.
     */
    SpeedBoost() {
        setSprite(SPEED_BOOST_SPRITE);
    }

    @Override
    void enableEffect() {
        getTarget().increaseRunSpeed(SPEED_BOOST);
    }

    @Override
    void disableEffect() {
        getTarget().increaseRunSpeed(-SPEED_BOOST);
    }
}
