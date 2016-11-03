package graphics;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import util.CanvasManager;

/**
 * This class represents a background image.
 */
public class Background {

    /**
     * The folder wherein background images are placed.
     */
    private static final String PATH_PREFIX = "images/backgrounds/";

    /**
     * The image this background consists of.
     */
    private final transient Image image;
    /**
     * The scale this background is drawn at.
     */
    private transient double scale;
    /**
     * The position this background is drawn at.
     */
    private transient Vec2d position;

    /**
     * Creates a new background image.
     * @param filename The file to load.
     */
    public Background(final String filename) {
        image = new Image(PATH_PREFIX + filename);

        setScale();
    }

    /**
     * Scales the background to cover the canvas.
     */
    private void setScale() {
        final Canvas canvas = CanvasManager.getCanvas();

        scale = Math.max(
                canvas.getWidth() / image.getWidth(),
                canvas.getHeight() / image.getHeight());

        position = new Vec2d(
                (canvas.getWidth() - image.getWidth() * scale) / 2,
                (canvas.getHeight() - image.getHeight() * scale) / 2);
    }

    /**
     * Draws the background image to the canvas.
     */
    public void draw() {
        final GraphicsContext graphicsContext = CanvasManager.getContext();

        graphicsContext.drawImage(image, position.x, position.y,
                image.getWidth() * scale, image.getHeight() * scale);
    }
}
