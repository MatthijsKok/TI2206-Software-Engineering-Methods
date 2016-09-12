package geometry;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by wouterraateland on 12-09-16.
 */
public class Rectangle extends Shape {

    protected Vec2d size;

    public Rectangle() {
        this(1.d, 1.d);
    }

    public Rectangle(double width, double height) {
        setPosition(0, 0);
        setSize(width, height);
    }

    public void setSize(double width, double height) {
        setSize(new Vec2d(width, height));
    }

    public void setSize(Vec2d size) {
        this.size = size;
    }

    public boolean intersects(Rectangle rect) {
        return false;
    }
}
