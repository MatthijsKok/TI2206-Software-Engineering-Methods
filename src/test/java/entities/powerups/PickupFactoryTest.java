package entities.powerups;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import game.Game;
import level.Level;
import main.UtilityClassTest;
import org.junit.Before;
import org.junit.Test;
import util.JSONParser;

import java.lang.reflect.InvocationTargetException;

import static entities.powerups.PickupFactory.spawnRandomPickUp;
import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for the PickupFactory.
 */
public class PickupFactoryTest extends BubbleTroubleApplicationTest {

    private Vec2d spawnPosition = new Vec2d(100, 300);
    private Level level;

    private long countPickups() {
        return level.getEntities().stream()
                .filter(entity -> entity instanceof Pickup)
                .count();
    }

    @Before
    public void setUp() {
        level = Game.getCurrentLevel();
    }

    @Test
    public void testSpawnRandomPickUp() {
        long pickups = countPickups();
        spawnRandomPickUp(level, spawnPosition);
        assertThat(countPickups(), is(pickups + 1));
    }

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(PickupFactory.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}
