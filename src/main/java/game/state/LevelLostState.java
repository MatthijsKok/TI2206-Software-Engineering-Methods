package game.state;

import level.Level;
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
     * The overlay that is drawn when a level is won.
     */
    private static final panes.overlays.LevelLostOverlay OVERLAY = new panes.overlays.LevelLostOverlay(StageManager.getStage());

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
        SceneManager.setOverlay(OVERLAY);
    }

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(RESTART_KEY)) {
            goToLevel(level, level);
            SceneManager.removeOverlay();
        }
    }

}
