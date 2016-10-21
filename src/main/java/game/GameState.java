
package game;

import game.player.Player;
import level.Level;
import util.KeyboardInputManager;
import util.sound.MultiSoundEffect;
import util.sound.Music;
import util.sound.SoundEffect;
import util.SceneManager;

import java.io.IOException;
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
        KeyboardInputManager.addListener(this);
    }

    /**
     * Entry point for gameStateChanges.
     * @param observable the observable that is changed.
     * @param obj optional, the argument it passes.
     */
    public void update(Observable observable, Object obj) {
        if (observable instanceof KeyboardInputManager) {
            updateKeyboardInput();
        }
    }

    /**
     * Handles keyboard input.
     */
    private void updateKeyboardInput() {
        Level level = getCurrentLevel();

        if (!level.isWon() && !level.isLost() && KeyboardInputManager.keyPressed(PAUSE_KEY)) {
            toggleProgress();
        }

        if (KeyboardInputManager.keyPressed(RESTART_KEY)) {
            tryRestart(level);
        }
    }

    /**
     * @return Boolean indicating whether the game is in progress.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    private void toggleProgress() {
        if (inProgress) {
            pause();
            SoundEffect.PAUSE.play();
        } else {
            resume();
        }
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        inProgress = false;

        // Pause the music
        Music.pauseMusic();
    }

    /**
     * Pauses the game.
     */
    void resume() {
        inProgress = true;

        // Resume the music
        Music.startMusic();
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

    private void tryRestart(Level level) {
        if (won || lost) {
            game.stop();
            SceneManager.goToScene("MainMenu");
        } else if (level.isWon()) {
            nextLevel();
            resume();
        } else if (level.isLost()) {
            try {
                level.restart();
            } catch (IOException e) {
                game.stop();
            }
            resume();
        }
    }

    /**
     * Progresses to the next level.
     */
    private void nextLevel() {
        getCurrentLevel().unload();

        if (hasNextLevel()) {
            currentLevel++;
            try {
                getCurrentLevel().load();
            } catch (IOException e) {
                game.stop();
            }
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
            SoundEffect.GAME_WON.play();
        }
        pause();
    }

    /**
     * Lose the game.
     */
    public void lose() {
        if (!won) {
            lost = true;
            MultiSoundEffect.GAME_OVER.playRandom();
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
