package game.state;

/**
 * Interface which defines the characteristics a game state
 * must implement.
 */
public interface GameState {

    /**
     * Handles updates of the game.
     * @param timeDifference Double - Time between now and
     *                       last update.
     */
    void update(double timeDifference);
}
