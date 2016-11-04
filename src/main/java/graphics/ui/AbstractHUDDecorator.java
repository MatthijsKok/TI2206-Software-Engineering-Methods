package graphics.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import util.CanvasManager;

/**
 * Decorator for the Heads Up Display.
 */
abstract class AbstractHUDDecorator implements UIElement {

    /**
     * Temporary UI element to be decorated.
     */
    private final transient UIElement element;

    /**
     * Constructor for HUDDecorator.
     * @param element The UI element to decorate.
     */
    /* default */ AbstractHUDDecorator(final UIElement element) {
        this.element = element;
    }

    /**
     * Draws the UI to the screen.
     */
    public void draw() {
        element.draw();
    }

    /**
     * @return Canvas - The getCanvas to draw on.
     */
    /* default */ final Canvas getCanvas() {
        return CanvasManager.getCanvas();
    }

    /**
     * @return GraphicsContext - The graphics context to draw on.
     */
    /* default */ final GraphicsContext getGraphicsContext() {
        return CanvasManager.getContext();
    }

}
