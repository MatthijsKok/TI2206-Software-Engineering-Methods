package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract class with functionality used by most ui elements.
 */
abstract class UIElement {
    /**
     * Draws the element to the canvas.
     * @param canvas The Canvas to draw on
     * @param graphicsContext The GraphicsContext to draw on
     */
    abstract void draw(Canvas canvas, GraphicsContext graphicsContext);
}
