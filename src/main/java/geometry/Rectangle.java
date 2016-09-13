package geometry;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by wouterraateland on 12-09-16.
 */
public class Rectangle extends Shape {

    protected Vec2d size, offset;

    public Rectangle() {
        this(1.d, 1.d);
    }

    public Rectangle(double width, double height) {
        super();
        setPosition(0, 0);
        setOffset(0, 0);
        setSize(width, height);
    }

    public void setSize(double width, double height) {
        setSize(new Vec2d(width, height));
    }
    public void setSize(Vec2d size) {
        this.size = size;
    }

    public Vec2d getSize() { return this.size; }

    public void setOffset(double x, double y) { setOffset(new Vec2d(x, y)); }
    public void setOffset(Vec2d offset) { this.offset = offset; }

    public double getLeft() { return position.x - offset.x; }
    public double getTop() { return position.y - offset.y; }
    public double getRight() { return position.x + size.x - offset.x; }
    public double getBottom() { return position.y + size.y - offset.y; }

    public boolean intersects(Shape shape) {
        if (shape instanceof Rectangle) {
            return intersects((Rectangle) shape);
        }

        if (shape instanceof Circle) {
            return ((Circle) shape).intersects(this);
        }

        return false;
    }


    public boolean intersects(Rectangle rect) {
        return (rect.getLeft() > getRight() ||
                rect.getRight() < getLeft() ||
                rect.getTop() > getBottom() ||
                rect.getBottom() < getTop());
    }
}
