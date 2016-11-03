package util;

import main.UtilityClassTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;

/**
 * Test suite for the Config class.
 */
public class ConfigTest {

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(Config.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}
