package game.state;

import level.Level;
import panes.OverlayMenuBuilder;

/**
 * State for when a level is won.
 */
public class LevelWonState implements GameState {

    /**
     * Creates a new LevelWonState.
     *
     * @param previousLevel Level - The level
     * @param nextLevel Level - The level after the level that
     *                  is just won.
     */
    public LevelWonState(Level previousLevel, Level nextLevel) {
        OverlayMenuBuilder builder = new OverlayMenuBuilder();

        builder.setTitle("You won the level!");
        builder.addItem("Next level",
                event -> GameStateHelper.goToLevel(previousLevel, nextLevel));

        GameStateHelper.setOverlay(builder.build());
    }
}
