package geometry;

/**
 * The Point class represents a 2d point.
 */
public class Point extends AbstractShape {

    /**
     * Creates a new point at (x,y).
     * @param xPosition double X coordinate of the point.
     * @param yPosition double Y coordinate of the point.
     */
    public Point(final double xPosition, final double yPosition) {
        super(xPosition, yPosition);
    }

    @Override
    public boolean intersects(AbstractShape shape) {
        if (shape instanceof Circle) {
            return intersects((Circle) shape);
        }

        return shape instanceof Rectangle
                && intersects((Rectangle) shape);

    }

    /**
     * boolean that checks if a point intersects with a Rectangle.
     * @param rect Rectangle shape.
     * @return true if they intersect.
     */
    private boolean intersects(Rectangle rect) {
        return getX() > rect.getLeft()
                && getX() < rect.getRight()
                && getY() > rect.getTop()
                && getY() < rect.getBottom();
    }

    /**
     * boolean that checks if a point intersects with a Circle.
     * @param circle Circle shape.
     * @return true if they intersect.
     */
    private boolean intersects(Circle circle) {
        return getPosition().distance(circle.getPosition()) < circle.getRadius();
    }
}
