package geometry;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by dana on 13/09/2016.
 */
public class RectangleTest {

    @Test
    public void intersectCircle(){
        Circle circle = new Circle(6);
        Rectangle rectangle = new Rectangle(4,5);
        assertTrue(rectangle.intersects(circle));
    }

    @Test
    public void intersectCircle2(){
        Circle circle = new Circle(6);
        circle.setPosition(10,40);
        Rectangle rectangle = new Rectangle(4,5);
        assertFalse(rectangle.intersects(circle));
    }

    @Test
    public void intersectRectangle(){
        Rectangle rectangle = new Rectangle(4,5);
        Rectangle rectangle2 = new Rectangle(1,2);
        rectangle.setOffset(5,5);
        assertFalse(rectangle.intersects(rectangle2));
    }
}
