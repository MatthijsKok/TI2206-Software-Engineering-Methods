package util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Utility class which manages the game canvas and makes it available
 * everywhere.
 */
public final class CanvasManager {

    /**
     * The canvas of this game.
     */
    private static Canvas targetCanvas;

    /**
     * The graphics context where the game is drawn on.
     */
    private static GraphicsContext targetGC;

    private CanvasManager() {

    }

    /**
     * Creates a canvas fitting a stage and adds it to that stage.
     * @param stage Stage to create a canvas for.
     * @return Canvas the created canvas.
     */
    public static Canvas createCanvas(Stage stage) {
        return new Canvas(stage.getWidth(), stage.getHeight());
    }

    /**
     * Sets the canvas target for drawing operations.
     * @param canvas Canvas to draw on.
     */
    public static void setCanvas(Canvas canvas) {
        targetCanvas = canvas;
        targetGC = canvas.getGraphicsContext2D();
    }

    /**
     * @return The game canvas.
     */
    public static Canvas getCanvas() {
        return targetCanvas;
    }

    /**
     * @return The graphics canvas to draw on.
     */
    public static GraphicsContext getContext() {
        return targetGC;
    }
}
