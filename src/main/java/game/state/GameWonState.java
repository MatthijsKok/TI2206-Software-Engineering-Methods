package game.state;

import game.Game;
import panes.overlays.GameWonOverlay;
import util.KeyboardInputManager;

/**
 * State for when the game is won.
 */
public class GameWonState implements GameState {

    /**
     * The key to press to return to the main menu.
     */
    private static final String RETURN_KEY = "ANY";
    /**
     * The overlay that is drawn when the game is won.
     */
    private static final GameWonOverlay GAME_WON_OVERLAY = new GameWonOverlay();

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(RETURN_KEY)) {
            Game.stop();
        }
    }
}
