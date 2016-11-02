package entities.character;

import main.UtilityClassTest;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;

/**
 * Test suite for the CharacterSprites class.
 */
public class CharacterSpritesTest {
    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(CharacterSprites.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}
