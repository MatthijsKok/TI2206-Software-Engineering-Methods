package entities.blocks;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

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
}