package geometry;

import com.sun.javafx.geom.Vec2d;

/**
 * Class representing a rectangle.
 */
public class Rectangle extends Shape {

    /**
     * Vec2d representations of the rectangles size and offset.
     */
    private Vec2d size, offset;

    /**
     * Creates a new rectangle at position (0, 0) with size (1, 1).
     */
    public Rectangle() {
        this(1, 1);
    }

    /**
     * Creates a new rectangle at position (0, 0) with size (width, height).
     * @param width rectangle width
     * @param height rectangle height
     */
    public Rectangle(final double width, final double height) {
        this(0, 0, width, height);
    }

    /**
     * Creates a new rectangle at position (x, y) with size (width, height).
     * @param x x coordinate
     * @param y y coordinate
     * @param width width
     * @param height height
     */
    public Rectangle(final double x, final double y, final double width, final double height) {
        super(x, y);
        setOffset(0, 0);
        setSize(width, height);
    }

    /**
     * Sets the size of the rectangle. Both width and height should be greater
     * than or equal to zero.
     * @param width target width
     * @param height target height
     */
    public void setSize(double width, double height) {
        setSize(new Vec2d(width, height));
    }

    /**
     * Sets the size of the rectangle. Both width and height should be greater
     * than or equal to zero.
     * @param size target size
     */
    public void setSize(Vec2d size) {
        if (size.x < 0) {
            size.x = 0;
        }

        if (size.y < 0) {
            size.y = 0;
        }
        this.size = size;
    }

    /**
     * @return Vec2d representation of rectangle dimensions.
     */
    public Vec2d getSize() {
        return this.size;
    }

    /**
     * Sets the rectangle offset.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setOffset(double x, double y) {
        setOffset(new Vec2d(x, y));
    }

    /**
     * Sets the rectangle offset.
     * @param offset target offset
     */
    public void setOffset(Vec2d offset) {
        this.offset = offset;
    }

    /**
     * @return left most x coordinate.
     */
    public double getLeft() {
        return getX() - offset.x;
    }

    /**
     * @return top most y coordinate.
     */
    public double getTop() {
        return getY() - offset.y;
    }

    /**
     * @return right most x coordinate.
     */
    public double getRight() {
        return getX() + size.x - offset.x;
    }

    /**
     * @return bottom most y coordinate.
     */
    public double getBottom() {
        return getY() + size.y - offset.y;
    }

    /**
     * Entry point for intersection checks.
     * @param shape the shape to check intersection with
     * @return whether the rectangle intersects with shape
     */
    public boolean intersects(Shape shape) {
        if (shape instanceof Rectangle) {
            return intersects((Rectangle) shape);
        }

        if (shape instanceof Circle) {
            return ((Circle) shape).intersects(this);
        }

        return false;
    }

    /**
     * Checks whether the rectangle intersects with the other rectangle.
     * @param rect the rectangle to check intersection with
     * @return whether the rectangle intersects the other rectangle
     */
    private boolean intersects(Rectangle rect) {
        return (rect.getLeft() < getRight()
                && rect.getRight() > getLeft()
                && rect.getTop() < getBottom()
                && rect.getBottom() > getTop());
    }
}
