package level;

import entities.Entity;
import entities.EntityFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JSONParser;

/**
 * This utility class loads entities into a level.
 */
class LevelLoader {

    private LevelLoader() {

    }

    static void load(Level level) {
        JSONObject json = JSONParser.parseJSONFile(level.getFilename());

        JSONArray entities = json.getJSONArray("entities");

        loadMetaData(level, json);
        loadEntities(level, entities);
    }

    private static void loadMetaData(Level level, JSONObject metaData) {
        //TODO: implement.
    }

    private static void loadEntities(Level level, JSONArray entities) {
        for (Object obj : entities) {
            JSONObject json = (JSONObject) obj;

            Entity entity = EntityFactory.createEntity(json);

            if (entity != null) {
                level.addEntity(entity);
            }
        }
    }
}
