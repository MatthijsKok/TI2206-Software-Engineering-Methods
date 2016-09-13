package geometry;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
     * Test intersection; check if the circle intersects with a rectangle.
     */
//    @Test
//    public void intersects(){
//
//    }

}
