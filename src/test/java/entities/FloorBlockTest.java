package entities;

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
    /**
     * The shape used for the floor object in the game.
     */
    private final Rectangle FLOOR_SHAPE = new Rectangle(32, 32);

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
        assertEquals("A floor should have a rectangular bounding box", floor.getShape(), FLOOR_SHAPE);
    }
}