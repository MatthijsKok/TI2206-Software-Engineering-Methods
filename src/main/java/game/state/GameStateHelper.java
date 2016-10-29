package game.state;

import game.Game;
import javafx.scene.layout.Pane;
import level.Level;
import util.SceneManager;
import util.logging.Logger;

import java.io.IOException;

/**
 * Utility class that implements methods useful for game states.
 */
public final class GameStateHelper {

    /**
     * The pane in which the overlays should be placed.
     */
    private static Pane anchor;

    private GameStateHelper() {

    }

    /**
     * Sets the pane where game overlays should be drawn on.
     * @param anchor Pane - The pane where game overlays should
     *               be drawn on.
     */
    public static void setAnchor(final Pane anchor) {
        GameStateHelper.anchor = anchor;
    }

    /**
     * Sets the overlay in SceneManager.
     *
     * @param overlay Pane - The pane to draw as overlay.
     */
    /* default */ static void setOverlay(final Pane overlay) {
        if (!anchor.getChildren().isEmpty()) {
            anchor.getChildren().remove(0);
        }

        if (overlay != null) {
            anchor.getChildren().add(overlay);
        }
    }

    /**
     * Behaviour that should be executed when the settings button is pressed.
     */
    /* default */ static void goToSettings() {
        SceneManager.goToScene("SettingsMenu");
    }

    /**
     * Behaviour that should be executed when the stop button is pressed.
     */
    /* default */ static void goToMainMenu() {
        Game.stop();
    }

    /**
     * Unloads the old level and reloads the new level.
     * @param oldLevel The old level.
     * @param newLevel The new level.
     */
    /* default */ static void goToLevel(final Level oldLevel, final Level newLevel) {
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
