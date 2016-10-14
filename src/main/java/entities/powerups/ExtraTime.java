package entities.powerups;
import game.Game;
import level.Level;

public class ExtraTime implements PowerUp {

    /**
     * Enables the effect of the specific power up.
     */
    @Override
    public void enableEffect() {
        Level level = Game.getInstance().getState().getCurrentLevel();
        if (level.getTimeLeft() + 10.0 < level.getDuration()) {
            level.setDuration(level.getTimeLeft() + 10.0);
        }
        else{
            level.setDuration(level.getDuration());
        }
    }

    /**
     * Disables the effect of the specific power up.
     */
    @Override
    public void disableEffect() {}

}
