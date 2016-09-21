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
     * Create a new circle at position (0, 0) with radius 1.
     */
    public Circle() {
        this(1);
    }

    /**
     * Create a new circle at position (x, y) with radius r.
     * @param r radius
     */
    public Circle(double r) {
        this(0, 0, r);
    }

    /**
     * Create a new circle at position (x, y) with radius radius.
     * @param x x coordinate
     * @param y y coordinate
     * @param r radius
     */
    public Circle(final double x, final double y, final double r) {
        super(x, y);
        setRadius(r);
    }

    /**
     * Set the radius of the circle. Should be bigger than zero.
     * @param radius the target radius.
     */
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
    }

    /**
     * @return the radius of the circle
     */
    public final double getRadius() {
        return this.radius;
    }

    /**
     * Entry point for intersection checks.
     * @param shape the shape to check intersection with
     * @return whether the circle intersects with shape
     */
    public boolean intersects(Shape shape) {
        if (shape instanceof Circle) {
            return intersects((Circle) shape);
        }

        if (shape instanceof Rectangle) {
            return intersects((Rectangle) shape);
        }

        return false;
    }

    /**
     * Checks whether the circle intersects with the rectangle.
     * @param rect the rectangle to check intersection with
     * @return whether the circle intersects the rectangle
     */
    private boolean intersects(Rectangle rect) {
        double dx = getX() - Math.max(rect.getLeft(), Math.min(getX(), rect.getRight()));
        double dy = getY() - Math.max(rect.getTop(), Math.min(getY(), rect.getBottom()));
        return (dx * dx + dy * dy) < (radius * radius);
    }

    /**
     * Checks whether the circle intersects with the other circle.
     * @param circle the circle to check intersection with
     * @return whether the circle intersects the circle
     */
    private boolean intersects(Circle circle) {
        return getPosition().distance(circle.getPosition()) < getRadius() + circle.getRadius();
    }
}
