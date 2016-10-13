package geometry;

/**
 * Class representing a circle.
 */
public class Circle extends Shape {

    /**
     * The radius of the circle.
     */
    private double radius = 1;

    /**
     * Create a new circle at position (x, y) with radius r.
     * @param radius radius
     */
    public Circle(final double radius) {
        this(0, 0, radius);
    }

    /**
     * Creates a new Circle with the same dimensions as the original.
     * @param circle the circle to copy.
     */
    public Circle(final Circle circle) {
        this(circle.getX(), circle.getY(), circle.getRadius());
    }

    /**
     * Create a new circle at position (x, y) with radius radius.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     * @param radius radius
     */
    private Circle(final double xPosition, final double yPosition, final double radius) {
        super(xPosition, yPosition);
        setRadius(radius);
    }

    /**
     * Set the radius of the circle. Should be bigger than zero.
     * @param radius the target radius.
     */
    public void setRadius(final double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
    }

    /**
     * @return the radius of the circle
     */
    final double getRadius() {
        return this.radius;
    }

    /**
     * Entry point for intersection checks.
     * @param shape the shape to check intersection with
     * @return whether the circle intersects with shape
     */
    public boolean intersects(final Shape shape) {
        if (shape instanceof Circle) {
            return intersects((Circle) shape);
        }

        return shape instanceof Rectangle && intersects((Rectangle) shape);

    }

    /**
     * Checks whether the circle intersects with the rectangle.
     * @param rect the rectangle to check intersection with
     * @return whether the circle intersects the rectangle
     */
    private boolean intersects(final Rectangle rect) {
        final double xDiff = getX() - Math.max(rect.getLeft(), Math.min(getX(), rect.getRight()));
        final double yDiff = getY() - Math.max(rect.getTop(), Math.min(getY(), rect.getBottom()));
        return Math.pow(xDiff, 2) + Math.pow(yDiff, 2) < Math.pow(radius, 2);
    }

    /**
     * Checks whether the circle intersects with the other circle.
     * @param circle the circle to check intersection with
     * @return whether the circle intersects the circle
     */
    private boolean intersects(final Circle circle) {
        return getPosition().distance(circle.getPosition()) < getRadius() + circle.getRadius();
    }
}
