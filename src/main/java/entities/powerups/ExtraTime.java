package entities.powerups;
import entities.Character;
import game.Game;
import level.Level;

/**
 * Power-up that adds extra time to the level time.
 */
public class ExtraTime implements PowerUp {


    /**
     * The amount of time in seconds that is added on picking up this powerup.
     */
    private static final double EXTRA_TIME = 10;

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect(Character character) {
        System.out.println("Extra time");

        Level level = Game.getInstance().getState().getCurrentLevel();
        if (level.getTimeLeft() + EXTRA_TIME < level.getDuration()) {
            level.setDuration(level.getTimeLeft() + EXTRA_TIME);
        }
        else {
            level.setDuration(level.getDuration());
        }
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    public void disableEffect(Character character) { }

}
