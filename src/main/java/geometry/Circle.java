package geometry;

/**
 * Created by wouterraateland on 12-09-16.
 */
public class Circle extends Shape {

    protected double radius;

    public Circle() {
        this(1);
    }

    public Circle(double radius) {
        super();
        setPosition(0, 0);
        setRadius(radius);
    }

    public void setRadius(double radius) {
        if (radius > 0) { this.radius = radius; }
    }

    public double getRadius() { return this.radius; }

    public boolean intersects(Shape shape) {
        if (shape instanceof Circle) {
            return intersects((Circle) shape);
        }

        if (shape instanceof Rectangle) {
            return intersects((Rectangle) shape);
        }

        return false;
    }

    public boolean intersects(Rectangle rect) {
        double dx = getX() - Math.max(rect.getLeft(), Math.min(getX(), rect.getRight()));
        double dy = getY() - Math.max(rect.getTop(), Math.min(getY(), rect.getBottom()));
        return (dx * dx + dy * dy) < (radius*radius);
    }

    public boolean intersects(Circle circle) {
        return getPosition().distance(circle.getPosition()) < getRadius() + circle.getRadius();
    }
}
