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
public class PausedState implements GameState {

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
     * The level this state is for.
     */
    private final Level level;

    /**
     * Creates a new PausedState.
     * @param level Level - The level that is paused.
     */
    /* default */ PausedState(Level level) {
        this.level = level;
        SceneManager.setOverlay(new PauseMenu(StageManager.getStage(), this));

    }

    /**
     * Behaviour that should be executed when the resume button is pressed.
     */
    public void handleResume() {
        Game.setState(new InProgressState(level));
        Music.startMusic();
    }

    /**
     * Behaviour that should be executed when the stop button is pressed.
     */
    public void handleMenu() {
        Game.stop();
    }

    /**
     * Behaviour that should be executed when the settings button is pressed.
     */
    public void handleSettings() {
        SceneManager.goToScene("SettingsMenu");
    }
    
    @Override
    public void update(double timeDifference) {
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
