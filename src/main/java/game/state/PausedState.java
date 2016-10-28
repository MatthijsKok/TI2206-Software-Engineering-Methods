package game.state;

import game.Game;
import level.Level;
import panes.PauseMenu;
import util.KeyboardInputManager;
import util.SceneManager;
import util.StageManager;
import util.sound.Music;

/**
 * State for when the game is paused.
 */
class PausedState implements GameState {

    /**
     * The key which resumes the game.
     */
    private static final String PAUSE_KEY = "P";
    /**
     * The key which goes to the settings menu.
     */
    private static final String SETTINGS_KEY = "S";
    /**
     * The key which returns to the main menu.
     */
    private static final String QUIT_KEY = "ESCAPE";

    /**
     * The overlay which is drawn when the game is paused.
     */
    private static final PauseMenu PAUSE_OVERLAY = new PauseMenu(StageManager.getStage());

    /**
     * The level this state is for.
     */
    private final Level level;

    /**
     * Creates a new PausedState.
     * @param level Level - The level that is paused.
     */
    /* default */ PausedState(Level level) {
        this.level = level;
    }
    
    @Override
    public void update(double timeDifference) {
        SceneManager.setOverlay(PAUSE_OVERLAY);
        if (KeyboardInputManager.keyPressed(PAUSE_KEY)) {
            Music.startMusic();
            Game.setState(new InProgressState(level));
        }

        if (KeyboardInputManager.keyPressed(SETTINGS_KEY)) {
            SceneManager.goToScene("SettingsMenu");
        }

        if (KeyboardInputManager.keyPressed(QUIT_KEY)) {
            Game.stop();
        }

    }
}
