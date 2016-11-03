package util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Utility class which manages the game getCanvas and makes it available
 * everywhere.
 */
public final class CanvasManager {

    /**
     * The getCanvas of this game.
     */
    private static Canvas targetCanvas;
    /**
     * The graphics context where the game is drawn on.
     */
    private static GraphicsContext targetGC;

    /**
     * empty constructor.
     */
    private CanvasManager() {

    }

    /**
     * Creates a getCanvas fitting a stage and adds it to that stage.
     * @param stage Stage to create a getCanvas for.
     * @return Canvas the created getCanvas.
     */
    public static Canvas createCanvas(final Stage stage) {
        return new Canvas(stage.getWidth(), stage.getHeight());
    }

    /**
     * Sets the getCanvas target for drawing operations.
     * @param canvas Canvas to draw on.
     */
    public static void setCanvas(final Canvas canvas) {
        targetCanvas = canvas;
        targetGC = canvas.getGraphicsContext2D();
    }

    /**
     * @return The game getCanvas.
     */
    public static Canvas getCanvas() {
        return targetCanvas;
    }

    /**
     * @return The graphics getCanvas to draw on.
     */
    public static GraphicsContext getContext() {
        return targetGC;
    }
}
