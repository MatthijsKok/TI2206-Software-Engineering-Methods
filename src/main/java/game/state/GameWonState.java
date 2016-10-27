package game.state;

import game.Game;
import ui.GameWonOverlay;
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
    private static final GameWonOverlay OVERLAY = new GameWonOverlay();

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(RETURN_KEY)) {
            Game.stop();
        }
    }

    @Override
    public void draw() {
        OVERLAY.draw();
    }
}
