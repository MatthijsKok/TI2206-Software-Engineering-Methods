package game.state;

import game.Game;
import level.Level;
import util.logging.Logger;

import java.io.IOException;

/**
 * Super class for LevelLostState and LevelWonState.
 */
abstract class AbstractLevelState implements GameState {

    /**
     * Creates a new AbstractLevelState instance.
     */
    AbstractLevelState() {
        // Does nothing.
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
