package geometry;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dana on 13/09/2016.
 */
public class CircleTest {

    /**
     * Test the Circle constructor
     */
    @Test
    public void Circle(){
        Circle Circle = new Circle(6);
        assertTrue(Circle.getRadius()==6);
    }

    /**
     * Test the Circle constructor
     */
    @Test
    public void Circle2(){
        Circle Circle = new Circle(6);
        assertFalse(Circle.getRadius()==4);
    }

    /**
     * Test the circle standard constructor,
     * circle with no parameters should have a radiu of 0.
     */
    @Test
    public void emptyCircle(){
        Circle circle = new Circle();
        assertTrue(circle.getRadius()==1);
    }

    /**
     * Test intersection; check if the circle intersects with a rectangle.
     * Their initial placement (0,0) is not changed, so they should intersect
     */
    @Test
    public void intersectsCircleRectangle(){
        Rectangle rectangle = new Rectangle(1,1);
        Circle circle = new Circle(1);
        assertTrue(circle.intersects(rectangle));
    }

    /**
     * Test intersection; check if the circle intersects with a rectangle.
     * the rectangle in moved with (4,5) they should not intersect.
     */
    @Test
    public void intersectsCircleRectangle2(){
        Rectangle rectangle = new Rectangle(1,1);
        Circle circle = new Circle(1);
        rectangle.setOffset(4,5);
        assertFalse(circle.intersects(rectangle));
    }

    /**
     * Test intersection; check if the circle intersects with a circle.
     * Their initial placement (0,0) is not changed, so they should intersect
     */
    @Test
    public void intersectsCircleCircle(){
        Circle circle = new Circle(1);
        Circle circle2 = new Circle(1);
        assertTrue(circle.intersects(circle2));
    }

    /**
     * Test intersection; check if the circle intersects with a circle.
     * the rectangle in moved with (4,5) they should not intersect.
    */
    @Test
    public void intersectsCircleCircle2(){
        Circle circle = new Circle(1);
        Circle circle2 = new Circle(1);
        circle2.setPosition(4,5);
        assertFalse(circle.intersects(circle2));
    }

    
}
