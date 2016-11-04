package game.player;

import main.UtilityClassTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotNull;

/**
 * Test suite for the PlayerFactory class.
 */
public class PlayerFactoryTest {

    @Test
    public void testCreatePlayer2() {
        Player player = PlayerFactory.createPlayer(2);
        assertNotNull(player);
    }

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(PlayerFactory.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}
