package game.state;

import panes.OverlayMenuBuilder;

/**
 * State for when the game is won.
 */
public class GameWonState extends GameState {

    /**
     * Creates a new GameWonState instance.
     */
    public GameWonState() {
        OverlayMenuBuilder builder = new OverlayMenuBuilder();

        builder.setTitle("You won the game!");
        builder.addItem("Main menu", event -> goToMainMenu());

        setOverlay(builder.build());
    }
}
