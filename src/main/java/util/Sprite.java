package util;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class that handles the creation of sprites.
 */
public class Sprite implements Cloneable {

    /**
     * The context this sprite is drawn to.
     */
    private static GraphicsContext gc =
            GameCanvasManager.getInstance().getContext();

    /**
     * The default frame speed of any sprite.
     */
    private static final double DEFAULT_FRAME_SPEED = 15; // f / s

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
    private int currentFrame;

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
    private double framePart;

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
        setFrameSpeed(DEFAULT_FRAME_SPEED);
        currentFrame = 0;
        framePart = 0;
    }

    /**
     * Clones a sprite using the same Image instance.
     * Use this when you want two sprites with the same image moving independently.
     *
     * @return the new sprite.
     */
    public final Sprite clone() {
        return new Sprite(image, frames, new Vec2d(getOffsetX(), getOffsetY()));
    }

    /**
     * Sets the sprite image.
     *
     * @param image a image object containing the desired image.
     */
    public final void setImage(final Image image) {
        this.image = image;
        width = (int) image.getWidth() / frames;
        height = (int) image.getHeight();
    }

    /**
     * Sets the sprite image.
     *
     * @param uri the universal resource identifier (filename) of the object.
     */
    public final void setImage(final String uri) {
        setImage(new Image(uri));
    }

    /**
     * Sets the sprite's amount of frames.
     *
     * @param frames amount of frames
     */
    public final void setFrames(final int frames) {
        if (frames > 0) {
            this.frames = frames;
        } else {
            this.frames = 1;
        }
    }

    /**
     * Sets the sprite's frame speed.
     *
     * @param speed frame speed in frames per second.
     */
    public final void setFrameSpeed(final double speed) {
        frameSpeed = speed;
    }

    /**
     * Sets the offset so it is in the middle of the sprite.
     */
    public final void setOffsetToCenter() {
        setOffset(new Vec2d(image.getWidth() / 2, image.getHeight() / 2));
    }

    /**
     * Moves the center of the sprite to the x and y locations.
     *
     * @param x offset on the x axis.
     * @param y offset on the y axis.
     */
    public final void setOffset(final double x, final double y) {
        setOffset(new Vec2d(x, y));
    }

    /**
     * Moves the center of the sprite to the x and y locations.
     *
     * @param offset A Vec2d containing the x and y values of the offset.
     */
    public final void setOffset(final Vec2d offset) {
        this.offset = offset;
    }

    /**
     * Updates the sprite.
     *
     * @param dt time expired since the last time the method was called.
     */
    public final void update(final double dt) {
        framePart = (framePart + dt * frameSpeed) % frames;
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
     * @param scale a double used to scale the sprite upon drawing e.g. 0,5 for half the size.
     */
    public final void draw(final Vec2d position, final double scale) {
        draw(position.x, position.y, scale);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param position a Vec2D containing the x and y coordinates of the sprite.
     * @param xScale a double used to scale the sprite in x direction upon drawing
     *               e.g. 0,5 for half the size.
     * @param yScale a double used to scale the sprite in y direction upon drawing
     *               e.g. 0,5 for half the size.
     */
    public final void draw(final Vec2d position, final double xScale, final double yScale) {
        draw(position.x, position.y, xScale, yScale);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param x the x position where the sprite should be drawn.
     * @param y the y position where the sprite should be drawn.
     */
    public final void draw(final double x, final double y) {
        draw(x, y, 1);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param x the x position where the sprite should be drawn.
     * @param y the y position where the sprite should be drawn.
     * @param scale the scale at which the Sprite should be drawn.
     */
    public final void draw(final double x, final double y, final double scale) {
        draw(x, y, scale, scale);
    }

    /**
     * Draws the Sprite to the screen.
     *
     * @param x the x position where the sprite should be drawn.
     * @param y the y position where the sprite should be drawn.
     * @param xScale a double used to scale the sprite in x direction upon drawing
     *               e.g. 0,5 for half the size.
     * @param yScale a double used to scale the sprite in y direction upon drawing
     *               e.g. 0,5 for half the size.
     */
    public final void draw(final double x, final double y,
                           final double xScale, final double yScale) {
        gc.drawImage(image,
                currentFrame * width, 0,
                width, height,
                x - offset.x * xScale, y - offset.y * yScale,
                width * xScale, height * yScale);
    }

    // GETTERS
    /**
     * Returns a Vec2D with the ofset of the Sprite.
     *
     * @return a Vec2D with the ofset of the Sprite
     */
    public Vec2d getOffset() {
        return offset;
    }

    /**
     * Returns the x offset of the sprite.
     *
     * @return the x offset of the sprite
     */
    public double getOffsetX() {
        return offset.x;
    }

    /**
     * Returns the y offset of the sprite.
     *
     * @return the y offset of the sprite
     */
    public double getOffsetY() {
        return offset.y;
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

    /**
     * Sets the frame of the sprite to be currently displayed, starting at 0.
     *
     * @param currentFrame the frame to set this sprite's current frame to.
     */
    public final void setCurrentFrame(final int currentFrame) {
        this.currentFrame = currentFrame;
    }
}
