package game.state;

import game.Game;
import level.Level;

import java.io.IOException;

/**
 * Super class for LevelLostState and LevelWonState.
 */
abstract class LevelState implements GameState {

    /**
     * Unloads the old level and reloads the new level.
     * @param oldLevel The old level.
     * @param newLevel The new level.
     */
    /* default */ void goToLevel(Level oldLevel, Level newLevel) {
        try {
            oldLevel.unload();
            newLevel.load();
            Game.setState(new InProgressState(newLevel));
        } catch (IOException e) {
            e.printStackTrace();
            Game.stop();
        }
    }
}
