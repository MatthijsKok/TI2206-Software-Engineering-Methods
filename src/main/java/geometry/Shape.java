package geometry;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by wouterraateland on 12-09-16.
 */
public abstract class Shape {

    protected Vec2d position;

    public Shape() {
        this(0, 0);
    }

    public Shape(double x, double y) {
        setPosition(x, y);
    }

    public void setPosition(double x, double y) {
        setPosition(new Vec2d(x, y));
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    public Vec2d getPosition() {
        return position;
    }

    public double getX() { return position.x; }
    public double getY() { return position.y; }

    public boolean intersects(Shape shape) {
        return false;
    }
}
