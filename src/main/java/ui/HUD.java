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
        gc.setFill(Color.BLACK);
        gc.fillRect(
                MARGIN, canvas.getHeight() - HEIGHT - MARGIN,
                canvas.getWidth() - 2 * MARGIN, HEIGHT);

        // Inner time bar
        Level level = Game.getInstance().getCurrentLevel();
        double timePart = 1 - level.timeSpend / level.duration;

        gc.setFill(Color.RED);
        gc.fillRect(
                MARGIN + PADDING, canvas.getHeight() - HEIGHT - MARGIN + PADDING,
                (canvas.getWidth() - 2 * (PADDING + MARGIN)) * timePart, HEIGHT - 2 * PADDING);
    }
}
