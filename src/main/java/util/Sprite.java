package util;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by wouterraateland on 08-09-16.
 */
public class Sprite {

    private Image image;
    private int frames;
    private int currentFrame;
    private Vec2d offset;

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
        setImage(uri);
        setFrames(frames);
        setOffset(offset);
        currentFrame = 0;
    }

    public void setImage(Image image) {
        this.image = image;
    }

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

    public void setOffset(double x, double y) {
        setOffset(new Vec2d(x, y));
    }

    public void setOffset(Vec2d offset) {
        this.offset = offset;
    }

    public void draw(Vec2d position) {
        draw(position.x, position.y);
    }

    public void draw(double x, double y) {
        GraphicsContext gc = GameCanvasManager.getInstance().getContext();
        gc.drawImage(image, x - offset.x, y - offset.y);
    }
}
