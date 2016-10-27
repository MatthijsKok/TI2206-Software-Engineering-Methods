package game.state;

import game.Game;
import level.Level;
import ui.LevelWonOverlay;
import util.KeyboardInputManager;

/**
 * State for when a level is won.
 */
public class LevelWonState implements GameState {

    /**
     * The key that progresses the game to the next level.
     */
    private static final String NEXT_LEVEL_KEY = "ANY";
    /**
     * The overlay that is drawn when a level is won.
     */
    private static final LevelWonOverlay OVERLAY = new LevelWonOverlay();

    /**
     * The level that comes after the level that is just won.
     */
    private final Level nextLevel;

    /**
     * Creates a new LevelWonState.
     * @param nextLevel Level - The level after the level that
     *                  is just won.
     */
    public LevelWonState(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(NEXT_LEVEL_KEY)) {
            Game.setState(new InProgressState(nextLevel));
        }
    }

    @Override
    public void draw() {
        OVERLAY.draw();
    }
}
