package entities.powerups;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Test class for the Extra Life power-up.
 */
public class ExtraLifeTest extends BubbleTroubleApplicationTest {

    private ExtraLife extraLife;
    private Vec2d spawnPosition = new Vec2d(100, 300);

    @Before
    public void setUp() {
        extraLife = new ExtraLife();
        Character character = new Character(spawnPosition);
        extraLife.setTarget(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A extraLife should have a sprite", extraLife.getSprite());
    }
}
