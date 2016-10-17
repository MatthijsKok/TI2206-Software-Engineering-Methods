package entities.powerups;
import game.Game;
import level.Level;

/**
 * Power-up that adds extra time to the level time.
 */
class ExtraTime extends InstantPowerUp {

    /**
     * The amount of time in seconds that is added on picking up this powerup.
     */
    private static final double EXTRA_TIME = 10;

    private Level getLevel() {
        return Game.getInstance().getState().getCurrentLevel();
    }

    @Override
    void applyEffect() {
        Level level = getLevel();
        level.increaseTime(EXTRA_TIME);
    }
}
