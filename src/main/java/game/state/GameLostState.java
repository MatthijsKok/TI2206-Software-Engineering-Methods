package game.state;

import panes.OverlayMenuBuilder;

/**
 * State for when the game is lost.
 */
public class GameLostState extends GameState {

    /**
     * Creates a new GameLostState instance.
     */
    public GameLostState() {
        OverlayMenuBuilder builder = new OverlayMenuBuilder();

        builder.setTitle("Game over...");
        builder.addItem("Main menu", event -> goToMainMenu());

        setOverlay(builder.build());
    }

}
