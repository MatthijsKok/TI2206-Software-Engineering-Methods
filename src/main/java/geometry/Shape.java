package geometry;

import com.sun.javafx.geom.Vec2d;

/**
 * Shape is abstract base class for all collision shapes.
 */
public abstract class Shape {

    /**
     * The central position of the shape.
     */
    private Vec2d position;

    /**
     * Creates a new shape instance at position (0, 0).
     */
    Shape() {
        this(0, 0);
    }

    /**
     * Creates a new shape instance at position (x, y).
     * @param x x position of the shape
     * @param y y position of the shape
     */
    Shape(double x, double y) {
        setPosition(x, y);
    }

    /**
     * Sets the position of the shape.
     * @param x x position
     * @param y y position
     */
    public void setPosition(double x, double y) {
        setPosition(new Vec2d(x, y));
    }

    /**
     * Sets the position of the shape.
     * @param position vector of position
     */
    public void setPosition(Vec2d position) {
        this.position = position;
    }

    /**
     * @return the position of the shape.
     */
    public Vec2d getPosition() {
        return position;
    }

    /**
     * @return x position
     */
    public double getX() {
        return position.x;
    }

    /**
     * @return y position
     */
    public double getY() {
        return position.y;
    }

    /**
     * This method checks if a shape intersects with a certain other shape.
     * @param shape the shape to check with
     * @return whether the shapes intersect
     */
    public abstract boolean intersects(Shape shape);
}
