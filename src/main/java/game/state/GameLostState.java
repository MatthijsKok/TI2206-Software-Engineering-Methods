package game.state;

import game.Game;
import panes.overlays.GameLostOverlay;
import util.KeyboardInputManager;
import util.SceneManager;
import util.StageManager;

/**
 * State for when the game is lost.
 */
public class GameLostState implements GameState {

    /**
     * The key to press to return to the main menu.
     */
    private static final String RETURN_KEY = "ANY";

    /**
     * Creates a new LevelLostState instance.
     */
    public GameLostState() {
        SceneManager.setOverlay(new GameLostOverlay(StageManager.getStage(), this));
    }

    @Override
    public void update(double timeDifference) {
        if (KeyboardInputManager.keyPressed(RETURN_KEY)) {
            Game.stop();
        }
    }

    /**
     * Handles the behaviour of the menu button.
     */
    public void handleMenu() {
        Game.stop();
    }
}
