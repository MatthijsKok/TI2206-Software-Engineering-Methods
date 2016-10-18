package graphics;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import util.CanvasManager;

/**
 * Class that handles the creation of sprites.
 */
public class Sprite implements Cloneable {
    /**
     * The default frame per second rate of any sprite.
     */
    private static final double DEFAULT_FPS = 15; // f / s

    /**
     * Image of the sprite.
     */
    private Image image;

    /**
     * The amount of frames the sprite consists of.
     */
    private int frames;

    /**
     * The current frame to be displayed.
     */
    private transient int currentFrame;

    /**
     * The frameSpeed at which the sprite should be displayed in
     * frames per second.
     */
    private double frameSpeed; // Frames / second

    /**
     * A Vec2d containing the x and y coordinates to be used as the
     * center of the sprite.
     */
    private Vec2d offset;

    /**
     * The with and height of the sprite in pixels.
     */
    private int width, height;

    /**
     * A double representing a timeline from frame 0 to the last
     * frame. Is used to determine currentFrame.
     */
    private transient double framePart;

    /**
     * Create new Sprite with 1 frame and a offset of (0,0).
     * @param uri the document name of the sprite image.
     */
    public Sprite(final String uri) {
        this(uri, 1, new Vec2d(0, 0));
    }

    /**
     * Create new Sprite with offset of (0,0).
     *
     * @param uri the document name of the sprite image.
     * @param frames the amount of frames the sprite consists of.
     */
    public Sprite(final String uri, final int frames) {
        this(uri, frames, new Vec2d(0, 0));
    }

    /**
     * Create new Sprite with 1 frame.
     *
     * @param uri the document name of the sprite image.
     * @param offset a Vec2D containing the (x,y) offset to the center of the sprite.
     */
    public Sprite(final String uri, final Vec2d offset) {
        this(uri, 1, offset);
    }

    /**
     * Create a new Sprite.
     *
     * @param uri the document name of the sprite image.
     * @param frames the amount of frames the sprite consists of.
     * @param offset a Vec2D containing the (x,y) offset to the center of the sprite.
     */
    public Sprite(final String uri, final int frames, final Vec2d offset) {
        this(new Image(uri), frames, offset);
    }

    /**
     * Create a new Sprite.
     *
     * @param image the Image object of the sprite image.
     * @param frames the amount of frames the sprite consists of.
     * @param offset a Vec2D containing the (x,y) offset to the center of the sprite.
     */
    public Sprite(final Image image, final int frames, final Vec2d offset) {
        setFrames(frames);
        setOffset(offset);
        setImage(image);
        setFrameSpeed(DEFAULT_FPS);
        currentFrame = 0;
        framePart = 0;
    }

    /**
     * Clones a sprite using the same Image instance.
     * Use this when you want two sprites with the same image moving independently.
     *
     * @param sprite the sprite to copy.
     */
    public Sprite(final Sprite sprite) {
        this(sprite.getImage(), sprite.getFrames(), sprite.getOffset());
    }

    /**
     * Sets the sprite image.
     *
     * @param image a image object containing the desired image.
     */
    private void setImage(final Image image) {
        this.image = image;
        width = (int) image.getWidth() / frames;
        height = (int) image.getHeight();
    }

    /**
     * Gets the sprite's image.
     *
     * @return the image of the sprite.
     */
    private Image getImage() {
        return image;
    }

    /**
     * Sets the sprite's amount of frames.
     *
     * @param frames amount of frames
     */
    private void setFrames(final int frames) {
        if (frames > 0) {
            this.frames = frames;
        } else {
            this.frames = 1;
        }
    }

    /**
     * Gets the sprite's amount of frames.
     *
     * @return the frame amount of the sprite.
     */
    private int getFrames() {
        return frames;
    }

    /**
     * Sets the sprite's frame speed.
     *
     * @param speed frame speed in frames per second.
     */
    private void setFrameSpeed(final double speed) {
        frameSpeed = speed;
    }

    /**
     * Sets the offset so it is in the middle of the sprite.
     */
    public final void setOffsetToCenter() {
        setOffset(image.getWidth() / 2, image.getHeight() / 2);
    }

    /**
     * Moves the center of the sprite to the x and y locations.
     *
     * @param xOffset offset on the x axis.
     * @param yOffset offset on the y axis.
     */
    private void setOffset(final double xOffset, final double yOffset) {
        setOffset(new Vec2d(xOffset, yOffset));
    }

    /**
     * Moves the center of the sprite to the x and y locations.
     *
     * @param offset A Vec2d containing the x and y values of the offset.
     */
    private void setOffset(final Vec2d offset) {
        this.offset = offset;
    }

    /**
     * Updates the sprite.
     *
     * @param timeDifference time expired since the last time the method was called.
     */
    public final void update(final double timeDifference) {
        framePart = (framePart + timeDifference * frameSpeed) % frames;
        currentFrame = (int) Math.floor(framePart);
    }

    /**
     * Draws the sprite to the screen.
     *
     * @param position a Vec2D containing the x and y coordinates of the sprite.
     */
    public final void draw(final Vec2d position) {
        draw(position.x, position.y);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param position a Vec2D containing the x and y coordinates of the sprite.
     * @param scale a double used to scale the sprite upon drawing e.g. 0,5 for
     *              half the size.
     */
    public final void draw(final Vec2d position, final double scale) {
        draw(position.x, position.y, scale);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param position Vec2D containing the x and y coordinates of the sprite.
     * @param xScale double used to scale the sprite in width.
     * @param yScale double used to scale the sprite in height.
     */
    public final void draw(final Vec2d position, final double xScale, final double yScale) {
        draw(position.x, position.y, xScale, yScale);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param xPosition the x position where the sprite should be drawn.
     * @param yPosition the y position where the sprite should be drawn.
     */
    public final void draw(final double xPosition, final double yPosition) {
        draw(xPosition, yPosition, 1);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param xPosition the x position where the sprite should be drawn.
     * @param yPosition the y position where the sprite should be drawn.
     * @param scale the scale at which the Sprite should be drawn.
     */
    public final void draw(final double xPosition, final double yPosition, final double scale) {
        draw(xPosition, yPosition, scale, scale);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param xPosition the x position where the sprite should be drawn.
     * @param yPosition the y position where the sprite should be drawn.
     * @param xScale double used to scale the sprite in width upon drawing.
     * @param yScale double used to scale the sprite in height upon drawing.
     */
    public final void draw(final double xPosition, final double yPosition,
                           final double xScale, final double yScale) {
        final GraphicsContext graphicsContext = CanvasManager.getContext();
        if (graphicsContext != null) {
            graphicsContext.drawImage(
                    image, currentFrame * width,
                    0, width, height,
                    xPosition - offset.x * xScale, yPosition - offset.y * yScale,
                    width * xScale, height * yScale);
        }
    }

    // GETTERS
    /**
     * Returns a Vec2D with the offset of the Sprite.
     *
     * @return a Vec2D with the offset of the Sprite
     */
    public Vec2d getOffset() {
        return offset;
    }

    /**
     * Returns the width of the sprite in pixels.
     *
     * @return the width of the sprite in pixels.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the sprite in pixels.
     *
     * @return the height of the sprite in pixels.
     */
    public int getHeight() {
        return height;
    }

}
