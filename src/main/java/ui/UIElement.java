package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import util.GameCanvasManager;

/**
 * Abstract class with functionality used by most ui elements.
 */
abstract class UIElement {
    /**
     * The graphics context to draw on.
     */
    static final Canvas CANVAS = GameCanvasManager.getInstance().getCanvas();

    /**
     * The canvas to draw on.
     */
    static final GraphicsContext GC = GameCanvasManager.getInstance().getContext();

    /**
     * Draws the element to the screen.
     */
    public abstract void draw();
}
