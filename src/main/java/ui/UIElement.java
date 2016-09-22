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
    private static GraphicsContext gc = GameCanvasManager.getInstance().getContext();

    /**
     * @return the grapics context.
     */
    protected final GraphicsContext gc() {
        return gc;
    }

    /**
     * Draws the element to the screen.
     */
    public abstract void draw();
}
