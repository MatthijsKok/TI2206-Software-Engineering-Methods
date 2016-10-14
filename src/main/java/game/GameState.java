
package game;

import game.player.Player;
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
    private Game game;

    /**
     * Indicates whether the game is in progress.
     */
    private boolean inProgress = false;

    /**
     * Indicates the current level of the game.
     */
    private int currentLevel = 0;

    /**
     * Indicate whether the game has been won or lost.
     */
    private boolean won = false, lost = false;


    /**
     * Creates a new GameState handler.
     * @param game The game to handle the gameState for.
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

        if (!level.isWon() && !level.isLost() && kim.keyPressed(PAUSE_KEY)) {
            if (inProgress) {
                pause();
            } else {
                resume();
            }
        }

        if (kim.keyPressed(RESTART_KEY)) {
            if (won || lost) {
                game.stop();
            } else if (level.isWon()) {
                nextLevel();
                resume();
            } else if (level.isLost()) {
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
     * Resets the game state.
     */
    void reset() {
        getCurrentLevel().unload();
        currentLevel = 0;
        won = false;
        lost = false;
        game.getPlayers().forEach(Player::resetLives);
    }

    /**
     * Progresses to the next level.
     */
    private void nextLevel() {
        getCurrentLevel().unload();

        if (hasNextLevel()) {
            currentLevel++;
            getCurrentLevel().load();
        }
    }

    /**
     * @return indicates whether the game has a next level.
     */
    public boolean hasNextLevel() {
        return currentLevel < game.getLevels().size() - 1;
    }

    /**
     * @return the current level.
     */
    public Level getCurrentLevel() {
        return game.getLevels().get(currentLevel);
    }

    /**
     * Win the game.
     */
    public void win() {
        if (!lost) {
            won = true;
        }
        pause();
    }

    /**
     * Lose the game.
     */
    public void lose() {
        if (!won) {
            lost = true;
        }
        pause();
    }

    /**
     * @return Boolean indicating whether the game is won.
     */
    public boolean isWon() {
        return won;
    }

    /**
     * @return Boolean indicating whether the game is lost.
     */
    public boolean isLost() {
        return lost;
    }
}
