package entities.powerups;
/**
 * Speeds up the players movements.
 */
class SpeedBoost extends DuringPowerUp {

    /**
     * The applied speed boost in px/s.
     */
    private static final double SPEED_BOOST = 10;

    @Override
    void enableEffect() {
        getTarget().setRunSpeed(getTarget().getRunSpeed() + SPEED_BOOST);
    }

    @Override
    void disableEffect() {
        getTarget().setRunSpeed(getTarget().getRunSpeed() - SPEED_BOOST);
    }
}
