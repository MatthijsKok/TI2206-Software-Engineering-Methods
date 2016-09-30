package ui;

import game.Game;
import javafx.scene.paint.Color;
import level.Level;

/**
 * HUD is a acronym for Heads Up Display. A common name for the overlay of a game.
 */
public class HUD extends UIElement {

    /**
     * Height of the time bar.
     */
    private static final int HEIGHT = 24;

    /**
     * Padding around the time bar.
     */
    private static final int MARGIN = 8;

    /**
     * Padding around the time bar.
     */
    private static final int PADDING = 2;

    /**
     * Draws the lives that the first player of a level has left.
     */
    public void draw() {
        // Outer time bar
        GC.setFill(Color.BLACK);
        GC.fillRect(
                MARGIN, CANVAS.getHeight() - HEIGHT - MARGIN,
                CANVAS.getWidth() - 2 * MARGIN, HEIGHT);

        // Inner time bar
        Level level = Game.getInstance().getState().getCurrentLevel();
        double timePart = 1 - level.timeSpend / level.duration;

        GC.setFill(Color.RED);
        GC.fillRect(
                MARGIN + PADDING, CANVAS.getHeight() - HEIGHT - MARGIN + PADDING,
                (CANVAS.getWidth() - 2 * (PADDING + MARGIN)) * timePart, HEIGHT - 2 * PADDING);
    }
}
