package entities;

import entities.balls.ColoredBall;
import entities.blocks.FloorBlock;
import entities.blocks.WallBlock;
import entities.character.Character;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Basic test suite for the Entity factory.
 */
@RunWith(Parameterized.class)
public class EntityFactoryClassNameTest extends AbstractEntityFactoryTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        JSONObject ballParams = new JSONObject();
        ballParams.put("size", 2);

        return Arrays.asList(new Object[][] {
                { createJSONEntity("Player", 10, 10), Character.class },
                { createJSONEntity("Ball",   20, 10, ballParams), ColoredBall.class },
                { createJSONEntity("Wall",   30, 10), WallBlock.class },
                { createJSONEntity("Floor",  10, 30), FloorBlock.class }
        });
    }

    @SuppressWarnings("all")
    @Parameterized.Parameter
    public JSONObject input;

    @SuppressWarnings("all")
    @Parameterized.Parameter(value = 1)
    public Class expected;

    @Test
    public void test() {
        AbstractEntity entity = EntityFactory.createEntity(input);

        if (entity == null) {
            fail();
        }

        assertEquals(expected, entity.getClass());
    }
}
