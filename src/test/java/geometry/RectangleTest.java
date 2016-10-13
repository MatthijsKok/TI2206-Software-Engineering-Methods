package geometry;

import com.sun.javafx.geom.Vec2d;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test suite for the Rectangle class.
 */
public class RectangleTest {

    /**
     * check if a rectangle intersects with a circle
     * they should intersect, since they are both at position 0,0
     */
    @Test
    public void intersectCircle(){
        Circle circle = new Circle(6);
        Rectangle rectangle = new Rectangle(4,5);
        assertTrue(rectangle.intersects(circle));
    }

    /**
     * check if a rectangle intersects with a circle
     * they should not intersect, since one ball is at position 4,4
     */
    @Test
    public void intersectCircle2(){
        Circle circle = new Circle(1);
        circle.setPosition(4,4);
        Rectangle rectangle = new Rectangle(2,3);
        assertFalse(rectangle.intersects(circle));
    }

    /**
     * check if a rectangle intersects with a rectangle
     * they should intersect, since they are both at position 0,0
     */
    @Test
    public void intersectRectangle(){
        Rectangle rectangle = new Rectangle(4,5);
        Rectangle rectangle2 = new Rectangle(1,2);
        assertTrue(rectangle.intersects(rectangle2));
    }

    /**
     * check if a rectangle intersects with a rectangle
     * they should not intersect, since one ball is at position 5,5
     */
    @Test
    public void intersectRectangle2(){
        Rectangle rectangle = new Rectangle(4,5);
        Rectangle rectangle2 = new Rectangle(1,2);
        rectangle.setOffset(5,5);
        assertFalse(rectangle.intersects(rectangle2));
    }

    /**
     * check if a rectangle itersects with a shape (rectangle)
     * should be true
     */
    @Test
    public void intersectShape(){
        Rectangle rect = new Rectangle(1, 1);
        Shape shape = new Rectangle(1, 1);
        assertTrue(rect.intersects(shape));
    }

    /**
     * check if a rectangle itersects with a shape (circle)
     * should be true
     */
    @Test
    public void intersectShape2(){
        Rectangle rect = new Rectangle(1, 1);
        Shape shape = new Circle(1);
        assertTrue(rect.intersects(shape));
    }

    /**
     * check if a rectangle itersects with a shape (none)
     * should be false
     */
    @Test
    public void intersectShape3(){
        Rectangle rect = new Rectangle(1, 1);
        Shape shape = new Shape() {
            @Override
            public boolean intersects(Shape shape) {
                return false;
            }
        };
        assertFalse(rect.intersects(shape));
    }
    /**
     * check setSize
     */
    @Test
    public void setSize(){
        Rectangle rect = new Rectangle(2,2);
        Vec2d vector = new Vec2d(2.0,2.0);
        assertEquals(rect.getSize(), vector);
    }
}
