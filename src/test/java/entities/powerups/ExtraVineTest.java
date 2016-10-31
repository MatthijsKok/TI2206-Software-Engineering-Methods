package entities.powerups;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import entities.character.Gun;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for Harpoon class.
 */
public class ExtraVineTest extends BubbleTroubleApplicationTest {

    private ExtraVine extraVine;
    private Gun gun;

    @Before
    public void setUp() {
        extraVine = new ExtraVine();
        Character character = new Character(new Vec2d(100, 300));
        gun = character.getGun();
        extraVine.setTarget(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A harpoon should have a sprite", extraVine.getSprite());
    }

    @Test
    public void testEnableEffect() {
        int maxConcurrentShots = gun.getMaxConcurrentShots();
        extraVine.enableEffect();
        assertThat(gun.getMaxConcurrentShots(), is(maxConcurrentShots + 1));
    }

    @Test
    public void testDisableEffect() {
        int maxConcurrentShots = gun.getMaxConcurrentShots();
        extraVine.disableEffect();
        assertThat(gun.getMaxConcurrentShots(), is(maxConcurrentShots - 1));
    }
}
