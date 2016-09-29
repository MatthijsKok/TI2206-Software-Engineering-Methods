package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import util.GameCanvasManager;

/**
 * Abstract class with functionality used by most UI elements.
 */
public abstract class UIElement {
    /**
     * The graphics context to draw on.
     */
    protected static final Canvas canvas = GameCanvasManager.getInstance().getCanvas();

    /**
     * The canvas to draw on.
     */
    protected static final GraphicsContext gc = GameCanvasManager.getInstance().getContext();

    /**
     * Draws the element to the screen.
     */
    public abstract void draw();
}
