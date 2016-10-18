package geometry;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;

/**
 * Class representing a rectangle.
 */
public class Rectangle extends AbstractShape {

    /**
     * Vec2d representations of the rectangles size and offset.
     */
    private Vec2d size, offset;

    /**
     * Creates a new rectangle at position (0, 0) with size (width, height).
     * @param width rectangle width
     * @param height rectangle height
     */
    public Rectangle(final double width, final double height) {
        this(0, 0, width, height);
    }

    /**
     * Creates a new rectangle around a sprite, with the right size and offset.
     * @param sprite Sprite to create a rectangle around.
     */
    public Rectangle(final Sprite sprite) {
        this(0, 0, sprite.getWidth(), sprite.getHeight());
        setOffset(sprite.getOffset());
    }

    /**
     * Creates a new rectangle with the same dimensions as the original.
     * @param rectangle the rectangle to copy.
     */
    public Rectangle(Rectangle rectangle) {
        this(rectangle.getX(), rectangle.getY(), rectangle.getSize().x, rectangle.getSize().y);
        Vec2d rectangleOffset = rectangle.getOffset();
        setOffset(rectangleOffset.x, rectangleOffset.y);
    }

    /**
     * Creates a new rectangle at position (x, y) with size (width, height).
     * @param x x coordinate
     * @param y y coordinate
     * @param width width
     * @param height height
     */
    private Rectangle(final double x, final double y, final double width, final double height) {
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
    private void setSize(double width, double height) {
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
    private void setOffset(Vec2d offset) {
        this.offset = offset;
    }

    /**
     * @return Vec2d representation of rectangle dimensions.
     */
    private Vec2d getOffset() {
        return offset;
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
     * Set the left side of the rectangle.
     * @param left the target for the left side of the rectangle
     */
    public void setLeft(final double left) {
        setPosition(left + offset.x, getY());
    }

    /**
     * Set the right side of the rectangle.
     * @param right the target for the right side of the rectangle.
     */
    public void setRight(final double right) {
        setPosition(right + offset.x - size.x, getY());
    }

    /**
     * Set the left side of the rectangle.
     * @param top the target for the left side of the rectangle
     */
    public void setTop(final double top) {
        setPosition(getX(), top + offset.y);
    }

    /**
     * Set the right side of the rectangle.
     * @param bottom the target for the right side of the rectangle.
     */
    public void setBottom(final double bottom) {
        setPosition(getX(), bottom + offset.y - size.y);
    }

    /**
     * Entry point for intersection checks.
     * @param shape the shape to check intersection with
     * @return whether the rectangle intersects with shape
     */
    public boolean intersects(AbstractShape shape) {
        if (shape instanceof Rectangle) {
            return intersects((Rectangle) shape);
        }

        return shape instanceof Circle && shape.intersects(this);
    }

    /**
     * Checks whether the rectangle intersects with the other rectangle.
     * @param rect the rectangle to check intersection with
     * @return whether the rectangle intersects the other rectangle
     */
    private boolean intersects(Rectangle rect) {
        return rect.getLeft() < getRight()
                && rect.getRight() > getLeft()
                && rect.getTop() < getBottom()
                && rect.getBottom() > getTop();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;

        return getPosition().equals(rectangle.getPosition())
                && getSize().equals(rectangle.getSize())
                && getOffset().equals(rectangle.getOffset());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getSize().hashCode();
        result = 31 * result + getOffset().hashCode();
        return result;
    }
}
