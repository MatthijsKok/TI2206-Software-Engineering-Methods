package util;

import main.UtilityClassTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;

/**
 * Test suite for the JSONParser class.
 */
public class JSONParserTest {

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(JSONParser.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}
