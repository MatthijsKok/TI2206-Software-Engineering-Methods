package util;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class that handles the creation of sprites
 */
public class Sprite {

    private static GraphicsContext gc = GameCanvasManager.getInstance().getContext();

    private Image image;
    private int frames;
    private int currentFrame;
    private int frameSpeed; // Frames / second
    private Vec2d offset;
    private int width, height;
    private double framePart;

    public Sprite(String uri) {
        this(uri, 1, new Vec2d(0, 0));
    }

    public Sprite(String uri, int frames) {
        this(uri, frames, new Vec2d(0, 0));
    }

    public Sprite(String uri, Vec2d offset) {
        this(uri, 1, offset);
    }

    public Sprite(String uri, int frames, Vec2d offset) {
        setFrames(frames);
        setOffset(offset);
        setImage(uri);
        setFrameSpeed(15);
        currentFrame = 0;
        framePart = 0;
    }

    /**
     * Sets the sprite image.
     * @param image a image object containing the desired image.
     */
    public void setImage(Image image) {
        this.image = image;
        width = (int)image.getWidth()/frames;
        height = (int)image.getHeight();
    }

    /**
     * Sets the sprite image.
     * @param uri the universal resource identifier (filename) of the object.
     */
    public void setImage(String uri) {
        setImage(new Image(uri));
    }

    public void setFrames(int frames) {
        if (frames > 0) {
            this.frames = frames;
        } else {
            this.frames = 1;
        }
    }

    public void setFrameSpeed(int speed) {
        frameSpeed = speed;
    }

    /**
     * Sets the offset so it is in the middle of the sprite.
     */
    public void setOffsetToCenter() {
        setOffset(new Vec2d(image.getWidth()/2, image.getHeight()/2));
    }

    /**
     * Moves the center of the sprite to the x and y locations.
     * @param x offset on the x axis.
     * @param y offset on the y axis.
     */
    public void setOffset(double x, double y) {
        setOffset(new Vec2d(x, y));
    }

    /**
     * Moves the center of the sprite to the x and y locations.
     * @param offset A Vec2d containing the x and y values of the offset.
     */
    public void setOffset(Vec2d offset) {
        this.offset = offset;
    }

    /**
     * Updates the sprite.
     * @param dt Time expired since the last time the method was called.
     */
    public void update(double dt) {
        framePart = (framePart + dt*frameSpeed) % frames;
        currentFrame = (int)Math.floor(framePart);
    }

    /**
     * Draws the sprite to the screen.
     * @param position A Vec2D containing the x and y coordinates of the sprite.
     */
    public void draw(Vec2d position) {
        draw(position.x, position.y);
    }

    public void draw(Vec2d position, double scale) {
        draw(position.x, position.y, scale);
    }

    public void draw(Vec2d position, double xScale, double yScale) {
        draw(position.x, position.y, xScale, yScale);
    }

    public void draw(double x, double y) {
        draw(x, y, 1);
    }

    public void draw(double x, double y, double scale) {
        draw(x, y, scale, scale);
    }

    public void draw(double x, double y, double xScale, double yScale) {
        gc.drawImage(image, currentFrame*width, 0, width, height, x - offset.x*xScale, y - offset.y*yScale, width*xScale, height*yScale);
    }
}
