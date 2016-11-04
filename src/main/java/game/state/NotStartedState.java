package game.state;

import javafx.application.Platform;
import util.StageManager;

/**
 * Dummy state for when the game is not yet playing.
 */
public class NotStartedState implements GameState {

    /**
     * Creates a new NotStartedState instance.
     */
    public NotStartedState() {
        GameStateHelper.setOverlay(null);
        Platform.runLater(() ->
                StageManager.getStage().setTitle("Super Mario Bubble Trouble"));
    }
}
