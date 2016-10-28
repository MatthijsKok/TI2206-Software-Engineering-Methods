package entities.blocks;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Test suite for the FloorBlock class.
 */
public class FloorBlockTest extends BubbleTroubleApplicationTest {

    private FloorBlock floor;

    @Before
    public void setUp() {
        floor = new FloorBlock(new Vec2d(0, 0));
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A floor should have a sprite", floor.getSprite());
    }

    @Test
    public void testConstructorSetsShape() {
        Rectangle floorShape = new Rectangle(FloorBlock.FLOOR_SPRITE.getWidth(), FloorBlock.FLOOR_SPRITE.getHeight());
        assertEquals("A floor should have a rectangular bounding box", floor.getShape(), floorShape);
    }
}