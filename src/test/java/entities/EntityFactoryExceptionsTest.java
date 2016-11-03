package entities;

import com.sun.javafx.geom.Vec2d;
import entities.balls.ColoredBall;
import junit.framework.TestCase;
import main.UtilityClassTest;
import org.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Test suite for exceptions in the EntityFactory.
 */
public class EntityFactoryExceptionsTest extends AbstractEntityFactoryTest {

    @Test
    public void testBallWithColor() {
        ColoredBall expectedBall = new ColoredBall(new Vec2d(0, 0), 2, ColoredBall.Color.BLUE);

        JSONObject params = new JSONObject();
        params.put("size", 2);
        params.put("color", "blue");

        JSONObject json = createJSONEntity("Ball", 0, 0, params);

        ColoredBall actualBall = (ColoredBall) EntityFactory.createEntity(json);

        if (actualBall == null) {
            fail();
        }

        assertEquals(expectedBall.getSize(), actualBall.getSize());
        assertEquals(expectedBall.getColor(), actualBall.getColor());
    }

    @Test
    public void testMoreCharactersThanPlayers() {
        JSONObject json = createJSONEntity("Player", 0, 0);
        EntityFactory.createEntity(json);
        assertNull(EntityFactory.createEntity(json));
    }

    @Test
    public void testNonExistentType() {
        JSONObject json = createJSONEntity("Unknown", 0, 0);
        assertNull(EntityFactory.createEntity(json));
    }

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(EntityFactory.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            TestCase.fail();
        }
    }
}
