package game.state;

import game.Game;
import level.Level;
import ui.LevelLostOverlay;
import util.KeyboardInputManager;

/**
 * State for when a level is won.
 */
public class LevelLostState implements GameState {

    /**
     * The key that progresses the game to the next level.
     */
    private static final String RESTART_KEY = "ANY";
    /**
     * The overlay that is drawn when a level is won.
     */
    private static final LevelLostOverlay OVERLAY = new LevelLostOverlay();

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
    }

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(RESTART_KEY)) {
            Game.setState(new InProgressState(level));
        }
    }

    @Override
    public void draw() {
        OVERLAY.draw();
    }
}
