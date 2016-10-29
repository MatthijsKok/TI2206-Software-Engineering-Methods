package game.state;

import game.Game;
import javafx.scene.layout.Pane;
import level.Level;
import util.SceneManager;
import util.logging.Logger;

import java.io.IOException;

/**
 * Abstract class for all game states.
 */
public abstract class GameState {

    /**
     * Sets the overlay in SceneManager.
     *
     * @param overlay Pane - The pane to draw as overlay.
     */
    /* default */ void setOverlay(Pane overlay) {
        if (overlay == null) {
            SceneManager.removeOverlay();
        } else {
            SceneManager.setOverlay(overlay);
        }
    }

    /**
     * Behaviour that should be executed when the settings button is pressed.
     */
    /* default */ void goToSettings() {
        SceneManager.goToScene("SettingsMenu");
    }

    /**
     * Behaviour that should be executed when the stop button is pressed.
     */
    /* default */ void goToMainMenu() {
        Game.stop();
    }

    /**
     * Unloads the old level and reloads the new level.
     * @param oldLevel The old level.
     * @param newLevel The new level.
     */
    /* default */ void goToLevel(final Level oldLevel, final Level newLevel) {
        try {
            oldLevel.unload();
            newLevel.load();
            Game.setState(new InProgressState(newLevel));
        } catch (IOException e) {
            Logger.getInstance().error(e.getMessage());
            Game.stop();
        }
    }
}
