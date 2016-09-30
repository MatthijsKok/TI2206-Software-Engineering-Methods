package game;

import level.Level;
import util.KeyboardInputManager;

import java.util.Observable;
import java.util.Observer;

/**
 * Handles events like pausing and resuming the game.
 */
public class GameState implements Observer {

    /**
     * Key which toggles the pause state of the game.
     */
    private static final String PAUSE_KEY = "P";

    /**
     * Key which restarts the game.
     */
    private static final String RESTART_KEY = "R";

    /**
     * The game this instance handles the state for.
     */
    private final Game game;

    /**
     * Indicates whether the game is in progress.
     */
    private boolean inProgress = false;

    /**
     * Indicates the current level of the game.
     */
    private int currentLevel = 0;

    /**
     * Creates a new GameState handler.
     * @param game The game to handle the gamestate for.
     */
    GameState(Game game) {
        this.game = game;
        KeyboardInputManager.getInstance().addObserver(this);
    }

    /**
     * Entry point for gameStateChanges.
     * @param observable the observable that is changed.
     * @param obj optional, the argument it passes.
     */
    public void update(Observable observable, Object obj) {
        if (observable instanceof KeyboardInputManager) {
            updateKeyboardInput((KeyboardInputManager) observable);
        }
    }

    /**
     * Handles keyboard input.
     * @param kim the
     */
    private void updateKeyboardInput(KeyboardInputManager kim) {
        Level level = getCurrentLevel();

        if (!level.won() && !level.lost()) {
            if (kim.keyPressed(PAUSE_KEY)) {
                if (inProgress) {
                    pause();
                } else {
                    resume();
                }
            }
        }

        if (kim.keyPressed(RESTART_KEY)) {
            if (level.won()) {
                nextLevel();
                resume();
            } else if (level.lost()) {
                level.restart();
                resume();
            }
        }
    }

    /**
     * @return Boolean indicating whether the game is in progress.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        inProgress = false;
    }

    /**
     * Pauses the game.
     */
    void resume() {
        inProgress = true;
    }

    /**
     * Progresses to the next level.
     */
    private void nextLevel() {
        if (currentLevel < game.getLevels().size() - 1) {
            getCurrentLevel().unload();
            currentLevel++;
            getCurrentLevel().load();
        }
    }

    /**
     * @return the current level.
     */
    public Level getCurrentLevel() {
        return game.getLevels().get(currentLevel);
    }
}
