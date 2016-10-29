package game.state;

import game.Game;
import level.Level;
import panes.overlays.LevelLostOverlay;
import util.KeyboardInputManager;
import util.SceneManager;
import util.StageManager;

/**
 * State for when a level is won.
 */
public class LevelLostState extends AbstractLevelState {

    /**
     * The key that progresses the game to the next level.
     */
    private static final String RESTART_KEY = "ANY";

    /**
     * The level that is lost.
     */
    private final Level level;

    /**
     * Creates a new LevelLostState instance.
     * @param level Level - The level that is lost.
     */
    public LevelLostState(Level level) {
        this.level = level;
        //TODO: this should only be called if the state is not game over
        SceneManager.setOverlay(new LevelLostOverlay(StageManager.getStage(), this));
    }

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(RESTART_KEY)) {
            goToLevel(level, level);
            SceneManager.removeOverlay();
        }
    }

    /**
     *
     */
    public void handleRetry() {
        goToLevel(level, level);
    }
}
