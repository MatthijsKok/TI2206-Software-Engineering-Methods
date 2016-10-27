package ui;

import javafx.scene.paint.Color;
import level.LevelTimer;

/**
 * HeadsUpDisplay is a acronym for Heads Up Display. A common name
 * for the overlay of a game.
 */
public class HeadsUpDisplay extends AbstractUIElement {

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
     * The level to draw the HUD for.
     */
    private final LevelTimer timer;

    /**
     * Creates a new HUD for.
     * @param timer LevelTimer - The timer for the current level to draw a HUD for.
     */
    public HeadsUpDisplay(LevelTimer timer) {
        this.timer = timer;
    }


    private double getTimeLeft() {
        return timer.getTimeLeft() / timer.getDuration();
    }

    @Override
    public void draw() {
        // Outer time bar
        getGraphicsContext().setFill(Color.BLACK);
        getGraphicsContext().fillRect(
                MARGIN, getCanvas().getHeight() - HEIGHT - MARGIN,
                getCanvas().getWidth() - 2 * MARGIN, HEIGHT);

        // Inner time bar
        getGraphicsContext().setFill(Color.RED);
        getGraphicsContext().fillRect(
                MARGIN + PADDING, getCanvas().getHeight() - HEIGHT - MARGIN + PADDING,
                (getCanvas().getWidth() - 2 * (PADDING + MARGIN)) * getTimeLeft(), HEIGHT - 2 * PADDING);
    }
}
