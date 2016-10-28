package game.state;

import game.Game;
import panes.overlays.GameLostOverlay;
import util.KeyboardInputManager;

/**
 * State for when the game is lost.
 */
public class GameLostState implements GameState {

    /**
     * The key to press to return to the main menu.
     */
    private static final String RETURN_KEY = "ANY";
    /**
     * The overlay that is drawn when the game is lost.
     */
    private static final GameLostOverlay OVERLAY = new GameLostOverlay();

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(RETURN_KEY)) {
            Game.stop();
        }
    }
}
