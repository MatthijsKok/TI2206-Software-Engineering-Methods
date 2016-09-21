package ui;

import javafx.scene.canvas.GraphicsContext;
import util.GameCanvasManager;

/**
 * Abstract class with functionality used by most UI elements.
 */
public abstract class UIElement {
    /**
     * The graphics context of the canvas manager.
     */
    protected static GraphicsContext gc = GameCanvasManager.getInstance().getContext();

    /**
     * Draws the element to the screen.
     */
    public abstract void draw();
}
