package game;

import junit.framework.TestCase;
import main.BubbleTroubleApplicationTest;
import main.UtilityClassTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Test suite for the game class.
 */
public class GameTest extends BubbleTroubleApplicationTest {

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(Game.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            TestCase.fail();
        }
    }

}
