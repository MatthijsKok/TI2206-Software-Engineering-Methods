package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import util.CanvasManager;

/**
 * Decorator for the Heads Up Display.
 */
public abstract class AbstractHUDDecorator implements UIElement {

    /**
     * Temporary UI element to be decorated.
     */
    private final UIElement tempUI;

    /**
     * Constructor for HUDDecorator.
     * @param tempUI The UI element to decorate.
     */
    public AbstractHUDDecorator(UIElement tempUI) {
        this.tempUI = tempUI;
    }

    /**
     * Draws the UI to the screen.
     */
    public void draw() {
        tempUI.draw();
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

}
