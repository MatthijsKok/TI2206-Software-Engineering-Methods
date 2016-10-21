package entities;

import bubbletrouble.BubbleTroubleApplicationTest;
import org.json.JSONObject;

/**
 * Base class for that provides usefull methods for the
 * Entity factory test suites.
 */
abstract class AbstractEntityFactoryTest extends BubbleTroubleApplicationTest {
    static /* default */ JSONObject createJSONEntity(final String type,
                                                 final double xPosition,
                                                 final double yPosition,
                                                 final JSONObject attributes) {
        final JSONObject entity = createJSONEntity(type, xPosition, yPosition);
        entity.put("attributes", attributes);

        return entity;
    }

    static /* default */ JSONObject createJSONEntity(final String type,
                                                 final double xPosition,
                                                 final double yPosition) {
        final JSONObject entity = new JSONObject();
        entity.put("type", type);
        entity.put("x", xPosition);
        entity.put("y", yPosition);

        return entity;
    }
}
