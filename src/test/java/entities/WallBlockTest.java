package entities;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Test suite for the WallBlock class.
 */
public class WallBlockTest extends BubbleTroubleApplicationTest {
    /**
     * The shape used for the wall object in the game.
     */
    private static final Rectangle WALL_SHAPE = new Rectangle(32, 32);

    private static WallBlock wall;

    @Before
    public void setUp() {
        wall = new WallBlock(new Vec2d(0, 0));
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A wall should have a sprite", wall.getSprite());
    }

    @Test
    public void testConstructorSetsShape() {
        assertEquals("A wall should have a rectangular bounding box", wall.getShape(), WALL_SHAPE);
    }
}
