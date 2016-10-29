package game.state;

import level.Level;
import panes.overlays.LevelWonOverlay;
import util.KeyboardInputManager;
import util.SceneManager;
import util.StageManager;

/**
 * State for when a level is won.
 */
public class LevelWonState extends AbstractLevelState {

    /**
     * The key that progresses the game to the next level.
     */
    private static final String NEXT_LEVEL_KEY = "ANY";

    /**
     * The levels that come before and after the level that is
     * just won.
     */
    private final Level previousLevel, nextLevel;

    /**
     * Creates a new LevelWonState.
     *
     * @param previousLevel Level - The level
     * @param nextLevel Level - The level after the level that
     *                  is just won.
     */
    public LevelWonState(Level previousLevel, Level nextLevel) {
        this.previousLevel = previousLevel;
        this.nextLevel = nextLevel;
        SceneManager.setOverlay(new LevelWonOverlay(StageManager.getStage(), this));
    }

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(NEXT_LEVEL_KEY)) {
            goToLevel(previousLevel, nextLevel);
        }
    }

    /**
     * Method for behaviour of the next level button.
     */
    public void handleNextLevel() {
        goToLevel(previousLevel, nextLevel);
    }
}
