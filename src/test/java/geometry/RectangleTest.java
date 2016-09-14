package geometry;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by dana on 13/09/2016.
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
}
