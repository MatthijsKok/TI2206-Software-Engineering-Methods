package game.state;

/**
 * Dummy state for when the game is not yet playing.
 */
public class NotStartedState implements GameState {

    /**
     * Creates a new NotStartedState instance.
     */
    public NotStartedState() {
        GameStateHelper.setOverlay(null);
    }
}
