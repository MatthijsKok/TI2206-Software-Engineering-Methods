package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import util.CanvasManager;

/**
 * Abstract class with functionality used by most ui elements.
 */
abstract class AbstractUIElement {

    /**
     * Creates a new AbstractUIElement instance.
     */
    AbstractUIElement() {
        // Does nothing.
    }

    /**
     * @return Canvas - The canvas to draw on.
     */
    protected final Canvas canvas() {
        return CanvasManager.getCanvas();
    }

    /**
     * @return GraphicsContext - The graphics context to draw on.
     */
    protected final GraphicsContext graphicsContext() {
        return CanvasManager.getContext();
    }

    /**
     * Draws the element to the canvas.
     */
    protected abstract void draw();

}
