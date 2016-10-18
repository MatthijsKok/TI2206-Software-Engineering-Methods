package entities;

import bubbletrouble.BubbleTroubleApplicationTest;
import org.json.JSONObject;

/**
 * Base class for that provides usefull methods for the
 * Entity factory test suites.
 */
abstract class AbstractEntityFactoryTest extends BubbleTroubleApplicationTest {
    static /* default */ JSONObject createJSONEntity(final String type,
                                                 final double x,
                                                 final double y,
                                                 final JSONObject attributes) {
        JSONObject entity = createJSONEntity(type, x, y);
        entity.put("attributes", attributes);

        return entity;
    }

    static /* default */ JSONObject createJSONEntity(final String type,
                                                 final double x,
                                                 final double y) {
        JSONObject entity = new JSONObject();
        entity.put("type", type);
        entity.put("x", x);
        entity.put("y", y);

        return entity;
    }
}
