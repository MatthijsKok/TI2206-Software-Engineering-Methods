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
     * @return Canvas - The getCanvas to draw on.
     */
    protected final Canvas getCanvas() {
        return CanvasManager.getCanvas();
    }

    /**
     * @return GraphicsContext - The graphics context to draw on.
     */
    protected final GraphicsContext getGraphicsContext() {
        return CanvasManager.getContext();
    }

    /**
     * Draws the element to the getCanvas.
     */
    protected abstract void draw();

}
