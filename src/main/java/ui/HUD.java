package ui;

import game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import level.Level;

/**
 * HUD is a acronym for Heads Up Display. A common name for the overlay of a game.
 */
class HUD extends UIElement {

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
     * @param canvas The Canvas to draw on
     * @param graphicsContext The GraphicsContext to draw on
     */
    void draw(Canvas canvas, GraphicsContext graphicsContext) {
        // Outer time bar
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(
                MARGIN, canvas.getHeight() - HEIGHT - MARGIN,
                canvas.getWidth() - 2 * MARGIN, HEIGHT);

        // Inner time bar
        final Level level = Game.getInstance().getState().getCurrentLevel();
        final double timePart = level.getTimeLeft() / level.getDuration();

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(
                MARGIN + PADDING, canvas.getHeight() - HEIGHT - MARGIN + PADDING,
                (canvas.getWidth() - 2 * (PADDING + MARGIN)) * timePart, HEIGHT - 2 * PADDING);
    }
}
