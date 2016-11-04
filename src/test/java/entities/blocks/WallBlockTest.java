package entities.blocks;

import com.sun.javafx.geom.Vec2d;
import main.BubbleTroubleApplicationTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

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
}
