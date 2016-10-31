package game.state;

import level.Level;
import panes.OverlayMenuBuilder;

/**
 * State for when a level is lost.
 */
public class LevelLostState implements GameState {

    /**
     * Creates a new LevelLostState instance.
     * @param level Level - The level that is lost.
     * @param timeUp Boolean - Indicates whether the
     *               level is lost by timeUp or not.
     */
    public LevelLostState(final Level level, boolean timeUp) {
        OverlayMenuBuilder builder = new OverlayMenuBuilder();

        if (timeUp) {
            builder.setTitle("Time's up!");
        } else {
            builder.setTitle("You died...");
        }

        builder.addItem("Retry",
                event -> GameStateHelper.goToLevel(level, level));

        GameStateHelper.setOverlay(builder.build());
    }

}
