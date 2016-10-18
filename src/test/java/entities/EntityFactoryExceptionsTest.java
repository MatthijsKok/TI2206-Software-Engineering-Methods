package entities;

import com.sun.javafx.geom.Vec2d;
import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Test suite for exceptions in the EntityFactory.
 */
public class EntityFactoryExceptionsTest extends AbstractEntityFactoryTest {

    @Test
    public void testBallWithColor() {
        Ball expectedBall = new Ball(new Vec2d(0, 0), 2, Ball.Color.BLUE);

        JSONObject params = new JSONObject();
        params.put("size", 2);
        params.put("color", "blue");

        JSONObject json = createJSONEntity("Ball", 0, 0, params);

        Ball actualBall = (Ball) EntityFactory.createEntity(json);

        if (actualBall == null) {
            fail();
        }

        assertEquals(expectedBall.getRadius(), actualBall.getRadius());
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
}
