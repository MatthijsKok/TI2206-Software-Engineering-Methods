package geometry;

import com.sun.javafx.geom.Vec2d;

/**
 * AbstractShape is abstract base class for all collision shapes.
 */
public abstract class AbstractShape {

    /**
     * The central position of the shape.
     */
    private Vec2d position = new Vec2d(0, 0);

    /**
     * Creates a new shape instance at position (0, 0).
     */
    AbstractShape() {
        this(0, 0);
    }

    /**
     * Creates a new shape instance at position (x, y).
     * @param x x position of the shape
     * @param y y position of the shape
     */
    AbstractShape(double x, double y) {
        setPosition(x, y);
    }

    /**
     * Sets the position of the shape.
     * @param x x position
     * @param y y position
     */
    void setPosition(double x, double y) {
        position.x = x;
        position.y = y;
    }

    /**
     * Binds the position of the shape to an existing Vec2d instance.
     * @param position vector of position
     */
    public void bindPosition(Vec2d position) {
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
     * Check if a shape intersects with a certain other shape.
     * @param shape the shape to check with
     * @return whether the shapes intersect
     */
    public abstract boolean intersects(AbstractShape shape);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractShape shape = (AbstractShape) o;

        return getPosition().equals(shape.getPosition());

    }

    @Override
    public int hashCode() {
        return getPosition().hashCode();
    }
}