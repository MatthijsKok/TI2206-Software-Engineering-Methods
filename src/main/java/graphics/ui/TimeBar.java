package graphics.ui;

import javafx.scene.paint.Color;
import level.LevelTimer;

/**
 * Decorator class for the HUD elements.
 */
public class TimeBar extends AbstractHUDDecorator {

    /**
     * Padding around the time bar.
     */
    private static final int MARGIN = 8;

    /**
     * Padding around the time bar.
     */
    private static final int PADDING = 2;

    /**
     * Height of the time bar.
     */
    private static final int HEIGHT = 24;

    /**
     * The level timer to draw.
     */
    private final LevelTimer timer;

    /**
     * Creates a new HUD for.
     * @param timer LevelTimer - The timer for the current level to draw a HUD for.
     * @param element The UIElement to draw over.
     */
    public TimeBar(UIElement element, LevelTimer timer) {
        super(element);
        this.timer = timer;
    }

    private double getTimeLeft() {
        return timer.getTimeLeft() / timer.getDuration();
    }

    @Override
    public void draw() {
        super.draw();

        // Outer time bar
        getGraphicsContext().setFill(Color.BLACK);
        getGraphicsContext().fillRect(
                0, getCanvas().getHeight() - HEIGHT - MARGIN,
                getCanvas().getWidth(), HEIGHT);

        // Inner time bar
        getGraphicsContext().setFill(Color.RED);
        getGraphicsContext().fillRect(0, getCanvas().getHeight() - HEIGHT - MARGIN + PADDING,
                (getCanvas().getWidth() - 2 * (PADDING + MARGIN)) * getTimeLeft(), HEIGHT);
    }
}
