package entities.blocks;

import main.BubbleTroubleApplicationTest;
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
        Rectangle wallShape = new Rectangle(WallBlock.WALL_SPRITE.getWidth(), WallBlock.WALL_SPRITE.getHeight());
        assertEquals("A wall should have a rectangular bounding box", wall.getShape(), wallShape);
    }
}
