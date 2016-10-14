package entities;


import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test suite for the Harpoon class.
 */
public class HarpoonTest extends BubbleTroubleApplicationTest {

    private Harpoon harpoon;
    private Vec2d shootPosition = new Vec2d(100, 300);

    @Before
    public void setUp() {
        harpoon = new Harpoon();
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals("A new harpoon should be created at (0,0)", harpoon.getPosition(), new Vec2d(0, 0));
    }

    @Test
    public void testInvisibleAfterInstantiation() {
        assertFalse("A harpoon should be invisible after instantiation", harpoon.isVisible());
    }

    @Test
    public void testShootWhileNotShot() {
        harpoon.shoot(shootPosition);
        assertEquals("A harpoon shall be shot from shooting position.", harpoon.getPosition(), shootPosition);
    }

    @Test
    public void testShootWhileAlreadyShot() {
        Vec2d shootPosition2 = new Vec2d(200, 300);
        harpoon.shoot(shootPosition);
        harpoon.shoot(shootPosition2);
        assertEquals("A harpoon can not change position while shot.", harpoon.getPosition(), shootPosition);
    }

    @Test
    public void testUpdateYGreaterThanZero() {
        harpoon.shoot(shootPosition);
        harpoon.update(0);
        assertTrue("A harpoon should remain visible until it reached y<=0", harpoon.isVisible());
    }

    @Test
    public void testUpdateYSmallerThanZero() {
        harpoon.shoot(shootPosition);
        harpoon.setPosition(200, -100);
        harpoon.update(0);
        assertFalse("A harpoon should become invisible when it reaches y<=0", harpoon.isVisible());
    }

    @Test
    public void testCollisionWithBall() {
        Ball ball = new Ball(new Vec2d(300, 200), 2);
        harpoon.shoot(shootPosition);
        harpoon.collideWith(ball);

        assertFalse("A harpoon should become invisible and changed when it collides with a ball.", harpoon.isVisible());
    }

    @Test
    public void testCollisionWithOtherEntity() {
        WallBlock otherEntity = new WallBlock(new Vec2d(300, 200));
        harpoon.shoot(shootPosition);
        harpoon.collideWith(otherEntity);

        assertFalse("Nothing should change when a harpoon collides with an object other than a ball.", harpoon.hasChanged());
    }
}
